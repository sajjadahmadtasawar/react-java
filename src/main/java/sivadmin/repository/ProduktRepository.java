package sivadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sivadmin.models.Produkt;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "produkter", path = "produkter")
public interface ProduktRepository extends JpaRepository<Produkt, Long> {
    List<Produkt> findByNavn(@Param("navn") String navn);
    Produkt findFirstByOrderByIdAsc();
    Page<Produkt> findAll(Specification<Produkt> spec, Pageable pageable);
}
