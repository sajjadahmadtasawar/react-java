package sivadmin.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMedSider<T> {
    private List<T> innhold;
    private int side;
    private int storrelse;
    private long totallElementer;
    private int totallSider;
    private boolean sisteSide;
}
