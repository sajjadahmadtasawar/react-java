package sivadmin.models;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import sivadmin.models.audit.DateAudit;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "brukere", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "brukernavn"
        }),
        @UniqueConstraint(columnNames = {
                "epost"
        })
})
public class Bruker extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String navn;

    @NotBlank
    @Size(max = 15)
    private String brukernavn;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    private String epost;

    @NotBlank
    @Size(max = 100)
    private String passord;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "bruker_roller",
            joinColumns = @JoinColumn(name = "bruker_id"),
            inverseJoinColumns = @JoinColumn(name = "rolle_id"))
    private Set<Rolle> roller = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bruker() {}

    public Bruker(String navn, String brukernavn, String epost, String passord) {
        this.navn = navn;
        this.brukernavn = brukernavn;
        this.epost = epost;
        this.passord = passord;
    }
}