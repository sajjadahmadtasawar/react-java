package sivadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import sivadmin.models.Prosjekt;
import sivadmin.models.ProsjektLeder;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "prosjekt_ledere", path = "prosjekt_ledere")
public interface ProsjektLederRepository extends JpaRepository<ProsjektLeder, Long> {
    List<ProsjektLeder> findByNavn(@Param("navn") String navn);
    ProsjektLeder findFirstByOrderByIdAsc();
}