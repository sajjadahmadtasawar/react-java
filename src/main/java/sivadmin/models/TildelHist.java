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
@Table(name = "tildelt_historikk")
public class TildelHist extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dato;

    @OneToOne(fetch = FetchType.EAGER)
    private Intervjuer intervjuer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "intervjue_objekt_id", nullable = false)
    @JsonBackReference
    private IntervjuObjekt intervjuObjekt;
}