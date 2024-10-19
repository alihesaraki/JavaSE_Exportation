package exportation.model.da;

import exportation.model.entity.*;
import lombok.extern.log4j.Log4j;
import exportation.model.tools.CRUD;
import exportation.model.tools.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class PaymentDa implements AutoCloseable, CRUD<Payment> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public PaymentDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    //save
    @Override
    public Payment save(Payment payment) throws Exception {
        payment.setId(ConnectionProvider.getConnectionProvider().getNextId("PAYMENT_SEQ"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO PAYMENT_TABLE (PAYMENT_ID, PAYMENT_TAX,PAYMENT_INSURANCE,ITEM_ID,TRANSPORTATION_ID,COMPANY_ID) VALUES (?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, payment.getId());
        preparedStatement.setFloat(2, payment.getTax());
        preparedStatement.setFloat(3, payment.getInsurance());
        preparedStatement.setFloat(4, payment.getItem().getId());
        preparedStatement.setFloat(5, payment.getTransportation().getId());
        preparedStatement.setInt(6, payment.getCompany().getCountry().getId());
        preparedStatement.execute();
        return payment;
    }

    //Edit
    @Override
    public Payment edit(Payment payment) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE PAYMENT_TABLE SET PAYMENT_TAX=? , PAYMENT_INSURANCE=? ,ITEM_ID=? ,TRANSPORTATION_ID=?,COMPANY_ID=? WHERE PAYMENT_ID=?"
        );
        preparedStatement.setFloat(1, payment.getTax());
        preparedStatement.setFloat(2, payment.getInsurance());
        preparedStatement.setFloat(3, payment.getItem().getCost());
        preparedStatement.setFloat(4, (float) payment.getTransportation().getFreight());
        preparedStatement.setInt(5, payment.getCompany().getCountry().getTariff());
        preparedStatement.setInt(6, payment.getId());
        preparedStatement.execute();
        return payment;
    }

    //Remove
    @Override
    public Payment remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM PAYMENT_TABLE WHERE PAYMENT_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //FindALl
    @Override
    public List<Payment> findAll() throws Exception {
        List<Payment> paymentList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM PAYMENT_TABLE ORDER BY PAYMENT_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Payment payment = Payment
                    .builder()
                    .id(resultSet.getInt("PAYMENT_ID"))
                    .tax(resultSet.getFloat("PAYMENT_TAX"))
                    .insurance(resultSet.getFloat("PAYMENT_INSURANCE"))
                    .item(Item.builder().cost(resultSet.getInt("ITEM_COST")).build())
                    .transportation(Transportation.builder().freight(resultSet.getInt("TRANSPORTATION_FREIGHT")).build())
                    .company(Company.builder().country(Country.builder().tariff(resultSet.getInt("COUNTRY_TARIFF")).build()).build())
                    .item(Item.builder().palletCapacity(resultSet.getInt("PALLET_CAPACITY")).build())
                    .build();

            paymentList.add(payment);
        }

        return paymentList;
    }

    //FindById
    @Override
    public Payment findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM PAYMENT_TABLE WHERE PAYMENT_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Payment payment = null;
        if (resultSet.next()) {
            payment = Payment
                    .builder()
                    .id(resultSet.getInt("PAYMENT_ID"))
                    .tax(resultSet.getFloat("PAYMENT_TAX"))
                    .insurance(resultSet.getFloat("PAYMENT_INSURANCE"))
                    .item(Item.builder().cost(resultSet.getInt("ITEM_COST")).build())
                    .transportation(Transportation.builder().freight(resultSet.getInt("TRANSPORTATION_FREIGHT")).build())
                    .company(Company.builder().country(Country.builder().tariff(resultSet.getInt("COUNTRY_TARIFF")).build()).build())
                    .item(Item.builder().palletCapacity(resultSet.getInt("PALLET_CAPACITY")).build())
                    .build();
        }
        return payment;
    }

    //Close
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
