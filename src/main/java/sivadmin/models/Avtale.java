package sivadmin.models;

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
@Table(name = "avtaler")
public class Avtale extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String avtaleType;
    private Date dateStart;
    private String timeStart;
    private Date dateEnd;
    private String timeEnd;
    private String weekDays;
    private String whoMade;
    private String avtaleMelding;

    @OneToOne(fetch = FetchType.EAGER)
    private IntervjuObjekt intervjuObjekt;
}