package sivadmin.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "adresser")
public class Adresse extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String adresseType;
    private String kommentar;
    private Date gyldigFom;
    private Date gyldigTom;
    private String gateAdresse;
    private String tilleggsAdresse;
    private String husBruksNummer;
    private String underNummer;
    private String bokstavFeste;
    private String boligNummer;
    private String postNummer;
    private String postSted;
    private String kommuneNummer;
    private String gateGaardNummer;
    private Boolean original;
    private String redigertAv;
    private Date redigertDato;
    private Boolean gjeldende;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "intervjue_objekt_id", nullable = false)
    @JsonBackReference
    private IntervjuObjekt intervjuObjekt;
}