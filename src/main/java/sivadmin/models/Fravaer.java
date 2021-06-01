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
@Table(name = "fravaer")
public class Fravaer extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fraTidspunkt;
    private Date tilTidspunkt;
    private String fravaerType;
    private Long prosent;
    private String kommentar;
    private String redigertAv;
    private Date redigertDato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "intervjuer_id", nullable = false)
    @JsonBackReference
    private Intervjuer intervjuer;
}