package sivadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sivadmin.models.IntHist;

@RepositoryRestResource(collectionResourceRel = "intervjuer_historikk", path = "intervjuer_historikk")
public interface IntHistRepository extends JpaRepository<IntHist, Long> {
    Page<IntHist> findAll(Specification<IntHist> spec, Pageable pageable);
}
