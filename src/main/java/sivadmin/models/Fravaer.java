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
@Table(name = "fravaerer")
public class Fravaer extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fraTidspunkt;
    private Date tilTidspunkt;
    private String fravaerType;
    private Long prosent;
    private String kommentar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "intervjuer_id", nullable = false)
    @JsonBackReference
    private Intervjuer intervjuer;

    public Fravaer(Date fraTidspunkt, Date tilTidspunkt, String fravaerType, Long prosent, String kommentar, Intervjuer intervjuer) {
        this.fraTidspunkt = fraTidspunkt;
        this.tilTidspunkt = tilTidspunkt;
        this.fravaerType = fravaerType;
        this.prosent = prosent;
        this.kommentar = kommentar;
        this.intervjuer = intervjuer;
    }
}