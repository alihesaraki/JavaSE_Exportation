package exportation.model.da;

import exportation.model.entity.*;
import exportation.model.entity.enums.CompanyType;
import lombok.extern.log4j.Log4j;
import exportation.model.tools.CRUD;
import exportation.model.tools.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class CompanyDa implements AutoCloseable, CRUD<Company> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public CompanyDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    //save
    @Override
    public Company save(Company company) throws Exception {
        company.setId(ConnectionProvider.getConnectionProvider().getNextId("COMPANY_SEQ"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO COMPANY_TABLE (COMPANY_ID,COMPANY_NAME,COMPANY_PRODUCT,COMPANY_ADDRESS,COMPANY_EMAIL,COMPANY_PHONE,PERSON_ID,COUNTRY_ID,COMPANY_TYPE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, company.getId());
        preparedStatement.setString(2, company.getName());
        preparedStatement.setString(3, company.getProduct());
        preparedStatement.setString(4, company.getAddress());
        preparedStatement.setString(5, company.getEmail());
        preparedStatement.setString(6, company.getPhoneNumber());
        preparedStatement.setInt(7, company.getPerson().getId());
        preparedStatement.setInt(8, company.getCountry().getId());
        preparedStatement.setString(9, String.valueOf(company.getCompanyType()));
        preparedStatement.execute();
        return company;
    }

    //Edit
    @Override
    public Company edit(Company company) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE COMPANY_TABLE SET COMPANY_NAME=?,COMPANY_PRODUCT=?,COMPANY_ADDRESS=?,COMPANY_EMAIL=?,COMPANY_PHONE=?,PERSON_ID=?,COUNTRY_ID=?,COMPANY_TYPE=? WHERE COMPANY_ID=?"
        );
        preparedStatement.setString(1, company.getName());
        preparedStatement.setString(2, company.getProduct());
        preparedStatement.setString(3, company.getAddress());
        preparedStatement.setString(4, company.getEmail());
        preparedStatement.setString(5, company.getPhoneNumber());
        preparedStatement.setInt(6, company.getPerson().getId());
        preparedStatement.setInt(7, company.getCountry().getId());
        preparedStatement.setString(8, String.valueOf(company.getCompanyType()));
        preparedStatement.setInt(9, company.getId());
        preparedStatement.execute();
        return company;
    }

    //Remove
    @Override
    public Company remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM COMPANY_TABLE WHERE COMPANY_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //FindALl
    @Override
    public List<Company> findAll() throws Exception {
        List<Company> companyList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM COMPANY_TABLE ORDER BY COMPANY_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Company company = Company
                    .builder()
                    .id(resultSet.getInt("COMPANY_ID"))
                    .name(resultSet.getString("COMPANY_NAME"))
                    .product(resultSet.getString("COMPANY_PRODUCT"))
                    .address(resultSet.getString("COMPANY_ADDRESS"))
                    .email(resultSet.getString("COMPANY_EMAIL"))
                    .phoneNumber(resultSet.getString("COMPANY_PHONE"))
                    .country(Country.builder().id(resultSet.getInt("COUNTRY_ID")).build())
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .companyType(CompanyType.valueOf(resultSet.getString("COMPANY_TYPE")))
                    .build();

            companyList.add(company);
        }
        return companyList;
    }

    //FindById
    @Override
    public Company findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM COMPANY_TABLE WHERE COMPANY_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Company company = null;
        if (resultSet.next()) {
            company = Company
                    .builder()
                    .id(resultSet.getInt("COMPANY_ID"))
                    .name(resultSet.getString("COMPANY_NAME"))
                    .product(resultSet.getString("COMPANY_PRODUCT"))
                    .address(resultSet.getString("COMPANY_ADDRESS"))
                    .email(resultSet.getString("COMPANY_EMAIL"))
                    .phoneNumber(resultSet.getString("COMPANY_PHONE"))
                    .country(Country.builder().id(resultSet.getInt("COUNTRY_ID")).build())
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .companyType(CompanyType.valueOf(resultSet.getString("COMPANY_TYPE")))
                    .build();
        }
        return company;
    }

    //Close
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
