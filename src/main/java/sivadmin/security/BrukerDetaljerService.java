package sivadmin.security;

import org.springframework.stereotype.Service;
import sivadmin.exception.ResourceNotFoundException;
import sivadmin.models.Bruker;
import sivadmin.repository.BrukerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service("brukerDetaljerService")
public class BrukerDetaljerService implements UserDetailsService {

    final BrukerRepository brukerRepository;

    public BrukerDetaljerService(BrukerRepository brukerRepository) {
        this.brukerRepository = brukerRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String brukernavn)
            throws UsernameNotFoundException {
        Bruker bruker = brukerRepository.findByBrukernavnOrEpost(brukernavn, brukernavn)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Bruker med brukernavnet eller epost finnes ikke : " + brukernavn)
        );

        return BrukerPrincipal.create(bruker);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        Bruker bruker = brukerRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Bruker", "id", id)
        );

        return BrukerPrincipal.create(bruker);
    }
}