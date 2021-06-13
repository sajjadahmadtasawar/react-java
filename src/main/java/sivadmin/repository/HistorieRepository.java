package sivadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sivadmin.models.Historie;

@RepositoryRestResource(collectionResourceRel = "historier", path = "historier")
public interface HistorieRepository extends JpaRepository<Historie, Long> {
    Page<Historie> findAll(Specification<Historie> spec, Pageable pageable);
}
