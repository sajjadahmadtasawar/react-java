package sivadmin.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import sivadmin.models.audit.DateAudit;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "logg_linjer")
public class LoggLinje extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String linje;
    private boolean feil = false;
    private Date opprettet = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logg_id", nullable = false)
    @JsonBackReference
    private Logg logg;
}