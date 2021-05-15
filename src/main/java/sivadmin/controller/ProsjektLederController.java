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
import sivadmin.models.Prosjekt;
import sivadmin.models.ProsjektLeder;
import sivadmin.models.ProsjektLeder;
import sivadmin.repository.ProsjektLederRepository;
import sivadmin.repository.ProsjektRepository;
import sivadmin.specifications.ProsjektSpecification;
import sivadmin.specifications.SearchCriteria;
import sivadmin.specifications.SearchOperation;
import sivadmin.specifications.SokSpecification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/prosjekt_ledere", produces = { MediaType.APPLICATION_JSON_VALUE })
public class ProsjektLederController {

    @Autowired
    ProsjektLederRepository prosjektLederRepository;

    @GetMapping("/liste")
    @Timed
    public ResponseEntity<Map<String, Object>> hentListe(
            @RequestParam(required = false) String navn,
            @RequestParam(required = false) String initialer,
            @RequestParam(required = false) String epost,
            @RequestParam(defaultValue = "1") int side,
            @RequestParam(defaultValue = "10") int visAntall,
            @RequestParam(defaultValue = "opprettetDato") String sort,
            @RequestParam(defaultValue = "false") Boolean asc
    ) {

        try {
            List<ProsjektLeder> objekter = new ArrayList<ProsjektLeder>();

            Pageable paging = PageRequest.of(side-1, visAntall, Sort.by(sort).ascending());

            if(!asc) {
                paging = PageRequest.of(side-1, visAntall, Sort.by(sort).descending());
            }

            Page<ProsjektLeder> liste;
            SokSpecification sokSpecification = new SokSpecification();

            if (!navn.isEmpty())
                sokSpecification.add(new SearchCriteria("navn", navn, SearchOperation.MATCH));

            if (!initialer.isEmpty())
                sokSpecification.add(new SearchCriteria("initialer", initialer, SearchOperation.MATCH));

            if (!epost.isEmpty())
                sokSpecification.add(new SearchCriteria("epost", epost, SearchOperation.MATCH));

            liste = prosjektLederRepository.findAll(sokSpecification, paging);

            objekter = liste.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("objekter", objekter);
            response.put("side", liste.getNumber() + 1);
            response.put("antall", liste.getTotalElements());
            response.put("antallSider", liste.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ProsjektLeder hentProsjektLederEtterId(@PathVariable(value = "id") Long id) {
        ProsjektLeder prosjektLeder = prosjektLederRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prosjektleder", "id", id));

        return prosjektLeder;
    }
}
