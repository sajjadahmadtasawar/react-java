package sivadmin.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import sivadmin.models.audit.DateAudit;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "perioder")
public class Periode extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long periodeNummer;
    private String periodeType;
    private Date oppstartDataInnsamling;
    private Date hentesTidligst;
    private Date planlagtSluttDato;
    private Date sluttDato;
    private String incentiver;
    private String kommentar;
    private Long delregisterNummer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skjema_id", nullable = false)
    @JsonBackReference
    private Skjema skjema;

    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "periode")
    private Set<IntervjuObjekt> intervjuObjekter = new HashSet<>();
}