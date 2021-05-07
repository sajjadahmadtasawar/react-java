package sivadmin.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrukerDetaljer {
    private Long id;
    private String brukernavn;
    private String navn;
}
