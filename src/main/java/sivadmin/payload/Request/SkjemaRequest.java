package sivadmin.payload.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sivadmin.models.Prosjekt;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SkjemaRequest {
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

    private Long prosjekt_id;
}
