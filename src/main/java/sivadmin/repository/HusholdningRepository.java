package sivadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sivadmin.models.Husholdning;

@RepositoryRestResource(collectionResourceRel = "husholdninger", path = "husholdninger")
public interface HusholdningRepository extends JpaRepository<Husholdning, Long> {
    Page<Husholdning> findAll(Specification<Husholdning> spec, Pageable pageable);
}
