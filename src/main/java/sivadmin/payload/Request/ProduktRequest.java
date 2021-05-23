package sivadmin.payload.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProduktRequest {
    private String navn;
    private String beskrivelse;
    private String produktNummer;
    private String finansiering;
    private Long prosentStat;
    private Long prosentMarked;
}
