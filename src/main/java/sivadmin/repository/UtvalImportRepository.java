package sivadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sivadmin.models.UtvalgImport;

@RepositoryRestResource(collectionResourceRel = "utvalg_importer", path = "utvalg_importer")
public interface UtvalImportRepository extends JpaRepository<UtvalgImport, Long> {
    Page<UtvalgImport> findAll(Specification<UtvalgImport> spec, Pageable pageable);
}
