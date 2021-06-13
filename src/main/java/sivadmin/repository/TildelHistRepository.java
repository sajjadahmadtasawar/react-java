package sivadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sivadmin.models.TildelHist;

@RepositoryRestResource(collectionResourceRel = "tildel_historikk", path = "tildel_historikk")
public interface TildelHistRepository extends JpaRepository<TildelHist, Long> {
    Page<TildelHist> findAll(Specification<TildelHist> spec, Pageable pageable);
}
