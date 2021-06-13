package sivadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import sivadmin.models.IntervjuObjektSok;
import sivadmin.models.IntervjuObjektSok;

import java.util.Optional;

@Repository
public interface IntervjuObjektSokRepository extends JpaRepository<IntervjuObjektSok, Long>,
        JpaSpecificationExecutor<IntervjuObjektSok> {
    Page<IntervjuObjektSok> findAll(Specification<IntervjuObjektSok> spec, Pageable pageable);
}
