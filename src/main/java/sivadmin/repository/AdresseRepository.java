package sivadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sivadmin.models.Adresse;

@RepositoryRestResource(collectionResourceRel = "adresser", path = "adresser")
public interface AdresseRepository extends JpaRepository<Adresse, Long> {
    Page<Adresse> findAll(Specification<Adresse> spec, Pageable pageable);
}
