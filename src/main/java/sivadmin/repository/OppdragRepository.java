package sivadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sivadmin.models.Oppdrag;

@RepositoryRestResource(collectionResourceRel = "oppdrager", path = "oppdrager")
public interface OppdragRepository extends JpaRepository<Oppdrag, Long> {
    Page<Oppdrag> findAll(Specification<Oppdrag> spec, Pageable pageable);
}
