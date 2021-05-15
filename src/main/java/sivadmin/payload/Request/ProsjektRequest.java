package sivadmin.payload.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sivadmin.models.ProsjektLeder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProsjektRequest {
    @NotBlank
    @Size(min = 4, max = 200)
    private String prosjektNavn;

    @NotBlank
    @Size(max = 5)
    private String produktNummer;

    @NotBlank
    private String aargang;

    private String registerNummer;
    private String prosjektStatus;
    private String modus;
    private String finansiering;
    private Long prosentStat;
    private Long prosentMarked;
    private Boolean panel;
    private Date oppstartDato;

    @NotBlank
    private Date avslutningsDato;
    private String kommentar;

    private Long prosjektLeder;
}
