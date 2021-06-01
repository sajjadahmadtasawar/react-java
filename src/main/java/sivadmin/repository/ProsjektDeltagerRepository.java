package sivadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sivadmin.models.ProsjektDeltager;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "prosjekt_deltagere", path = "prosjekt_deltagere")
public interface ProsjektDeltagerRepository extends JpaRepository<ProsjektDeltager, Long> {
    List<ProsjektDeltager> findByDeltagerNavnLike(@Param("navn") String navn);
    ProsjektDeltager findFirstByOrderByIdAsc();
    Page<ProsjektDeltager> findAll(Specification<ProsjektDeltager> spec, Pageable pageable);
}
