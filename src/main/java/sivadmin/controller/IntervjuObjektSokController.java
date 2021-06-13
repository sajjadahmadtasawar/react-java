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
import sivadmin.models.IntervjuObjekt;
import sivadmin.payload.ApiRespons;
import sivadmin.payload.Request.SkjemaRequest;
import sivadmin.repository.IntervjuObjektRepository;
import sivadmin.repository.IntervjuObjektSokRepository;
import sivadmin.repository.SkjemaRepository;
import sivadmin.specifications.SearchCriteria;
import sivadmin.specifications.SearchOperation;
import sivadmin.specifications.SokSpecification;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/intervjuObjekter", produces = { MediaType.APPLICATION_JSON_VALUE })
public class IntervjuObjektSokController {

    final
    IntervjuObjektRepository intervjuObjektRepository;

    public IntervjuObjektSokController(IntervjuObjektRepository intervjuObjektRepository) {
        this.intervjuObjektRepository = intervjuObjektRepository;
    }

    @GetMapping("/liste")
    @Timed
    public ResponseEntity<Map<String, Object>> hentListe(
            @RequestParam(required = false) Long intervjuObjektId,
            @RequestParam(required = false) String intervjuObjektNummer,
            @RequestParam(defaultValue = "1") int side,
            @RequestParam(defaultValue = "10") int visAntall,
            @RequestParam(defaultValue = "opprettetDato") String sort,
            @RequestParam(defaultValue = "false") Boolean asc
    ) {

        try {
            List<IntervjuObjekt> intervjuObjekter;

            Pageable paging = PageRequest.of(side-1, visAntall, Sort.by(sort).ascending());

            if(!asc) {
                paging = PageRequest.of(side-1, visAntall, Sort.by(sort).descending());
            }

            Page<IntervjuObjekt> liste;
            SokSpecification<IntervjuObjekt> sokSpecification = new SokSpecification<>();

            if (intervjuObjektId != null)
                sokSpecification.add(new SearchCriteria("id", intervjuObjektId, SearchOperation.EQUAL));

            liste = intervjuObjektRepository.findAll(sokSpecification, paging);

            intervjuObjekter = liste.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("intervjuObjekter", intervjuObjekter);
            response.put("side", liste.getNumber() + 1);
            response.put("antall", liste.getTotalElements());
            response.put("antallSider", liste.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public IntervjuObjekt hentIOEtterId(@PathVariable(value = "id") Long id) {
        return intervjuObjektRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("IntervjuObjekt", "navn", id));
    }

}
