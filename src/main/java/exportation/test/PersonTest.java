package exportation.test;

import exportation.model.bl.PersonBl;
import exportation.model.bl.UserBl;
import exportation.model.entity.Person;
import exportation.model.entity.User;
import exportation.model.entity.enums.Gender;

public class PersonTest {
    public static void main(String[] args) throws Exception {


        User user = User.builder()
                .username("ahmad")
                .password("ahmad123")
                .enabled(true)
                .build();


        Person person = Person.builder()
                .name("ali")
                .family("hesaraki")
                .email("ali@hesaraki@gmail.com")
                .phoneNumber("+911234567")
                .nationalId("001906499")
                .position("BOSS")
                .address("Tehran,Hesarak,p36")
                .gender(Gender.male)
                .user(user)
                .build();

        UserBl.getUserBl().save(user);
        System.out.println(user);

        PersonBl.getPersonBl().save(person);
        System.out.println(person);

    }
}