package no.ssb.sivadmin.models

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.OneToOne

@Entity
class Klynge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "INTERVJUER_ID", referencedColumnName = "ID")
    private List<Intervjuer> intervjuere

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "KOMMUNE_ID", referencedColumnName = "ID")
    private List<Kommune> kommuner

    String klyngeNavn
    String klyngeSjef
    String epost
    String beskrivelse
}
