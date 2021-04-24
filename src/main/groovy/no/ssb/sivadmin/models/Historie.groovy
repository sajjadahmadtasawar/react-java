package no.ssb.sivadmin.models


import javax.persistence.*

@Entity
class Historie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    @ManyToOne
    @JoinColumn(name = "HISTORIE_ID", referencedColumnName = "ID")
    IntervjuObjekt intervjuObjekt

    String resultat
    Integer historieNummer
}
