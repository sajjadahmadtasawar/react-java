package sivadmin.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sivadmin.models.audit.DateAudit;
import sivadmin.payload.Request.SkjemaRequest;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "skjemaer")
public class Skjema extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 200)
    private String skjemaNavn;

    @NotBlank
    @Size(max = 50)
    private String skjemaKortNavn;

    @NotBlank
    @Size(max = 5)
    private String delProduktNummer;

    private String undersokelseType;
    private Boolean aktivertForIntervjuing;
    private Boolean intervjuTypeTelefon;
    private Boolean intervjuTypeBesok;
    private Boolean intervjuTypeWeb;
    private Boolean slettesEtterRetur;
    private Boolean intervjuTypePapir;
    private Date oppstartDataInnsamling;
    private Boolean klarTilGenerering;
    private Boolean klarTilUtsending;
    private String status;
    private Date planlagtSluttDato;
    private Date sluttDato;
    private Boolean overtid;
    private Long onsketSvarProsent;
    private Date dataUttaksDato;
    private Boolean hentAlleOppdrag;
    private Boolean kanSlettesLokalt;
    private String langtidsLagretAv;
    private Date langtidsLagretDato;
    private Long antallIntervjuObjekterLagret;
    private Long antallOppdragLagret;
    private Long intervjuVarighet;
    private Long adminTid;
    private Date regoverforingDato;
    private String regoverforingInitialer;
    private String regoverforingSeksjon;
    private Date ioBrevGodkjentDato;
    private String ioBrevGodkjentInitialer;
    private Date krypteringDato;
    private Date krypteringMailSendt;
    private Date anonymDato;
    private Date anonymMailSendt;
    private Date ryddDato;
    private Date ryddMailSendt;
    private Date papirMakuleringDato;
    private Date papirMakuleringMailSendt;
    private Long maxAntIntervjuObjekterKontakt;
    private Long malVersjon;
    private String kommentar;
    private String instrumentId;
    private Boolean altIBlaise5;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prosjekt_id", nullable = false)
    @JsonBackReference
    private Prosjekt prosjekt;

    public Skjema(SkjemaRequest skjemaRequest) {
        this.skjemaNavn = skjemaRequest.getSkjemaNavn();
        this.skjemaKortNavn = skjemaRequest.getSkjemaKortNavn();
        this.delProduktNummer = skjemaRequest.getDelProduktNummer();
        this.undersokelseType = skjemaRequest.getUndersokelseType();
        this.aktivertForIntervjuing = skjemaRequest.getAktivertForIntervjuing();
        this.intervjuTypeTelefon = skjemaRequest.getIntervjuTypeTelefon();
        this.intervjuTypeBesok = skjemaRequest.getIntervjuTypeBesok();
        this.intervjuTypeWeb = skjemaRequest.getIntervjuTypeWeb();
        this.slettesEtterRetur = skjemaRequest.getSlettesEtterRetur();
        this.intervjuTypePapir = skjemaRequest.getIntervjuTypePapir();
        this.oppstartDataInnsamling = skjemaRequest.getOppstartDataInnsamling();
        this.klarTilGenerering = skjemaRequest.getKlarTilGenerering();
        this.klarTilUtsending = skjemaRequest.getKlarTilUtsending();
        this.status = skjemaRequest.getStatus();
        this.planlagtSluttDato = skjemaRequest.getPlanlagtSluttDato();
        this.sluttDato = skjemaRequest.getSluttDato();
        this.overtid = skjemaRequest.getOvertid();
        this.onsketSvarProsent = skjemaRequest.getOnsketSvarProsent();
        this.dataUttaksDato = skjemaRequest.getDataUttaksDato();
        this.hentAlleOppdrag = skjemaRequest.getHentAlleOppdrag();
        this.kanSlettesLokalt = skjemaRequest.getKanSlettesLokalt();
        this.langtidsLagretAv = skjemaRequest.getLangtidsLagretAv();
        this.langtidsLagretDato = skjemaRequest.getLangtidsLagretDato();
        this.antallIntervjuObjekterLagret = skjemaRequest.getAntallIntervjuObjekterLagret();
        this.antallOppdragLagret = skjemaRequest.getAntallOppdragLagret();
        this.intervjuVarighet = skjemaRequest.getIntervjuVarighet();
        this.adminTid = skjemaRequest.getAdminTid();
        this.regoverforingDato = skjemaRequest.getRegoverforingDato();
        this.regoverforingInitialer = skjemaRequest.getRegoverforingInitialer();
        this.regoverforingSeksjon = skjemaRequest.getRegoverforingSeksjon();
        this.ioBrevGodkjentDato = skjemaRequest.getIoBrevGodkjentDato();
        this.ioBrevGodkjentInitialer = skjemaRequest.getIoBrevGodkjentInitialer();
        this.krypteringDato = skjemaRequest.getKrypteringDato();
        this.krypteringMailSendt = skjemaRequest.getKrypteringMailSendt();
        this.anonymDato = skjemaRequest.getAnonymDato();
        this.anonymMailSendt = skjemaRequest.getAnonymMailSendt();
        this.ryddDato = skjemaRequest.getRyddDato();
        this.ryddMailSendt = skjemaRequest.getRyddMailSendt();
        this.papirMakuleringDato = skjemaRequest.getPapirMakuleringDato();
        this.papirMakuleringMailSendt = skjemaRequest.getPapirMakuleringMailSendt();
        this.maxAntIntervjuObjekterKontakt = skjemaRequest.getMaxAntIntervjuObjekterKontakt();
        this.malVersjon = skjemaRequest.getMalVersjon();
        this.kommentar = skjemaRequest.getKommentar();
        this.instrumentId = skjemaRequest.getInstrumentId();
        this.altIBlaise5 = skjemaRequest.getAltIBlaise5();
    }
}