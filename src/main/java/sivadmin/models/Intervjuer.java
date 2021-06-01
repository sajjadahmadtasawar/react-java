package sivadmin.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import sivadmin.models.audit.DateAudit;
import sivadmin.payload.Request.ProduktRequest;

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
@Table(name = "intervjuere")
public class Intervjuer extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String initialer;
    private Long intervjuerNummer;
    private String navn;
    private String kjonn;
    private Date fodselsDato;
    private String gateAdresse;
    private String gateAdresse2;
    private String postNummer;
    private String postSted;
    private String kommuneNummer;
    private String epostPrivat;
    private String epostJobb;
    private String mobil;
    private String telefonHjem;
    private String telefonJobb;
    private String status;
    private Date ansattDato;
    private Long avtaltAntallTimer;
    private String arbeidsType;
    private Date sluttDato;
    private Boolean pensjonistLonn = false;
    private Boolean lokal = false;
    private String kommentar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "klynge_id", nullable = false)
    @JsonBackReference
    private Klynge klynge;

    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "intervjuer")
    private Set<Fravaer> fravaer = new HashSet<>();
}