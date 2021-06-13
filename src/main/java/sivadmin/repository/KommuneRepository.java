package sivadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sivadmin.models.Kommune;

@RepositoryRestResource(collectionResourceRel = "kommuner", path = "kommuner")
public interface KommuneRepository extends JpaRepository<Kommune, Long> {
    Page<Kommune> findAll(Specification<Kommune> spec, Pageable pageable);
}
