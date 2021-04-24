package no.ssb.sivadmin.models

import no.ssb.sivadmin.enums.AdresseType

import javax.persistence.*

@Entity
class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    @ManyToOne
    @JoinColumn(name = "ADDRESSE_ID", referencedColumnName = "ID")
    IntervjuObjekt intervjuObjekt

    @Enumerated(EnumType.STRING)
    AdresseType adresseType

    String kommentar
    Date gyldigFom
    Date gyldigTom
    String gateAdresse
    String tilleggsAdresse
    String husBruksNummer
    String underNummer
    String bokstavFeste
    String boligNummer
    String postNummer
    String postSted
    String kommuneNummer
    String gateGaardNummer
    Boolean original
    String redigertAv
    Date redigertDato = new Date()
    Boolean gjeldende
}
