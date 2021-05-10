package sivadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sivadmin.models.Prosjekt;

import java.util.List;

@Repository
public interface ProsjektRepository extends JpaRepository<Prosjekt, Long>,
        JpaSpecificationExecutor<Prosjekt> {
    List<Prosjekt> findByProsjektNavn(@Param("navn") String navn);
    Page<Prosjekt> findByProduktNummerIsContaining(@Param("produktNummer") String produktNummer, Pageable pageable);
    Page<Prosjekt> findByProsjektNavnIsContaining(@Param("prosjektNavn") String prosjektNavn, Pageable pageable);
    Page<Prosjekt> findByAargangIsContaining(@Param("aargang") String aargang, Pageable pageable);
    Page<Prosjekt> findByProsjektStatusIsContaining(@Param("prosjektStatus") String prosjektStatus, Pageable pageable);
    Page<Prosjekt> findAll(Specification<Prosjekt> spec, Pageable pageable);
}
