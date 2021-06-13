package sivadmin.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import sivadmin.models.audit.DateAudit;
import sivadmin.payload.Request.ProduktRequest;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "klynger")
public class Klynge extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 200)
    private String klyngeNavn;
    private String klyngeSjef;
    private String epost;
    private String beskrivelse;

    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "klynge")
    private Set<Intervjuer> intervjuere = new HashSet<>();

    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "klynge")
    private Set<Kommune> kommuner = new HashSet<>();

    public Klynge(@NotBlank @Size(max = 200) String klyngeNavn, String klyngeSjef, String epost, String beskrivelse) {
        this.klyngeNavn = klyngeNavn;
        this.klyngeSjef = klyngeSjef;
        this.epost = epost;
        this.beskrivelse = beskrivelse;
    }
}