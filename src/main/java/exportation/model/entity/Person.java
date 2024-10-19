package exportation.model.entity;

import com.google.gson.Gson;
import exportation.model.entity.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class Person {
    private int id;
    private String name;
    private String family;
    private String email;
    private String phoneNumber;
    private String nationalId;
    private String position;
    private String address;
    private Gender gender;
    private User user;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
