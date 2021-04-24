package no.ssb.sivadmin.models

import no.ssb.sivadmin.enums.AdresseType

import javax.persistence.*

@Entity
class TildeltHist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    @ManyToOne
    @JoinColumn(name = "TILDELT_HIST_ID", referencedColumnName = "ID")
    IntervjuObjekt intervjuObjekt

    @OneToOne(optional=false)
    @JoinColumn(name = "INTERVJUER_ID", referencedColumnName = "ID")
    Intervjuer intervjuer

    Date dato
}
