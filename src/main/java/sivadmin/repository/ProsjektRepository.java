package sivadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import sivadmin.models.Prosjekt;

import java.util.Optional;

@Repository
public interface ProsjektRepository extends JpaRepository<Prosjekt, Long>,
        JpaSpecificationExecutor<Prosjekt> {
    Page<Prosjekt> findAll(Specification<Prosjekt> spec, Pageable pageable);
    boolean existsProsjektByProsjektNavn(String prosjektNavn);
    Optional<Prosjekt> findByProsjektNavn(String prosjektNavn);

}
