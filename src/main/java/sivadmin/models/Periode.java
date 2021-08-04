package sivadmin.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import sivadmin.models.audit.DateAudit;
import sivadmin.payload.Request.PeriodeRequest;

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

    private String aar;
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

    public Periode(PeriodeRequest periodeRequest) {
        this.aar = periodeRequest.getAar();
        this.periodeNummer = periodeRequest.getPeriodeNummer();
        this.periodeType = periodeRequest.getPeriodeType();
        this.oppstartDataInnsamling = periodeRequest.getOppstartDataInnsamling();
        this.hentesTidligst = periodeRequest.getHentesTidligst();
        this.planlagtSluttDato = periodeRequest.getPlanlagtSluttDato();
        this.sluttDato = periodeRequest.getSluttDato();
        this.incentiver = periodeRequest.getIncentiver();
        this.kommentar = periodeRequest.getKommentar();
        this.delregisterNummer = periodeRequest.getDelregisterNummer();
    }
}