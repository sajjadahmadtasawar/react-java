package sivadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import sivadmin.models.Skjema;
import sivadmin.models.Utvalg;

import java.util.Optional;

@Repository
public interface UtvalgRepository extends JpaRepository<Utvalg, Long>,
        JpaSpecificationExecutor<Utvalg> {
    Page<Utvalg> findAll(Specification<Utvalg> spec, Pageable pageable);
    Optional<Utvalg> findUtvalgBySkjema(Skjema skjema);
}
