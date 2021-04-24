package no.ssb.sivadmin.models

import no.ssb.sivadmin.enums.PeriodeType

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
class Periode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    @ManyToOne
    @JoinColumn(name = "PERIODE_ID", referencedColumnName = "ID")
    private Skjema skjema

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "INTERVJUE_OBJEKT_ID", referencedColumnName = "ID")
    private List<IntervjuObjekt> intervjuObjekter

    @Enumerated(EnumType.STRING)
    PeriodeType periodeType

    String aar
    Long periodeNummer
    Date oppstartDataInnsamling
    Date hentesTidligst
    Date planlagtSluttDato
    Date sluttDato
    String incentiver
    String kommentar
    Long delregisterNummer
}
