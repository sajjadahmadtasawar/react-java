package sivadmin.payload.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrukerRequest {
    @NotBlank
    @Size(min = 4, max = 40)
    private String navn;

    @NotBlank
    @Size(min = 3, max = 15)
    private String brukernavn;

    @NotBlank
    @Size(max = 40)
    @Email
    private String epost;

    @NotBlank
    @Size(min = 6, max = 20)
    private String passord;
}
