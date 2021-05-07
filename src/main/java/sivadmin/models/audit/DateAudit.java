package sivadmin.models.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"opprettetDato", "oppdatertDato"},
        allowGetters = true
)
public abstract class DateAudit implements Serializable {

    @CreatedDate
    private Instant opprettetDato;

    @LastModifiedDate
    private Instant oppdatertDato;

    public Instant getOpprettetDato() {
        return opprettetDato;
    }

    public void setOpprettetDato(Instant opprettetDato) {
        this.opprettetDato = opprettetDato;
    }

    public Instant getOppdatertDato() {
        return oppdatertDato;
    }

    public void setOppdatertDato(Instant oppdatertDato) {
        this.oppdatertDato = oppdatertDato;
    }
}