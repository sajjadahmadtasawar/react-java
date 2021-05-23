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
public class ProsjektDeltagerRequest {
    private String deltagerNavn;
    private String deltagerEpost;
    private String deltagerInitialer;

    private Long prosjekt_id;
}
