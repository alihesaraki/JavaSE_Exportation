package exportation.model.da;

import exportation.model.entity.User;
import lombok.extern.log4j.Log4j;
import exportation.model.tools.CRUD;
import exportation.model.tools.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class UserDa implements AutoCloseable, CRUD<User> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public UserDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    //save
    @Override
    public User save(User user) throws Exception {
        user.setId(ConnectionProvider.getConnectionProvider().getNextId("USER_SEQ"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO USER_TABLE (USER_ID,USERNAME,USER_PASSWORD,USER_ENABLED) VALUES (?,?,?,?)"
        );
        preparedStatement.setInt(1, user.getId());
        preparedStatement.setString(2, user.getUsername());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setBoolean(4, user.isEnabled());
        preparedStatement.execute();
        return user;
    }

    //Edit
    @Override
    public User edit(User user) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE USER_TABLE SET USERNAME=?,USER_PASSWORD=?,USER_ENABLED=? WHERE USER_ID=? "
        );
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setBoolean(3, user.isEnabled());
        preparedStatement.setInt(4, user.getId());
        preparedStatement.execute();
        return user;
    }

    //Remove
    @Override
    public User remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM USER_TABLE WHERE USER_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //FindALl
    @Override
    public List<User> findAll() throws Exception {
        List<User> userList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM USER_TABLE ORDER BY USER_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            User user = User
                    .builder()
                    .id(resultSet.getInt("USER_ID"))
                    .username(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("USER_PASSWORD"))
                    .enabled(resultSet.getBoolean("USER_ENABLED"))
                    .build();

            userList.add(user);
        }

        return userList;
    }

    //FindById
    @Override
    public User findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM USER_TABLE WHERE USER_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;
        if (resultSet.next()) {
            user = User
                    .builder()
                    .id(resultSet.getInt("USER_ID"))
                    .username(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("USER_PASSWORD"))
                    .enabled(resultSet.getBoolean("USER_ENABLED"))
                    .build();
        }
        return user;
    }

    //FindByUsername
    public User findByUsername(String username) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM USER_TABLE WHERE USERNAME=?");
        preparedStatement.setString(1, username + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;
        while (resultSet.next()) {
            user = User
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .username(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("USER_PASSWORD"))
                    .enabled(resultSet.getBoolean("USER_ENABLED"))
                    .build();
        }
        return user;
    }

    //Close
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
