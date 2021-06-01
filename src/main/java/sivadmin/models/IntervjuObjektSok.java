package sivadmin.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sivadmin.models.audit.DateAudit;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "intervjue_objekt_sok")
public class IntervjuObjektSok extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Verdier ved lagring
    private String sokeNavn;
    private Date lagret;
    private String lagretAv;

    // Verdier i skjema
    private String navn;
    private String intervjuObjektNummer;
    private Long intervjuObjektId;
    private String skjemaStatus;
    private String avtaleType;
    private String kilde;
    private String resultatStatus;
    private Long skjema;

    private Long assosiertSkjema;
    private String intervjuStatusAssSkj;
    private Boolean intervjuStatusBlankAssSkj;
    private String kontaktperiodeAssSkj;

    private String periodeNummer;
    private String adresse;
    private String initialer;
    private String fodselsNummer;
    private String familienummer;
    private String kjonn;
    private Long alderFra;
    private Long alderTil;
    private Long fullforingMin;
    private Long fullforingMax;
    private String fullforingsStatus;

    private Boolean typeBesok;
    private String aargang;
    private String intervjuStatus;

    private Boolean intervjuStatusBlank;

    private String postSted;
    private String postNummer;
    private String kommuneNummer;
    private String boligNummer;
    private String telefonNummer;
    private String epost;
    private String husBruk;
    private Long klynge;
    private String skjemaKortNavn;
    private String kontaktperiode;

    private String delutvalg;

    private Date  intStatDatoIntervallFra;
    private Date  intStatDatoIntervallTil;
    private Boolean persisterSokeResultat;
    private String intervjuObjektSearchResult;
    private Long meldingsheaderMalId;

    private String maalform;
}