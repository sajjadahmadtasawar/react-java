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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "kommuner")
public class Kommune extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String kommuneNummer;
    private String kommuneNavn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "klynge_id", nullable = false)
    @JsonBackReference
    private Klynge klynge;

    public Kommune(String kommuneNummer, String kommuneNavn, Klynge klynge) {
        this.kommuneNummer = kommuneNummer;
        this.kommuneNavn = kommuneNavn;
        this.klynge = klynge;
    }
}