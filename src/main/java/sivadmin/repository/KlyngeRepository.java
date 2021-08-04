package sivadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sivadmin.models.Klynge;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "klynger", path = "klynger")
public interface KlyngeRepository extends JpaRepository<Klynge, Long> {
    List<Klynge> findByKlyngeNavn(@Param("klyngeNavn") String klyngeNavn);
    Klynge getByKlyngeNavn(String klyngeNavn);
    Page<Klynge> findAll(Specification<Klynge> spec, Pageable pageable);
}
