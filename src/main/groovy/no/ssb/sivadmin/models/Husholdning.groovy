package no.ssb.sivadmin.models

import no.ssb.sivadmin.enums.IntervjuerArbeidsType
import no.ssb.sivadmin.enums.IntervjuerStatus
import no.ssb.sivadmin.enums.Kjonn

import javax.persistence.*

@Entity
class Husholdning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    @ManyToOne
    @JoinColumn(name = "HUSHOLDNING_ID", referencedColumnName = "ID")
    IntervjuObjekt intervjuObjekt

    String navn
    Date fodselsDato
    String personKode
    String fodselsNummer
    Integer husholdNummer
}
