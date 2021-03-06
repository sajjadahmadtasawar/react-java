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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "intervjuer_historikk")
public class IntHist extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 200)
    private String navn;
    private String beskrivelse;
    private String produktNummer;
    private String finansiering;
    private Long prosentStat;
    private Long prosentMarked;

    public IntHist(ProduktRequest produktRequest) {
        this.navn = produktRequest.getNavn();
        this.beskrivelse = produktRequest.getBeskrivelse();
        this.produktNummer = produktRequest.getProduktNummer();
        this.finansiering = produktRequest.getFinansiering();
        this.prosentStat = produktRequest.getProsentStat();
        this.prosentMarked = produktRequest.getProsentMarked();
    }
}