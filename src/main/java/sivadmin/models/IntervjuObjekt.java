package sivadmin.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sivadmin.models.audit.DateAudit;
import sivadmin.payload.Request.ProduktRequest;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "intervjue_objekter")
public class IntervjuObjekt extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String katSkjemaStatus;
    private String kilde;
    private String intervjuObjektNummer;
    private String kontaktPersonFodselsNummer;
    private String delutvalg;
    private String navn;

    private String sivilstand;
    private String familienummer;
    private String statsborgerskap;
    private String personKode;
    private String referansePerson;
    private String organisasjonsNummer;
    private String kontaktperiode;
    private String meldingTilIntervjuer;
    private String statusKommentar;
    private Boolean onskerKontaktMedProsjektleder;
    private Boolean onskerResultatEpost;
    private Boolean harLestBrev;

    private Long fullforingsGrad;
    private Long fullforingsStatus;
    private Integer intervjuStatus;
    private String internStatus;

    private Integer dayBatchKode;

    private Boolean nekterBrevSendt;
    private Date postaltTilleggMottatt;
    private Long antallTilleggMottatt;
    private Boolean ferdig;
    private Date ferdigDato;
    private Date paVentDato;
    private String fodselsNummer;
    private String kjonn;

    private Boolean parkert;
    private Date parkertDato;
    private String kommentar;

    private Long alder;
    private String utvalgsOmraade;
    private String utvalgAvtaleDato;

    private String overIoNummer;

    private String epost;
    private Boolean reservasjon;

    private String passordWeb;

    private String redigertAv;
    private Date redigertDato;

    private String laastAv;
    private Date laastTidspunkt;
    private String maalform;
    private String varslingsstatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "periode_id", nullable = false)
    @JsonBackReference
    private Periode periode;

    @OneToOne(fetch = FetchType.EAGER)
    private Intervjuer intervjuer;

    @OneToOne(fetch = FetchType.EAGER)
    private Avtale avtale;

    @OneToOne(fetch = FetchType.EAGER)
    private UtvalgImport utvalgImport;
}