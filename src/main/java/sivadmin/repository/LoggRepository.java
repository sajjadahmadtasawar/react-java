package sivadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sivadmin.models.Logg;

@RepositoryRestResource(collectionResourceRel = "logger", path = "logger")
public interface LoggRepository extends JpaRepository<Logg, Long> {
    Page<Logg> findAll(Specification<Logg> spec, Pageable pageable);
}
