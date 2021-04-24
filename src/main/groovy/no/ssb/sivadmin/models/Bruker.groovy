package no.ssb.sivadmin.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Bruker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    String navn
    String username
    String password
    boolean enabled
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
}
