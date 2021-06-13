package sivadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import sivadmin.models.IntervjuObjekt;
import sivadmin.models.IntervjuObjektSok;

@Repository
public interface IntervjuObjektRepository extends JpaRepository<IntervjuObjekt, Long>,
        JpaSpecificationExecutor<IntervjuObjekt> {
    Page<IntervjuObjekt> findAll(Specification<IntervjuObjekt> spec, Pageable pageable);
}
