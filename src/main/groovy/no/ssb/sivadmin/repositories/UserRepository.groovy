package no.ssb.sivadmin.repositories

import no.ssb.sivadmin.models.auth.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username)

    Boolean existsByUsername(String username)

    Boolean existsByEmail(String email)
}