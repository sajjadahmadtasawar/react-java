package sivadmin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sivadmin.exception.ResourceNotFoundException;
import sivadmin.models.Bruker;
import sivadmin.payload.BrukerProfil;
import sivadmin.payload.BrukerIdentitetTilgjengelig;
import sivadmin.payload.BrukerDetaljer;
import sivadmin.repository.BrukerRepository;
import sivadmin.security.BrukerPrincipal;
import sivadmin.security.DenBruker;

@RestController
@RequestMapping("api/brukere")
public class BrukerController {

    @Autowired
    private BrukerRepository brukerRepository;

    private static final Logger logger = LoggerFactory.getLogger(BrukerController.class);

    @GetMapping("/meg")
    public BrukerDetaljer getCurrentUser(@DenBruker BrukerPrincipal brukerPrincipal) {
        BrukerDetaljer brukerDetaljer = new BrukerDetaljer(brukerPrincipal.getId(), brukerPrincipal.getBrukernavn(), brukerPrincipal.getNavn());
        return brukerDetaljer;
    }

    @GetMapping("/sokBrukernavn")
    public BrukerIdentitetTilgjengelig sokBrukernavn(@RequestParam(value = "brukernavn") String brukernavn) {
        Boolean isAvailable = !brukerRepository.existsByBrukernavn(brukernavn);
        return new BrukerIdentitetTilgjengelig(isAvailable);
    }

    @GetMapping("/sokEpost")
    public BrukerIdentitetTilgjengelig sokEpost(@RequestParam(value = "epost") String epost) {
        Boolean isAvailable = !brukerRepository.existsByEpost(epost);
        return new BrukerIdentitetTilgjengelig(isAvailable);
    }

    @GetMapping("/{brukernavn}")
    public BrukerProfil getUserProfile(@PathVariable(value = "brukernavn") String brukernavn) {
        Bruker bruker = brukerRepository.findByBrukernavn(brukernavn)
                .orElseThrow(() -> new ResourceNotFoundException("Bruker", "username", brukernavn));

        BrukerProfil brukerProfil = new BrukerProfil(bruker.getId(), bruker.getBrukernavn(), bruker.getNavn(), bruker.getOpprettetDato());

        return brukerProfil;
    }

}
