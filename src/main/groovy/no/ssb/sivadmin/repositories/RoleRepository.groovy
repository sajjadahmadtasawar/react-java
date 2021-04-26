package no.ssb.sivadmin.repositories

import no.ssb.sivadmin.models.auth.ERole
import no.ssb.sivadmin.models.auth.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name)
}
