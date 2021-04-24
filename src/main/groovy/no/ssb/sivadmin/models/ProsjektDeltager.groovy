package no.ssb.sivadmin.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class ProsjektDeltager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    @ManyToOne
    @JoinColumn(name = "PROSJEKT_DELTAGER_ID", referencedColumnName = "ID")
    Prosjekt projekt

    String deltagerInitialer
    String deltagerNavn
    String deltagerEpost
}
