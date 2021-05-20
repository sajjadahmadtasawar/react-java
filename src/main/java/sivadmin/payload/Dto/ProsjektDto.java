package sivadmin.payload.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sivadmin.models.ProsjektLeder;
import sivadmin.models.Skjema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProsjektDto {
    private Long id;
    private String prosjektNavn;
    private String produktNummer;
    private String aargang;
    private String registerNummer;
    private String prosjektStatus;
    private String modus;
    private String finansiering;
    private Long prosentStat;
    private Long prosentMarked;
    private Boolean panel;
    private Date oppstartDato;
    private Date avslutningsDato;
    private String kommentar;
    private ProsjektLeder prosjektLeder;
    private List<Skjema> skjemaer;
}
