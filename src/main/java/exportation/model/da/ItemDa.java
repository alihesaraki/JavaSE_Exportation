package exportation.model.da;

import exportation.model.entity.Item;
import exportation.model.entity.enums.Brand;
import lombok.extern.log4j.Log4j;
import exportation.model.tools.CRUD;
import exportation.model.tools.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class ItemDa implements AutoCloseable, CRUD<Item> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public ItemDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    //save
    @Override
    public Item save(Item item) throws Exception {
        item.setId(ConnectionProvider.getConnectionProvider().getNextId("ITEM_SEQ"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO ITEM_TABLE (ITEM_ID, ITEM_NAME, ITEM_BRAND, ITEM_MODEL,DIMENSION_OF_UNIT,DIMENSION_OF_PALLET,PALLET_CAPACITY,ITEM_COST,ITEM_HS_CODE,WEIGHT_OF_UNIT,WEIGHT_OF_PALLET) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, item.getId());
        preparedStatement.setString(2, item.getName());
        preparedStatement.setString(3, item.getBrand().toString());
        preparedStatement.setString(4, item.getModel());
        preparedStatement.setString(5, item.getDimensionOfUnite());
        preparedStatement.setString(6, item.getDimensionOfPallet());
        preparedStatement.setInt(7, item.getPalletCapacity());
        preparedStatement.setDouble(8, item.getCost());
        preparedStatement.setLong(9, item.getHs_Code());
        preparedStatement.setFloat(10, item.getWeightOfUnit());
        preparedStatement.setFloat(11, item.getWeightOfPallet());
        preparedStatement.execute();
        return item;
    }

    //Edit
    @Override
    public Item edit(Item item) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE ITEM_TABLE SET ITEM_NAME=?, ITEM_BRAND=?, ITEM_MODEL=?, DIMENSION_OF_UNIT=?,DIMENSION_OF_PALLET=?,PALLET_CAPACITY=?,ITEM_COST=? ,ITEM_HS_CODE=?,WEIGHT_OF_UNIT=?,WEIGHT_OF_PALLET=? WHERE ITEM_ID=?"
        );
        preparedStatement.setString(1, item.getName());
        preparedStatement.setString(2, item.getBrand().toString());
        preparedStatement.setString(3, item.getModel());
        preparedStatement.setString(4, item.getDimensionOfUnite());
        preparedStatement.setString(5, item.getDimensionOfPallet());
        preparedStatement.setInt(6, item.getPalletCapacity());
        preparedStatement.setDouble(7, item.getCost());
        preparedStatement.setLong(8, item.getHs_Code());
        preparedStatement.setFloat(9, item.getWeightOfUnit());
        preparedStatement.setFloat(10, item.getWeightOfPallet());
        preparedStatement.setInt(11, item.getId());
        preparedStatement.execute();
        return item;
    }

    //Remove
    @Override
    public Item remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM ITEM_TABLE WHERE ITEM_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //FindALl
    @Override
    public List<Item> findAll() throws Exception {
        List<Item> itemList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM ITEM_TABLE ORDER BY ITEM_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Item item = Item
                    .builder()
                    .id(resultSet.getInt("ITEM_ID"))
                    .name(resultSet.getString("ITEM_NAME"))
                    .brand(resultSet.getObject("ITEM_BRAND", Brand.class))
                    .model(resultSet.getString("ITEM_MODEL"))
                    .dimensionOfUnite(resultSet.getString("DIMENSION_OF_UNIT"))
                    .dimensionOfPallet(resultSet.getString("DIMENSION_OF_PALLET"))
                    .palletCapacity(resultSet.getInt("PALLET_CAPACITY"))
                    .Hs_Code(Long.parseLong(resultSet.getString("ITEM_HS_CODE")))
                    .cost(resultSet.getFloat("ITEM_COST"))
                    .weightOfUnit(resultSet.getFloat("WEIGHT_OF_UNIT"))
                    .weightOfPallet(resultSet.getFloat("WEIGHT_OF_PALLET"))
                    .build();

            itemList.add(item);
        }

        return itemList;
    }

    //FindById
    @Override
    public Item findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM ITEM_TABLE WHERE ITEM_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Item item = null;
        if (resultSet.next()) {
            item = Item
                    .builder()
                    .id(resultSet.getInt("ITEM_ID"))
                    .name(resultSet.getString("ITEM_NAME"))
                    .brand(resultSet.getObject("ITEM_BRAND", Brand.class))
                    .model(resultSet.getString("ITEM_MODEL"))
                    .dimensionOfUnite(resultSet.getString("DIMENSION_OF_UNIT"))
                    .dimensionOfPallet(resultSet.getString("DIMENSION_OF_PALLET"))
                    .palletCapacity(resultSet.getInt("PALLET_CAPACITY"))
                    .Hs_Code(Long.parseLong(resultSet.getString("ITEM_HS_CODE")))
                    .cost(resultSet.getFloat("ITEM_COST"))
                    .weightOfUnit(resultSet.getFloat("WEIGHT_OF_UNIT"))
                    .weightOfPallet(resultSet.getFloat("WEIGHT_OF_PALLET"))
                    .build();
        }
        return item;
    }

    //FindByModel
    public List<Item> findByModel(String model) throws Exception {
        List<Item> itemList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM ITEM_TABLE WHERE ITEM_MODEL LIKE? ORDER BY ITEM_ID");
        preparedStatement.setString(1, model + "%");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Item item = Item
                    .builder()
                    .id(resultSet.getInt("ITEM_ID"))
                    .name(resultSet.getString("ITEM_NAME"))
                    .brand(resultSet.getObject("ITEM_BRAND", Brand.class))
                    .model(resultSet.getString("ITEM_MODEL"))
                    .dimensionOfUnite(resultSet.getString("DIMENSION_OF_UNIT"))
                    .dimensionOfPallet(resultSet.getString("DIMENSION_OF_PALLET"))
                    .palletCapacity(resultSet.getInt("PALLET_CAPACITY"))
                    .Hs_Code(Long.parseLong(resultSet.getString("ITEM_HS_CODE")))
                    .cost(resultSet.getFloat("ITEM_COST"))
                    .weightOfUnit(resultSet.getFloat("WEIGHT_OF_UNIT"))
                    .weightOfPallet(resultSet.getFloat("WEIGHT_OF_PALLET"))
                    .build();

            itemList.add(item);
        }
        return itemList;
    }

    //Close
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
