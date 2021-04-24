package no.ssb.sivadmin.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne

@Entity
class UtvalgImport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    @OneToOne(optional=false)
    @JoinColumn(name = "SKJEMA_ID", referencedColumnName = "ID")
    Skjema skjema

    Date importDato
    int antallFil
    int antallImportert
    String importertAv
    String melding
}
