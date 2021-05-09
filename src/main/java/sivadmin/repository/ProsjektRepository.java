package sivadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import sivadmin.models.Prosjekt;

import java.util.List;

@Repository
public interface ProsjektRepository extends JpaRepository<Prosjekt, Long> {
    List<Prosjekt> findByProsjektNavn(@Param("navn") String navn);
    Page<Prosjekt> findByProduktNummerIsContaining(@Param("produktNummer") String produktNummer, Pageable pageable);
    List<Prosjekt> findByProsjektNavnIsContaining(@Param("navn") String navn);
}
