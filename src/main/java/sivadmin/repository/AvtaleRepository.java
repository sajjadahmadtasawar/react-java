package sivadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sivadmin.models.Avtale;

@RepositoryRestResource(collectionResourceRel = "avtaler", path = "avtaler")
public interface AvtaleRepository extends JpaRepository<Avtale, Long> {
    Page<Avtale> findAll(Specification<Avtale> spec, Pageable pageable);
}
