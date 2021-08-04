package sivadmin.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sivadmin.models.audit.DateAudit;
import sivadmin.payload.Request.AdresseRequest;

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

    public Adresse(AdresseRequest adresseRequest) {
        this.adresseType = adresseRequest.getAdresseType();
        this.kommentar = adresseRequest.getKommentar();
        this.gyldigFom = adresseRequest.getGyldigFom();
        this.gyldigTom = adresseRequest.getGyldigTom();
        this.gateAdresse = adresseRequest.getGateAdresse();
        this.tilleggsAdresse = adresseRequest.getTilleggsAdresse();
        this.husBruksNummer = adresseRequest.getHusBruksNummer();
        this.underNummer = adresseRequest.getUnderNummer();
        this.bokstavFeste = adresseRequest.getBokstavFeste();
        this.boligNummer = adresseRequest.getBoligNummer();
        this.postNummer = adresseRequest.getPostNummer();
        this.postSted = adresseRequest.getPostSted();
        this.kommuneNummer = adresseRequest.getKommuneNummer();
        this.gateGaardNummer = adresseRequest.getGateGaardNummer();
        this.original = adresseRequest.getOriginal();
        this.redigertAv = adresseRequest.getRedigertAv();
        this.redigertDato = adresseRequest.getRedigertDato();
        this.gjeldende = adresseRequest.getGjeldende();
    }
}