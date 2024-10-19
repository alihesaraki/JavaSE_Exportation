package exportation.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class Transportation {
    private int id;
    private String direction;
    private double freight;
    private Item item;
    private Company company;
    private Country country;
    private LocalDate date;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
