package sivadmin.payload.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdresseRequest {
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

    private Long intervjue_objekt_id;

}
