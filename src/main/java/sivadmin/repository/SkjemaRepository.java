package sivadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import sivadmin.models.Prosjekt;
import sivadmin.models.Skjema;

import java.util.Optional;

@Repository
public interface SkjemaRepository extends JpaRepository<Skjema, Long>,
        JpaSpecificationExecutor<Skjema> {
    Page<Skjema> findAll(Specification<Skjema> spec, Pageable pageable);
    boolean existsSkjemaBySkjemaNavn(String skjemaNavn);
    boolean existsSkjemaBySkjemaKortNavn(String skjemaKortNavn);
    Optional<Skjema> findBySkjemaNavn(String skjemaNavn);
    Skjema findFirstByOrderByIdAsc();

}
