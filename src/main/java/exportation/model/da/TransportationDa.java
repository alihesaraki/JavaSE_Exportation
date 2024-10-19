package exportation.model.da;

import exportation.model.entity.*;
import exportation.model.tools.CRUD;
import exportation.model.tools.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransportationDa implements AutoCloseable, CRUD<Transportation> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public TransportationDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    //save
    @Override
    public Transportation save(Transportation transportation) throws Exception {
        transportation.setId(ConnectionProvider.getConnectionProvider().getNextId("TRANSPORTATION_SEQ"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO TRANSPORTATION_TABLE (TRANSPORTATION_ID,TRANSPORTATION_DIRECTION,TRANSPORTATION_FREIGHT, ITEM_ID, COMPANY_ID,COUNTRY_ID,TRANSPORTATION_DATE ) VALUES (?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, transportation.getId());
        preparedStatement.setString(2, transportation.getDirection());
        preparedStatement.setDouble(3, transportation.getFreight());
        preparedStatement.setInt(4, transportation.getItem().getId());
        preparedStatement.setString(5, transportation.getCompany().getName());
        preparedStatement.setInt(6, transportation.getCountry().getId());
        preparedStatement.setDate(7, Date.valueOf(transportation.getDate()));
        preparedStatement.execute();
        return transportation;
    }

    //Edit
    @Override
    public Transportation edit(Transportation transportation) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE TRANSPORTATION_TABLE SET TRANSPORTATION_DIRECTION=?, TRANSPORTATION_FREIGHT=?,ITEM_ID=?, COMPANY_ID=?,COUNTRY_ID=?, TRANSPORTATION_DATE=? WHERE TRANSPORTATION_ID=? "
        );
        preparedStatement.setString(1, transportation.getDirection());
        preparedStatement.setDouble(2, transportation.getFreight());
        preparedStatement.setInt(3, transportation.getItem().getId());
        preparedStatement.setInt(4, transportation.getItem().getId());
        preparedStatement.setInt(5, transportation.getCountry().getId());
        preparedStatement.setDate(6, Date.valueOf(transportation.getDate()));
        preparedStatement.setInt(7, transportation.getId());
        preparedStatement.execute();
        return transportation;
    }

    //Remove
    @Override
    public Transportation remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM TRANSPORTATION_TABLE WHERE TRANSPORTATION_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //FindALl
    @Override
    public List<Transportation> findAll() throws Exception {
        List<Transportation> transportationList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM TRANSPORTATION_TABLE ORDER BY TRANSPORTATION_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Transportation transportation = Transportation
                    .builder()
                    .id(resultSet.getInt("TRANSPORTATION_ID"))
                    .direction(resultSet.getString("TRANSPORTATION_DIRECTION"))
                    .freight(resultSet.getFloat("TRANSPORTATION_FREIGHT"))
                    .item(Item.builder().id(resultSet.getInt("ITEM_ID")).build())
                    .company(Company.builder().name(resultSet.getString("COMPANY_ID")).build())
                    .country(Country.builder().id(resultSet.getInt("COUNTRY_ID")).build())
                    .date(resultSet.getDate("TRANSPORTATION_DATE").toLocalDate())
                    .build();

            transportationList.add(transportation);
        }

        return transportationList;
    }

    //FindById
    @Override
    public Transportation findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM TRANSPORTATION_TABLE WHERE TRANSPORTATION_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Transportation transportation = null;
        if (resultSet.next()) {
            transportation = Transportation
                    .builder()
                    .id(resultSet.getInt("TRANSPORTATION_ID"))
                    .direction(resultSet.getString("TRANSPORTATION_DIRECTION"))
                    .freight(resultSet.getFloat("TRANSPORTATION_FREIGHT"))
                    .item(Item.builder().id(resultSet.getInt("ITEM_ID")).build())
                    .company(Company.builder().name(resultSet.getString("COMPANY_ID")).build())
                    .country(Country.builder().id(resultSet.getInt("COUNTRY_ID")).build())
                    .date(resultSet.getDate("TRANSPORTATION_DATE").toLocalDate())
                    .build();
        }
        return transportation;
    }

    //Close
    @Override
    public void close() throws Exception {

    }
}
