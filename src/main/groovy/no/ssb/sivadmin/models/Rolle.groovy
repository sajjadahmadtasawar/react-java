package no.ssb.sivadmin.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Rolle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    String authority
}