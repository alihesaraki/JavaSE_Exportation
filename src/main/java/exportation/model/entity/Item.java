package exportation.model.entity;

import com.google.gson.Gson;
import exportation.model.entity.enums.Brand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class Item {
    private int id;
    private String name;
    private Brand brand;
    private String model;
    private String dimensionOfUnite;
    private String dimensionOfPallet;
    private int palletCapacity;
    private long Hs_Code;
    private float cost;
    private float weightOfUnit;
    private float weightOfPallet;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
