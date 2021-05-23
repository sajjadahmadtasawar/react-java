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
import sivadmin.models.ProsjektDeltager;
import sivadmin.models.ProsjektLeder;
import sivadmin.payload.ApiRespons;
import sivadmin.payload.Request.ProsjektDeltagerRequest;
import sivadmin.payload.Request.ProsjektRequest;
import sivadmin.repository.ProsjektDeltagerRepository;
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
@RequestMapping(value = "/api/prosjekt_deltagere", produces = { MediaType.APPLICATION_JSON_VALUE })
public class ProsjektDeltagerController {

    final
    ProsjektDeltagerRepository prosjektDeltagerRepository;

    public ProsjektDeltagerController(ProsjektDeltagerRepository prosjektDeltagerRepository) {
        this.prosjektDeltagerRepository = prosjektDeltagerRepository;
    }

    @GetMapping("/liste")
    @Timed
    public ResponseEntity<Map<String, Object>> hentListe(
            @RequestParam(required = false) String deltagerNavn,
            @RequestParam(required = false) String deltagerEpost,
            @RequestParam(required = false) String deltagerInitialer,
            @RequestParam(defaultValue = "1") int side,
            @RequestParam(defaultValue = "10") int visAntall,
            @RequestParam(defaultValue = "opprettetDato") String sort,
            @RequestParam(defaultValue = "false") Boolean asc
    ) {

        try {
            List<ProsjektDeltager> prosjektDeltagere;

            Pageable paging = PageRequest.of(side-1, visAntall, Sort.by(sort).ascending());

            if(!asc) {
                paging = PageRequest.of(side-1, visAntall, Sort.by(sort).descending());
            }

            Page<ProsjektDeltager> prosjektDeltagerListe;
            SokSpecification<ProsjektDeltager> sokSpecification = new SokSpecification<>();

            if (!deltagerNavn.isEmpty())
                sokSpecification.add(new SearchCriteria("deltagerNavn", deltagerNavn, SearchOperation.MATCH));

            if (!deltagerEpost.isEmpty())
                sokSpecification.add(new SearchCriteria("deltagerEpost", deltagerEpost, SearchOperation.MATCH));

            if (!deltagerInitialer.isEmpty())
                sokSpecification.add(new SearchCriteria("deltagerInitialer", deltagerInitialer, SearchOperation.MATCH));

            prosjektDeltagerListe = prosjektDeltagerRepository.findAll(sokSpecification, paging);

            prosjektDeltagere = prosjektDeltagerListe.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("prosjektDeltagere", prosjektDeltagere);
            response.put("side", prosjektDeltagerListe.getNumber() + 1);
            response.put("antall", prosjektDeltagerListe.getTotalElements());
            response.put("antallSider", prosjektDeltagerListe.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ProsjektDeltager hentProsjektEtterId(@PathVariable(value = "id") Long id) {
        return prosjektDeltagerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prosjekt deltager", "navn", id));
    }

    @PostMapping("/opprett")
    public ResponseEntity<?> opprett(@Valid @RequestBody ProsjektDeltagerRequest prosjektDeltagerRequest) {
       ProsjektDeltager prosjektDeltager = map(prosjektDeltagerRequest);

        try {
            prosjektDeltagerRepository.save(prosjektDeltager);

            return new ResponseEntity(new ApiRespons(true, "Prosjekt opprettet!"),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new ApiRespons(false, "Mislykkes!. " + e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/oppdater/{id}")
    public ResponseEntity<?> oppdater(@PathVariable("id") Long id, @Valid @RequestBody ProsjektDeltagerRequest prosjektRequest) {
        sjekkProsjektById(id);

       ProsjektDeltager prosjekt = map(prosjektRequest);

        try {
            prosjektDeltagerRepository.save(prosjekt);

            return new ResponseEntity(new ApiRespons(true, "Prosjekt Oppdatert!"),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ApiRespons(false, "Mislykkes!. " + e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/slett/{id}")
    public ResponseEntity<?> slett(@Valid @RequestBody Long id) {
        sjekkProsjektById(id);

        try {
            prosjektDeltagerRepository.deleteById(id);

            return new ResponseEntity(new ApiRespons(true, "Prosjekt slettet!"),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ApiRespons(false, "Mislykkes!. " + e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<?> sjekkProsjektById(Long id) {
        if(!prosjektDeltagerRepository.existsById(id)) {
            return new ResponseEntity(new ApiRespons(false, "Prosjekt med finnes ikke!"),
                    HttpStatus.BAD_REQUEST);
        }

        return null;
    }

    private ProsjektDeltager map(ProsjektDeltagerRequest prosjektDeltagerRequest) {
       ProsjektDeltager prosjektDeltager = new ProsjektDeltager(prosjektDeltagerRequest);

        return prosjektDeltager;
    }
}
