package sivadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sivadmin.models.LoggLinje;

@RepositoryRestResource(collectionResourceRel = "logg_linjer", path = "logg_linjer")
public interface LoggLinjeRepository extends JpaRepository<LoggLinje, Long> {
    Page<LoggLinje> findAll(Specification<LoggLinje> spec, Pageable pageable);
}
