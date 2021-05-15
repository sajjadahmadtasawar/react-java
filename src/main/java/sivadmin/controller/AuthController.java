package sivadmin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sivadmin.exception.AppException;
import sivadmin.models.Bruker;
import sivadmin.models.Rolle;
import sivadmin.models.Roller;
import sivadmin.payload.ApiRespons;
import sivadmin.payload.JwtAuthenticationResponse;
import sivadmin.payload.Request.Logginn;
import sivadmin.payload.Request.BrukerRequest;
import sivadmin.repository.BrukerRepository;
import sivadmin.repository.RolleRepository;
import sivadmin.security.JwtTokenProvider;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    final AuthenticationManager authenticationManager;
    final BrukerRepository brukerRepository;
    final RolleRepository rolleRepository;
    final PasswordEncoder passwordEncoder;
    final JwtTokenProvider tokenProvider;

    public AuthController(AuthenticationManager authenticationManager, BrukerRepository brukerRepository, RolleRepository rolleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.brukerRepository = brukerRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.rolleRepository = rolleRepository;
    }

    @PostMapping("/logginn")
    public ResponseEntity<?> authenticateBruker(@Valid @RequestBody Logginn logginn) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        logginn.getBrukernavn(),
                        logginn.getPassord()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/opprett")
    public ResponseEntity<?> opprettBruker(@Valid @RequestBody BrukerRequest brukerRequest) {
        if(brukerRepository.existsByBrukernavn(brukerRequest.getBrukernavn())) {
            return new ResponseEntity(new ApiRespons(false, "Bruker med brukernavnet existerer!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(brukerRepository.existsByEpost(brukerRequest.getEpost())) {
            return new ResponseEntity(new ApiRespons(false, "Bruker med epost adresse existerer!"),
                    HttpStatus.BAD_REQUEST);
        }

        Bruker bruker = new Bruker(brukerRequest.getNavn(), brukerRequest.getBrukernavn(),
                brukerRequest.getEpost(), brukerRequest.getPassord());

        bruker.setPassord(passwordEncoder.encode(bruker.getPassord()));

        Rolle rolle = rolleRepository.findByRolleNavn(Roller.ROLE_INTERVJUER)
                .orElseThrow(() -> new AppException("Bruker Role ikke satt."));

        bruker.setRoller(Collections.singleton(rolle));

        Bruker result = brukerRepository.save(bruker);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/brukere/{brukername}")
                .buildAndExpand(result.getBrukernavn()).toUri();

        return ResponseEntity.created(location).body(new ApiRespons(true, "Bruker opprettet!"));
    }
}
