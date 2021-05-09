package sivadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sivadmin.models.Prosjekt;
import sivadmin.repository.ProsjektRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
            @RequestParam(required = false) String navn,
            @RequestParam(required = false) String aargang,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {

        try {
            List<Prosjekt> prosjekter = new ArrayList<Prosjekt>();
            Pageable paging = PageRequest.of(page, size);

            Page<Prosjekt> prosjektSider;
            if (produktNummer == null)
                prosjektSider = prosjektRepository.findAll(paging);
            else
                prosjektSider = prosjektRepository.findByProduktNummerIsContaining(produktNummer, paging);

            prosjekter = prosjektSider.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("prosjekter", prosjekter);
            response.put("currentPage", prosjektSider.getNumber());
            response.put("totalItems", prosjektSider.getTotalElements());
            response.put("totalPages", prosjektSider.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
