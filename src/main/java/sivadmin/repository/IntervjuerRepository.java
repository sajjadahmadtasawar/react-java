package sivadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sivadmin.models.Intervjuer;

@RepositoryRestResource(collectionResourceRel = "intervjuere", path = "intervjuere")
public interface IntervjuerRepository extends JpaRepository<Intervjuer, Long> {
    Page<Intervjuer> findAll(Specification<Intervjuer> spec, Pageable pageable);
}
