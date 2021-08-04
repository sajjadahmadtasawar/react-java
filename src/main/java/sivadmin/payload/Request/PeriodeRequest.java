package sivadmin.payload.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PeriodeRequest {
    private String aar;
    private Long periodeNummer;
    private String periodeType;
    private Date oppstartDataInnsamling;
    private Date hentesTidligst;
    private Date planlagtSluttDato;
    private Date sluttDato;
    private String incentiver;
    private String kommentar;
    private Long delregisterNummer;

    private Long skjema_id;

}
