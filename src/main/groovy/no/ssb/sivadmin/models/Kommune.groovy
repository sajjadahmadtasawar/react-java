package no.ssb.sivadmin.models

import javax.persistence.*

@Entity
class Kommune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    @ManyToOne
    @JoinColumn(name = "KOMMUNE_ID", referencedColumnName = "ID")
    private Klynge klynge

    String kommuneNummer
    String kommuneNavn
}
