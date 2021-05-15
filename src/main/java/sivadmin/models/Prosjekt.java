package sivadmin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sivadmin.models.audit.DateAudit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

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

    public Prosjekt(@NotBlank @Size(max = 200) String prosjektNavn, @NotBlank @Size(max = 7) String produktNummer, String aargang, String registerNummer, String prosjektStatus, String modus, String finansiering, Long prosentStat, Long prosentMarked, Boolean panel, Date oppstartDato, Date avslutningsDato, String kommentar) {
        this.prosjektNavn = prosjektNavn;
        this.produktNummer = produktNummer;
        this.aargang = aargang;
        this.registerNummer = registerNummer;
        this.prosjektStatus = prosjektStatus;
        this.modus = modus;
        this.finansiering = finansiering;
        this.prosentStat = prosentStat;
        this.prosentMarked = prosentMarked;
        this.panel = panel;
        this.oppstartDato = oppstartDato;
        this.avslutningsDato = avslutningsDato;
        this.kommentar = kommentar;
    }
}