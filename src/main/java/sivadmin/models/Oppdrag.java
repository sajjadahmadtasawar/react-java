package sivadmin.models;

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
@Table(name = "oppdrager")
public class Oppdrag extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer intervjuStatus;
    private String intervjuType;
    private Date intervjuStatusDato;
    private String intervjuStatusKommentar;
    private String intervjuObjektNummer;
    private String skjemaKortNavn;
    private Long periodeNummer;
    private Long oppdragsKilde;
    private Date hentesTidligst;
    private String intervjuerInitialer;
    private String intervjuStart;
    private Long bruktTid;
    private String returStatus;
    private String overfortLokaltTidspunkt;
    private String sisteSynkTidspunkt;
    private String tildelingsType;
    private boolean oppdragFullfort;
    private Boolean endringIntervjuObjekt;
    private Boolean endringAvtale;
    private Boolean endringKontakt;
    private Boolean endringData;
    private String endringTidspunkt;
    private boolean merketSlettHosIntervjuer;
    private boolean slettetHosIntervjuer;
    private boolean selvplukk;
    private String generertAv;
    private Date generertDato;
    private String operatorId;
    private Date gyldighetsDato;
    private Date kansellertDato;
    private String kansellertAv;
    private String kansellertKommentar;
    private boolean klarTilSynk = false;

    @OneToOne(fetch = FetchType.EAGER)
    private IntervjuObjekt intervjuObjekt;

    @OneToOne(fetch = FetchType.EAGER)
    private Intervjuer intervjuer;
}