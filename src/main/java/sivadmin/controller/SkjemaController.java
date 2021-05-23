package sivadmin.controller;

import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sivadmin.exception.ResourceNotFoundException;
import sivadmin.models.Skjema;
import sivadmin.payload.ApiRespons;
import sivadmin.payload.Request.SkjemaRequest;
import sivadmin.repository.SkjemaRepository;
import sivadmin.specifications.SearchCriteria;
import sivadmin.specifications.SearchOperation;
import sivadmin.specifications.SokSpecification;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/skjemaer", produces = { MediaType.APPLICATION_JSON_VALUE })
public class SkjemaController {

    @Autowired
    SkjemaRepository skjemaRepository;

    @GetMapping("/liste")
    @Timed
    public ResponseEntity<Map<String, Object>> hentListe(
            @RequestParam(required = false) String delProduktNummer,
            @RequestParam(required = false) String skjemaNavn,
            @RequestParam(required = false) String skjemaKortNavn,
            @RequestParam(required = false) String undersokelseType,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "1") int side,
            @RequestParam(defaultValue = "10") int visAntall,
            @RequestParam(defaultValue = "opprettetDato") String sort,
            @RequestParam(defaultValue = "false") Boolean asc
    ) {

        try {
            List<Skjema> skjemaer;

            Pageable paging = PageRequest.of(side-1, visAntall, Sort.by(sort).ascending());

            if(!asc) {
                paging = PageRequest.of(side-1, visAntall, Sort.by(sort).descending());
            }

            Page<Skjema> skjemaListe;
            SokSpecification<Skjema> sokSpecification = new SokSpecification<>();

            if (!skjemaNavn.isEmpty())
                sokSpecification.add(new SearchCriteria("skjemaNavn", skjemaNavn, SearchOperation.MATCH));

            if (!delProduktNummer.isEmpty())
                sokSpecification.add(new SearchCriteria("delProduktNummer", delProduktNummer, SearchOperation.MATCH));

            if (!skjemaKortNavn.isEmpty())
                sokSpecification.add(new SearchCriteria("skjemaKortNavn", skjemaKortNavn, SearchOperation.MATCH));

            if (!undersokelseType.isEmpty())
                sokSpecification.add(new SearchCriteria("undersokelseType", undersokelseType, SearchOperation.EQUAL));

            if (!status.isEmpty())
                sokSpecification.add(new SearchCriteria("status", status, SearchOperation.EQUAL));

            skjemaListe = skjemaRepository.findAll(sokSpecification, paging);

            skjemaer = skjemaListe.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("skjemaer", skjemaer);
            response.put("side", skjemaListe.getNumber() + 1);
            response.put("antall", skjemaListe.getTotalElements());
            response.put("antallSider", skjemaListe.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public Skjema hentSkjemaEtterId(@PathVariable(value = "id") Long id) {
        return skjemaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skjema", "navn", id));
    }

    @GetMapping("/sok/{skjemaNavn}")
    public Skjema hentSkjemaEtterNavn(@PathVariable(value = "skjemaNavn") String skjemaNavn) {
        return skjemaRepository.findBySkjemaNavn(skjemaNavn)
                .orElseThrow(() -> new ResourceNotFoundException("Skjema", "navn", skjemaNavn));
    }

    @PostMapping("/opprett")
    public ResponseEntity<?> opprett(@Valid @RequestBody SkjemaRequest skjemaRequest) {
        if(skjemaRepository.existsSkjemaBySkjemaKortNavn(skjemaRequest.getSkjemaKortNavn())) {
            return new ResponseEntity(new ApiRespons(false, "Skjema med dette kortnavn existerer!"),
                    HttpStatus.BAD_REQUEST);
        }

        Skjema skjema = mapSkjema(skjemaRequest);

        try {
            skjemaRepository.save(skjema);

            return new ResponseEntity(new ApiRespons(true, "Skjema opprettet!"),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new ApiRespons(false, "Mislykkes!. " + e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/oppdater/{id}")
    public ResponseEntity<?> oppdater(@PathVariable("id") Long id, @Valid @RequestBody SkjemaRequest skjemaRequest) {
        sjekkSkjemaById(id);

        Skjema skjema = mapSkjema(skjemaRequest);

        try {
            skjemaRepository.save(skjema);

            return new ResponseEntity(new ApiRespons(true, "Skjema oppdatert!"),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new ApiRespons(false, "Mislykkes!. " + e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/slett/{id}")
    public ResponseEntity<?> slett(@Valid @RequestBody Long id) {
        sjekkSkjemaById(id);

        try {
            skjemaRepository.deleteById(id);

            return new ResponseEntity(new ApiRespons(true, "Skjema slettet!"),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new ApiRespons(false, "Mislykkes!. " + e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<?> sjekkSkjemaById(Long id) {
        if(!skjemaRepository.existsById(id)) {
            return new ResponseEntity(new ApiRespons(false, "Skjema med finnes ikke!"),
                    HttpStatus.BAD_REQUEST);
        }

        return null;
    }

    private Skjema mapSkjema(SkjemaRequest skjemaRequest) {
        Skjema skjema = new Skjema(skjemaRequest);

        return skjema;
    }
}
