package no.ssb.sivadmin.models

import no.ssb.sivadmin.enums.SkjemaStatus

import javax.persistence.*

@Entity
class StatHist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    @ManyToOne
    @JoinColumn(name = "STAT_HIST_ID", referencedColumnName = "ID")
    IntervjuObjekt intervjuObjekt

    @Enumerated(EnumType.STRING)
    SkjemaStatus skjemaStatus

    Integer intervjuStatus
    String redigertAv
    Date dato
}
