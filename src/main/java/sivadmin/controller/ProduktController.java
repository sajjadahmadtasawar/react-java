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
import sivadmin.models.Produkt;
import sivadmin.payload.ApiRespons;
import sivadmin.payload.Request.ProduktRequest;
import sivadmin.repository.ProduktRepository;
import sivadmin.specifications.SearchCriteria;
import sivadmin.specifications.SearchOperation;
import sivadmin.specifications.SokSpecification;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/produkter", produces = { MediaType.APPLICATION_JSON_VALUE })
public class ProduktController {

    final
    ProduktRepository produktRepository;

    public ProduktController(ProduktRepository produktRepository) {
        this.produktRepository = produktRepository;
    }

    @GetMapping("/liste")
    @Timed
    public ResponseEntity<Map<String, Object>> hentListe(
            @RequestParam(required = false) String navn,
            @RequestParam(required = false) String produktNummer,
            @RequestParam(required = false) String finansiering,
            @RequestParam(defaultValue = "1") int side,
            @RequestParam(defaultValue = "10") int visAntall,
            @RequestParam(defaultValue = "opprettetDato") String sort,
            @RequestParam(defaultValue = "false") Boolean asc
    ) {

        try {
            List<Produkt> produkter;

            Pageable paging = PageRequest.of(side-1, visAntall, Sort.by(sort).ascending());

            if(!asc) {
                paging = PageRequest.of(side-1, visAntall, Sort.by(sort).descending());
            }

            Page<Produkt> produktListe;
            SokSpecification<Produkt> sokSpecification = new SokSpecification<>();

            if (!navn.isEmpty())
                sokSpecification.add(new SearchCriteria("navn", navn, SearchOperation.MATCH));

            if (!produktNummer.isEmpty())
                sokSpecification.add(new SearchCriteria("produktNummer", produktNummer, SearchOperation.MATCH));

            if (!finansiering.isEmpty())
                sokSpecification.add(new SearchCriteria("finansiering", finansiering, SearchOperation.MATCH));

            produktListe = produktRepository.findAll(sokSpecification, paging);

            produkter = produktListe.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("produkter", produkter);
            response.put("side", produktListe.getNumber() + 1);
            response.put("antall", produktListe.getTotalElements());
            response.put("antallSider", produktListe.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public Produkt hentId(@PathVariable(value = "id") Long id) {
        return produktRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prosjekt deltager", "navn", id));
    }

    @PostMapping("/opprett")
    public ResponseEntity<?> opprett(@Valid @RequestBody ProduktRequest produktRequest) {
       Produkt produkt = map(produktRequest);

        try {
            produktRepository.save(produkt);

            return new ResponseEntity(new ApiRespons(true, "Produkt opprettet!"),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new ApiRespons(false, "Mislykkes!. " + e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/oppdater/{id}")
    public ResponseEntity<?> oppdater(@PathVariable("id") Long id, @Valid @RequestBody ProduktRequest prosjektRequest) {
        sjekkId(id);

       Produkt prosjekt = map(prosjektRequest);

        try {
            produktRepository.save(prosjekt);

            return new ResponseEntity(new ApiRespons(true, "Produkt Oppdatert!"),
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
            produktRepository.deleteById(id);

            return new ResponseEntity(new ApiRespons(true, "Produkt slettet!"),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ApiRespons(false, "Mislykkes!. " + e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<?> sjekkId(Long id) {
        if(!produktRepository.existsById(id)) {
            return new ResponseEntity(new ApiRespons(false, "Produkt med finnes ikke!"),
                    HttpStatus.BAD_REQUEST);
        }

        return null;
    }

    private Produkt map(ProduktRequest produktRequest) {
       Produkt produkt = new Produkt(produktRequest);

        return produkt;
    }
}
