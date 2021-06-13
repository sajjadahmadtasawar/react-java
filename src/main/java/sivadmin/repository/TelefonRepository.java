package sivadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sivadmin.models.Telefon;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "telefoner", path = "telefoner")
public interface TelefonRepository extends JpaRepository<Telefon, Long> {
    Page<Telefon> findAll(Specification<Telefon> spec, Pageable pageable);
}
