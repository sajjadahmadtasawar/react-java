package sivadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sivadmin.models.Periode;

@RepositoryRestResource(collectionResourceRel = "perioder", path = "perioder")
public interface PeriodeRepository extends JpaRepository<Periode, Long> {
    Page<Periode> findAll(Specification<Periode> spec, Pageable pageable);
}
