package sivadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sivadmin.models.StatHist;

@RepositoryRestResource(collectionResourceRel = "status_historikk", path = "status_historikk")
public interface StatHistRepository extends JpaRepository<StatHist, Long> {
    Page<StatHist> findAll(Specification<StatHist> spec, Pageable pageable);
}
