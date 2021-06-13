package sivadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sivadmin.models.ProsessLogg;

@RepositoryRestResource(collectionResourceRel = "prosses_logg", path = "prosses_logg")
public interface ProsessLoggRepository extends JpaRepository<ProsessLogg, Long> {
    Page<ProsessLogg> findAll(Specification<ProsessLogg> spec, Pageable pageable);
}
