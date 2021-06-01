package sivadmin.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sivadmin.models.audit.DateAudit;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "telefoner")
public class Telefon extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String telefonNummer;
    private String telefonType;
    private String kommentar;
    private String kilde;
    private Boolean resepsjon;
    private Boolean original;
    private Boolean gjeldende;
    private String redigertAv;
    private Date redigertDato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "intervjue_objekt_id", nullable = false)
    @JsonBackReference
    private IntervjuObjekt intervjuObjekt;
}