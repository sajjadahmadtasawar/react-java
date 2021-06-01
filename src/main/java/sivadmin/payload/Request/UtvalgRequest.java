package sivadmin.payload.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UtvalgRequest {
    private Date importDato;
    private int antallFil;
    private int antallImportert;
    private String importertAv;
    private String melding;

    private Long skjema_id;
}
