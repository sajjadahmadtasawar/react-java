package no.ssb.sivadmin.models

import no.ssb.sivadmin.enums.UndersokelsesType

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
import javax.persistence.OneToOne

@Entity
class Skjema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    @ManyToOne
    @JoinColumn(name = "SKJEMA_ID", referencedColumnName = "ID")
    private Prosjekt projekt

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "PERIODE_ID", referencedColumnName = "ID")
    private List<Periode> perioder

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "SKJEMA_VERSJON_ID", referencedColumnName = "ID")
    private List<SkjemaVersjon> skjemaVersjoner

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "OPPLARING_ID", referencedColumnName = "ID")
    private List<Opplaring> opplaringer

    @Enumerated(EnumType.STRING)
    UndersokelsesType undersokelseType

    boolean aktivertForIntervjuing
    String delProduktNummer
    String skjemaNavn
    String skjemaKortNavn
    Boolean intervjuTypeBesok
    Boolean intervjuTypeTelefon
    Boolean intervjuTypePapir
    Boolean intervjuTypeWeb
    Boolean slettesEtterRetur
    Date oppstartDataInnsamling
    Boolean klarTilGenerering
    Boolean klarTilUtsending
    String status
    Date planlagtSluttDato
    Date sluttDato
    Boolean overtid
    Long onsketSvarProsent
    Date dataUttaksDato
    Boolean hentAlleOppdrag
    Boolean kanSlettesLokalt
    String langtidsLagretAv
    Date langtidsLagretDato
    Long antallIntervjuObjekterLagret
    Long antallOppdragLagret
    Long intervjuVarighet
    Long adminTid
    Date regoverforingDato
    String regoverforingInitialer
    String regoverforingSeksjon
    Date ioBrevGodkjentDato
    String ioBrevGodkjentInitialer
    Date krypteringDato
    Date krypteringMailSendt
    Date anonymDato
    Date anonymMailSendt
    Date ryddDato
    Date ryddMailSendt
    Date papirMakuleringDato
    Date papirMakuleringMailSendt
    Long maxAntIntervjuObjekterKontakt
    Long malVersjon
    String kommentar
    String instrumentId
    Boolean altIBlaise5
}
