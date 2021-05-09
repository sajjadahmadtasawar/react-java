package sivadmin.models;

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
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    @Size(max = 7)
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

    @OneToOne(cascade = CascadeType.ALL)
    private ProsjektLeder prosjektLeder;
}