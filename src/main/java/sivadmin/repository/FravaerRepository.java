package sivadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sivadmin.models.Fravaer;

@RepositoryRestResource(collectionResourceRel = "fravaerer", path = "fravaerer")
public interface FravaerRepository extends JpaRepository<Fravaer, Long> {
    Page<Fravaer> findAll(Specification<Fravaer> spec, Pageable pageable);
}
