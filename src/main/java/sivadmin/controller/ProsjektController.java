package sivadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sivadmin.models.Prosjekt;
import sivadmin.repository.ProsjektRepository;
import sivadmin.specifications.ProsjektSpecification;
import sivadmin.specifications.SearchCriteria;
import sivadmin.specifications.SearchOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/prosjekter", produces = { MediaType.APPLICATION_JSON_VALUE })
public class ProsjektController {

    @Autowired
    ProsjektRepository prosjektRepository;

    @GetMapping("/liste")
    public ResponseEntity<Map<String, Object>> hentListe(
            @RequestParam(required = false) String produktNummer,
            @RequestParam(required = false) String prosjektNavn,
            @RequestParam(required = false) String aargang,
            @RequestParam(required = false) String prosjektStatus,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "opprettetDato") String sort,
            @RequestParam(defaultValue = "false") Boolean asc
    ) {

        try {
            List<Prosjekt> prosjekter = new ArrayList<Prosjekt>();
            Pageable paging = PageRequest.of(page, size, Sort.by(sort).ascending());

            if(!asc) {
                paging = PageRequest.of(page, size, Sort.by(sort).descending());
            }

            Page<Prosjekt> prosjektListe;

            prosjektListe = prosjektRepository.findAll(paging);
            ProsjektSpecification prosjektSpecification = new ProsjektSpecification();

            if (!prosjektNavn.isEmpty())
                prosjektSpecification.add(new SearchCriteria("prosjektNavn", prosjektNavn, SearchOperation.MATCH));

            if (!produktNummer.isEmpty())
                prosjektSpecification.add(new SearchCriteria("produktNummer", produktNummer, SearchOperation.MATCH));

            if (!aargang.isEmpty())
                prosjektSpecification.add(new SearchCriteria("aargang", aargang, SearchOperation.MATCH));

            if (!prosjektStatus.isEmpty())
                prosjektSpecification.add(new SearchCriteria("prosjektStatus", prosjektStatus, SearchOperation.EQUAL));

            prosjektListe = prosjektRepository.findAll(prosjektSpecification, paging);

            prosjekter = prosjektListe.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("prosjekter", prosjekter);
            response.put("side", prosjektListe.getNumber());
            response.put("antall", prosjektListe.getTotalElements());
            response.put("antallSider", prosjektListe.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
