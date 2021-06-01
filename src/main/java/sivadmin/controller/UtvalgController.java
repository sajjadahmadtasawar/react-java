package sivadmin.controller;

import io.micrometer.core.annotation.Timed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sivadmin.exception.ResourceNotFoundException;
import sivadmin.models.Prosjekt;
import sivadmin.models.ProsjektLeder;
import sivadmin.payload.ApiRespons;
import sivadmin.payload.Request.ProsjektRequest;
import sivadmin.repository.ProsjektLederRepository;
import sivadmin.repository.ProsjektRepository;
import sivadmin.specifications.SearchCriteria;
import sivadmin.specifications.SearchOperation;
import sivadmin.specifications.SokSpecification;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/utvalger", produces = { MediaType.APPLICATION_JSON_VALUE })
public class UtvalgController {

    final
    ProsjektRepository prosjektRepository;

    public UtvalgController(ProsjektRepository prosjektRepository) {
        this.prosjektRepository = prosjektRepository;
    }

    @GetMapping("/liste")
    @Timed
    public ResponseEntity<Map<String, Object>> hentListe(
            @RequestParam(required = false) String produktNummer,
            @RequestParam(required = false) String prosjektNavn,
            @RequestParam(required = false) String aargang,
            @RequestParam(required = false) String prosjektStatus,
            @RequestParam(defaultValue = "1") int side,
            @RequestParam(defaultValue = "10") int visAntall,
            @RequestParam(defaultValue = "opprettetDato") String sort,
            @RequestParam(defaultValue = "false") Boolean asc
    ) {

        try {
            List<Prosjekt> prosjekter;

            Pageable paging = PageRequest.of(side-1, visAntall, Sort.by(sort).ascending());

            if(!asc) {
                paging = PageRequest.of(side-1, visAntall, Sort.by(sort).descending());
            }

            Page<Prosjekt> prosjektListe;
            SokSpecification<Prosjekt> sokSpecification = new SokSpecification<>();

            if (!prosjektNavn.isEmpty())
                sokSpecification.add(new SearchCriteria("prosjektNavn", prosjektNavn, SearchOperation.MATCH));

            if (!produktNummer.isEmpty())
                sokSpecification.add(new SearchCriteria("produktNummer", produktNummer, SearchOperation.MATCH));

            if (!aargang.isEmpty())
                sokSpecification.add(new SearchCriteria("aargang", aargang, SearchOperation.MATCH));

            if (!prosjektStatus.isEmpty())
                sokSpecification.add(new SearchCriteria("prosjektStatus", prosjektStatus, SearchOperation.EQUAL));

            prosjektListe = prosjektRepository.findAll(sokSpecification, paging);

            prosjekter = prosjektListe.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("prosjekter", prosjekter);
            response.put("side", prosjektListe.getNumber() + 1);
            response.put("antall", prosjektListe.getTotalElements());
            response.put("antallSider", prosjektListe.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public Prosjekt hentProsjektEtterId(@PathVariable(value = "id") Long id) {
        return prosjektRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prosjekt", "navn", id));
    }

    @GetMapping("/sok/{prosjektNavn}")
    public Prosjekt hentProsjektEtterNavn(@PathVariable(value = "prosjektNavn") String prosjektNavn) {
      return prosjektRepository.findByProsjektNavn(prosjektNavn)
                .orElseThrow(() -> new ResourceNotFoundException("Prosjekt", "navn", prosjektNavn));
    }

    @PostMapping("/opprett")
    public ResponseEntity<?> opprett(@Valid @RequestBody ProsjektRequest prosjektRequest) {
        if(prosjektRepository.existsProsjektByProsjektNavn(prosjektRequest.getProsjektNavn())) {
            return new ResponseEntity(new ApiRespons(false, "Prosjekt med dette navn existerer!"),
                    HttpStatus.BAD_REQUEST);
        }

        Prosjekt prosjekt = map(prosjektRequest);

        try {
            prosjektRepository.save(prosjekt);

            return new ResponseEntity(new ApiRespons(true, "Prosjekt opprettet!"),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new ApiRespons(false, "Mislykkes!. " + e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/oppdater/{id}")
    public ResponseEntity<?> oppdater(@PathVariable("id") Long id, @Valid @RequestBody ProsjektRequest prosjektRequest) {
        sjekkId(id);

        Prosjekt prosjekt = map(prosjektRequest);

        try {
            prosjektRepository.save(prosjekt);

            return new ResponseEntity(new ApiRespons(true, "Prosjekt Oppdatert!"),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ApiRespons(false, "Mislykkes!. " + e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/slett/{id}")
    public ResponseEntity<?> slett(@Valid @RequestBody Long id) {
        sjekkId(id);

        try {
            prosjektRepository.deleteById(id);

            return new ResponseEntity(new ApiRespons(true, "Prosjekt slettet!"),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ApiRespons(false, "Mislykkes!. " + e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<?> sjekkId(Long id) {
        if(!prosjektRepository.existsById(id)) {
            return new ResponseEntity(new ApiRespons(false, "Prosjekt med finnes ikke!"),
                    HttpStatus.BAD_REQUEST);
        }

        return null;
    }

    private Prosjekt map(ProsjektRequest prosjektRequest) {
        Prosjekt prosjekt = new Prosjekt(prosjektRequest);

        return prosjekt;
    }
}
