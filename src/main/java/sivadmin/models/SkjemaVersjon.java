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
@Table(name = "skjema_versjoner")
public class SkjemaVersjon extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long versjonsNummer;

    private Date gyldigFom;
    private Date gyldigTom;
    private Date skjemaGodkjentDato;
    private String skjemaGodkjentInitialer;
    private Date webskjemaGodkjentDato;
    private String webskjemaGodkjentInitialer;
    private String kommentar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skjema_id", nullable = false)
    @JsonBackReference
    private Skjema skjema;

}