package sivadmin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import sivadmin.models.audit.DateAudit;
import sivadmin.payload.Request.ProsjektRequest;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prosjekter")
public class Prosjekt extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 200)
    private String prosjektNavn;

    @NotBlank
    @Size(max = 5)
    private String produktNummer;

    private String aargang;
    private String registerNummer;
    private String prosjektStatus;
    private String modus;
    private String finansiering;
    private Long prosentStat;
    private Long prosentMarked;
    private Boolean panel;
    private Date oppstartDato;
    private Date avslutningsDato;
    private String kommentar;

    @OneToOne(fetch = FetchType.EAGER)
    private ProsjektLeder prosjektLeder;

    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "prosjekt")
    private Set<Skjema> skjemaer = new HashSet<>();

    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "prosjekt")
    private Set<ProsjektDeltager> prosjektDeltagere = new HashSet<>();

    public Prosjekt(ProsjektRequest prosjektRequest) {
        this.prosjektNavn = prosjektRequest.getProsjektNavn();
        this.produktNummer = prosjektRequest.getProduktNummer();
        this.aargang = prosjektRequest.getAargang();
        this.registerNummer = prosjektRequest.getRegisterNummer();
        this.prosjektStatus = prosjektRequest.getProsjektStatus();
        this.modus = prosjektRequest.getModus();
        this.finansiering = prosjektRequest.getFinansiering();
        this.prosentStat = prosjektRequest.getProsentStat();
        this.prosentMarked = prosjektRequest.getProsentMarked();
        this.panel = prosjektRequest.getPanel();
        this.oppstartDato = prosjektRequest.getOppstartDato();
        this.avslutningsDato = prosjektRequest.getAvslutningsDato();
        this.kommentar = prosjektRequest.getKommentar();
    }
}