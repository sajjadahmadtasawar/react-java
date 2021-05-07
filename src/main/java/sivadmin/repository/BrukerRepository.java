package sivadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import sivadmin.models.Bruker;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrukerRepository extends JpaRepository<Bruker, Long> {
    Optional<Bruker> findByEpost(String epost);
    Optional<Bruker> findByBrukernavnOrEpost(String brukernavn, String epost);
    List<Bruker> findByIdIn(List<Long> userIds);
    Optional<Bruker> findByBrukernavn(String brukernavn);
    Boolean existsByBrukernavn(String brukernavn);
    Boolean existsByEpost(String epost);
}
