package exportation.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ExportTracing {
    private int id;
    private boolean loadingStatus;
    private boolean prePayment;
    private boolean checkout;
    private Transportation transportation;
    private Trade trade;
    private LocalDate date;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
