package no.ssb.sivadmin.models

import no.ssb.sivadmin.enums.IntervjuerArbeidsType
import no.ssb.sivadmin.enums.IntervjuerStatus
import no.ssb.sivadmin.enums.Kjonn

import javax.persistence.*

@Entity
class Intervjuer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    @ManyToOne(optional=false)
    @JoinColumn(name = "INTERVJUER_ID", referencedColumnName = "ID")
    Klynge klynge

    @Enumerated(EnumType.STRING)
    Kjonn kjonn

    @Enumerated(EnumType.STRING)
    IntervjuerStatus status

    @Enumerated(EnumType.STRING)
    IntervjuerArbeidsType arbeidsType

    String initialer
    Long intervjuerNummer
    String navn
    Date fodselsDato
    String gateAdresse
    String gateAdresse2
    String postNummer
    String postSted
    String kommuneNummer
    String epostPrivat
    String epostJobb
    String mobil
    String telefonHjem
    String telefonJobb
    Date ansattDato
    Long avtaltAntallTimer
    Date sluttDato
    Boolean pensjonistLonn = false
    Boolean lokal = false
    String kommentar
}
