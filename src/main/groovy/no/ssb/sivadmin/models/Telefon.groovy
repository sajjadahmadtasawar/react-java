package no.ssb.sivadmin.models


import javax.persistence.*

@Entity
class Telefon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    @ManyToOne
    @JoinColumn(name = "TELEFON_ID", referencedColumnName = "ID")
    IntervjuObjekt intervjuObjekt

    String telefonNummer
    String kommentar
    String kilde
    Boolean resepsjon
    Boolean original
    Boolean gjeldende
    String redigertAv
    Date redigertDato = new Date()
}
