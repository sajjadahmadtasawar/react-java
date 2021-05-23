package sivadmin.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sivadmin.models.audit.DateAudit;
import sivadmin.payload.Request.ProsjektDeltagerRequest;
import sivadmin.payload.Request.SkjemaRequest;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prosjekt_deltagere")
public class ProsjektDeltager extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deltagerNavn;
    private String deltagerEpost;
    private String deltagerInitialer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prosjekt_id", nullable = false)
    @JsonBackReference
    private Prosjekt prosjekt;

    public ProsjektDeltager(ProsjektDeltagerRequest prosjektDeltagerRequest) {
        this.deltagerNavn = prosjektDeltagerRequest.getDeltagerNavn();
        this.deltagerEpost = prosjektDeltagerRequest.getDeltagerEpost();
        this.deltagerInitialer = prosjektDeltagerRequest.getDeltagerInitialer();
    }
}