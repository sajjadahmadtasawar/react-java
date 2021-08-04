package sivadmin.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import sivadmin.models.audit.DateAudit;
import sivadmin.payload.Request.ProduktRequest;
import sivadmin.payload.Request.ProsjektRequest;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produkter")
public class Produkt extends DateAudit {
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

    public Produkt(ProduktRequest produktRequest) {
        this.navn = produktRequest.getNavn();
        this.beskrivelse = produktRequest.getBeskrivelse();
        this.produktNummer = produktRequest.getProduktNummer();
        this.finansiering = produktRequest.getFinansiering();
        this.prosentStat = produktRequest.getProsentStat();
        this.prosentMarked = produktRequest.getProsentMarked();
    }

    public Produkt(@NotBlank @Size(max = 200) String navn, String produktNummer, String finansiering, Long prosentStat, Long prosentMarked) {
        this.navn = navn;
        this.produktNummer = produktNummer;
        this.finansiering = finansiering;
        this.prosentStat = prosentStat;
        this.prosentMarked = prosentMarked;
    }
}