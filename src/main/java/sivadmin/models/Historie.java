package sivadmin.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sivadmin.models.audit.DateAudit;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "historier")
public class Historie extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resultat;
    private Integer historieNummer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "intervjue_objekt_id", nullable = false)
    @JsonBackReference
    private IntervjuObjekt intervjuObjekt;
}