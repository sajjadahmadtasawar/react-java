package no.ssb.sivadmin.models

import no.ssb.sivadmin.enums.ProsjektFinansiering
import no.ssb.sivadmin.enums.ProsjektModus
import no.ssb.sivadmin.enums.ProsjektStatus

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.OneToOne

@Entity
class Prosjekt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    @OneToOne(optional=false)
    @JoinColumn(name = "PROSJEKT_LEDER_ID", referencedColumnName = "ID")
    ProsjektLeder prosjektLeder

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "SKEJMA_ID", referencedColumnName = "ID")
    private List<Skjema> skjemaer

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "PROSJEKT_DELTAGER_ID", referencedColumnName = "ID")
    private List<ProsjektDeltager> prosjektDeltagere

    @Enumerated(EnumType.STRING)
    ProsjektFinansiering finansiering

    @Enumerated(EnumType.STRING)
    ProsjektStatus prosjektStatus

    @Enumerated(EnumType.STRING)
    ProsjektModus modus

    String prosjektNavn
    String produktNummer
    String aargang
    String registerNummer
    Long prosentStat
    Long prosentMarked
    Boolean panel
    Date oppstartDato
    Date avslutningsDato
    String kommentar
}
