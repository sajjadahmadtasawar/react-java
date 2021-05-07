package sivadmin.models.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@JsonIgnoreProperties(
        value = {"opprettetAv", "oppdatertAv"},
        allowGetters = true
)
public abstract class UserDateAudit extends DateAudit {

    @CreatedBy
    private Long opprettetAv;

    @LastModifiedBy
    private Long oppdatertAv;

    public Long getOpprettetAv() {
        return opprettetAv;
    }

    public void setOpprettetAv(Long opprettetAv) {
        this.opprettetAv = opprettetAv;
    }

    public Long getOppdatertAv() {
        return oppdatertAv;
    }

    public void setOppdatertAv(Long oppdatertAv) {
        this.oppdatertAv = oppdatertAv;
    }
}