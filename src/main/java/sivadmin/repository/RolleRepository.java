package sivadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sivadmin.models.Rolle;
import sivadmin.models.Roller;

import java.util.Optional;

@Repository
public interface RolleRepository extends JpaRepository<Rolle, Long> {
    Optional<Rolle> findByRolleNavn(Roller rolleNavn);
    Rolle findByRolleNavn(String rolleNavn);
}