package exportation.model.da;

import exportation.model.entity.Person;
import exportation.model.entity.User;
import exportation.model.entity.enums.Gender;
import lombok.extern.log4j.Log4j;
import exportation.model.tools.CRUD;
import exportation.model.tools.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class PersonDa implements AutoCloseable, CRUD<Person> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public PersonDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    //save
    @Override
    public Person save(Person person) throws Exception {
        person.setId(ConnectionProvider.getConnectionProvider().getNextId("PERSON_SEQ"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO PERSON_TABLE (PERSON_ID,PERSON_NAME,PERSON_FAMILY,PERSON_GENDER,NATIONAL_ID,PERSON_PHONE_NUMBER,PERSON_EMAIL,PERSON_ADDRESS,PERSON_POSITION) VALUES (?,?,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, person.getId());
        preparedStatement.setString(2, person.getName());
        preparedStatement.setString(3, person.getFamily());
        preparedStatement.setString(4, person.getGender().toString());
        preparedStatement.setString(5, person.getNationalId());
        preparedStatement.setString(6, person.getPhoneNumber());
        preparedStatement.setString(7, person.getEmail());
        preparedStatement.setString(8, person.getAddress());
        preparedStatement.setString(9, person.getPosition());
//        preparedStatement.setInt(10, person.getUser()==null?null:person.getUser().getId());
        preparedStatement.execute();
        return person;
    }

    //Edit
    @Override
    public Person edit(Person person) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE PERSON_TABLE SET PERSON_NAME=?, PERSON_FAMILY=?, PERSON_GENDER=?, NATIONAL_ID=?, PERSON_PHONE_NUMBER=?,PERSON_EMAIL=?,PERSON_ADDRESS=?,PERSON_POSITION=? WHERE PERSON_ID=?"
        );
        preparedStatement.setString(1, person.getName());
        preparedStatement.setString(2, person.getFamily());
        preparedStatement.setString(3, person.getGender().toString());
        preparedStatement.setString(4, person.getNationalId());
        preparedStatement.setString(5, person.getPhoneNumber());
        preparedStatement.setString(6, person.getEmail());
        preparedStatement.setString(7, person.getAddress());
        preparedStatement.setString(8, person.getPosition());
//        preparedStatement.setInt(9, person.getUser().getId());
        preparedStatement.setInt(9, person.getId());
        preparedStatement.execute();
        return person;
    }

    //Remove
    @Override
    public Person remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM PERSON_TABLE WHERE PERSON_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //FindALl
    @Override
    public List<Person> findAll() throws Exception {
        List<Person> personList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM PERSON_TABLE ORDER BY PERSON_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Person person = Person
                    .builder()
                    .id(resultSet.getInt("PERSON_ID"))
                    .name(resultSet.getString("PERSON_NAME"))
                    .family(resultSet.getString("PERSON_FAMILY"))
                    .gender(Gender.valueOf(resultSet.getString("PERSON_GENDER")))
                    .nationalId(resultSet.getString("NATIONAL_ID"))
                    .phoneNumber(resultSet.getString("PERSON_PHONE_NUMBER"))
                    .email(resultSet.getString("PERSON_EMAIL"))
                    .address(resultSet.getString("PERSON_ADDRESS"))
                    .position(resultSet.getString("PERSON_POSITION"))
//                    .user(User.builder().id(resultSet.getInt("USER_ID")).build())
                    .build();

            personList.add(person);
        }

        return personList;
    }

    //FindById
    @Override
    public Person findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM PERSON_TABLE WHERE PERSON_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Person person = null;
        if (resultSet.next()) {
            person = Person
                    .builder()
                    .id(resultSet.getInt("PERSON_ID"))
                    .name(resultSet.getString("PERSON_NAME"))
                    .family(resultSet.getString("PERSON_FAMILY"))
                    .gender(Gender.valueOf(resultSet.getString("PERSON_GENDER")))
                    .nationalId(resultSet.getString("NATIONAL_ID"))
                    .phoneNumber(resultSet.getString("PERSON_PHONE_NUMBER"))
                    .email(resultSet.getString("PERSON_EMAIL"))
                    .address(resultSet.getString("PERSON_ADDRESS"))
                    .position(resultSet.getString("PERSON_POSITION"))
//                    .user(User.builder().id(resultSet.getInt("USER_ID")).build())
                    .build();
        }
        return person;
    }

    //FindByFamily
    public List<Person> findByFamily(String family) throws Exception {
        List<Person> personList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM PERSON_TABLE WHERE PERSON_FAMILY LIKE? ORDER BY PERSON_ID");
        preparedStatement.setString(1, family + "%");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Person person = Person
                    .builder()
                    .id(resultSet.getInt("PERSON_ID"))
                    .name(resultSet.getString("PERSON_NAME"))
                    .family(resultSet.getString("PERSON_FAMILY"))
                    .gender(Gender.valueOf(resultSet.getString("PERSON_GENDER")))
                    .nationalId(resultSet.getString("NATIONAL_ID"))
                    .phoneNumber(resultSet.getString("PERSON_PHONE_NUMBER"))
                    .email(resultSet.getString("PERSON_EMAIL"))
                    .address(resultSet.getString("PERSON_ADDRESS"))
                    .position(resultSet.getString("PERSON_POSITION"))
//                    .user(User.builder().id(resultSet.getInt("USER_ID")).build())
                    .build();
            personList.add(person);
        }
        return personList;
    }



    //Close
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }



}
