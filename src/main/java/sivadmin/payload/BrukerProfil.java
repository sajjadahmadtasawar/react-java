package sivadmin.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrukerProfil {
    private Long id;
    private String brukernavn;
    private String navn;
    private Instant joinedAt;
    public Instant getJoinedAt() {
        return joinedAt;
    }
}
