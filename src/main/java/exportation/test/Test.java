package exportation.test;

import exportation.model.bl.*;
import exportation.model.entity.*;
import exportation.model.entity.enums.Brand;
import exportation.model.entity.enums.CompanyType;
import exportation.model.entity.enums.Gender;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) throws Exception {

        //check Country
        Country country1 =
                Country
                        .builder()
                        .name("Iran")
                        .tariff(100)
                        .phoneCode("0098")
                        .importRate(100)
                        .population(85)
                        .carRate(1000)
                        .neighbors("Turkey")
                        .build();

        Country country2 =
                Country
                        .builder()
                        .name("Iraq")
                        .tariff(200)
                        .phoneCode("0097")
                        .importRate(200)
                        .population(40)
                        .carRate(2000)
                        .neighbors("Iran")
                        .build();
        Country country3 =
                Country
                        .builder()
                        .name("UAE")
                        .tariff(300)
                        .phoneCode("0096")
                        .importRate(300)
                        .population(20)
                        .carRate(3000)
                        .neighbors("Saudi Arabia")
                        .build();

        CountryBl.getCountryBl().save(country1);
        CountryBl.getCountryBl().save(country2);
        CountryBl.getCountryBl().save(country3);

        //check user
        User user1 =
                User
                        .builder()
                        .username("ali_he")
                        .password("ali1234")
                        .enabled(true)
                        .build();

        User user2 =
                User
                        .builder()
                        .username("zahra_eb")
                        .password("zahra1234")
                        .enabled(false)
                        .build();

        User user3 =
                User
                        .builder()
                        .username("reza_he")
                        .password("reza1234")
                        .enabled(false)
                        .build();

        UserBl.getUserBl().save(user1);
        UserBl.getUserBl().save(user2);
        UserBl.getUserBl().save(user3);

        //check Person
        Person person1 =
                Person
                        .builder()
                        .name("ali")
                        .family("hesaraki")
                        .email("ali@hesaraki@gmail.com")
                        .phoneNumber("09101244905")
                        .nationalId("0019596499")
                        .position("Boss")
                        .address("hesarak")
                        .gender(Gender.male)
                        .user(user1)
                        .build();
        Person person2 =
                Person
                        .builder()
                        .name("zahra")
                        .family("ebrahimi")
                        .email("zahraebrahimi@gmail.com")
                        .phoneNumber("0910000000")
                        .nationalId("0019596497")
                        .position("kargar_sade")
                        .address("shahrak")
                        .gender(Gender.female)
                        .user(user2)
                        .build();

        Person person3 =
                Person
                        .builder()
                        .name("reza")
                        .family("rezaei")
                        .email("reza@gmail.com")
                        .phoneNumber("0920000000")
                        .nationalId("0019596498")
                        .position("kargar_sade")
                        .address("shahrak")
                        .gender(Gender.female)
                        .user(user3)
                        .build();

        PersonBl.getPersonBl().save(person1);
        PersonBl.getPersonBl().save(person2);
        PersonBl.getPersonBl().save(person3);

        //check Company
        Company company1 =
                Company
                        .builder()
                        .name("Iran Co.")
                        .product("Product1")
                        .address("Address1")
                        .email("company1@gmail.com")
                        .phoneNumber("09111111111")
                        .person(person1)
                        .country(country1)
                        .companyType(CompanyType.manufacturer)
                        .build();

        Company company2 =
                Company
                        .builder()
                        .name("Iraq Co.")
                        .product("Product2")
                        .address("Address2")
                        .email("company2@gmail.com")
                        .phoneNumber("09222222222")
                        .person(person2)
                        .country(country2)
                        .companyType(CompanyType.supplier)
                        .build();

        Company company3 =
                Company
                        .builder()
                        .name("UAE Co.")
                        .product("Product3")
                        .address("Address3")
                        .email("company3@gmail.com")
                        .phoneNumber("09333333333")
                        .person(person3)
                        .country(country3)
                        .companyType(CompanyType.supplier)
                        .build();

        CompanyBl.getCompanyBl().save(company1);
        CompanyBl.getCompanyBl().save(company2);
        CompanyBl.getCompanyBl().save(company3);


        //check Trade
        Trade trade1 =
                Trade
                        .builder()
                        .status("status1")
                        .contract("contract1")
                        .agreement("agreement1")
                        .person(person1)
                        .date(LocalDate.ofYearDay(2024, 1))
                        .build();

        Trade trade2 =
                Trade
                        .builder()
                        .status("status2")
                        .contract("contract2")
                        .agreement("agreement2")
                        .person(person2)
                        .date(LocalDate.ofYearDay(2024, 2))
                        .build();

        Trade trade3 =
                Trade
                        .builder()
                        .status("status3")
                        .contract("contract3")
                        .agreement("agreement3")
                        .person(person3)
                        .date(LocalDate.ofYearDay(2024, 3))
                        .build();

        TradeBl.getTradeBl().save(trade1);
        TradeBl.getTradeBl().save(trade2);
        TradeBl.getTradeBl().save(trade3);


        //check Item
        Item item =
                Item
                        .builder()
                        .name("Mobile")
                        .brand(Brand.carpile)
                        .model("model1")
                        .dimensionOfUnite("1000")
                        .dimensionOfPallet("1000")
                        .palletCapacity(1)
                        .Hs_Code(1000000)
                        .cost(1000)
                        .weightOfUnit(1000000)
                        .weightOfPallet(1000000)
                        .build();

        ItemBl.getItemBl().save(item);


        //heck Transportation
        Transportation transportation =
                Transportation
                        .builder()
                        .direction("direction1")
                        .freight(1000000)
                        .item(item)
                        .company(company1)
                        .country(country1)
                        .date(LocalDate.ofYearDay(2024, 20))
                        .build();

        TransportationBl.getTransportationBl().save(transportation);

        //check ExportTracing
        ExportTracing exportTracing =
                ExportTracing
                        .builder()
                        .loadingStatus(true)
                        .prePayment(true)
                        .checkout(false)
                        .transportation(transportation)
                        .trade(trade1)
                        .date(LocalDate.ofYearDay(2024, 30))
                        .build();

        ExportTracingBl.getExportTracingBl().save(exportTracing);

//check Payment
        Payment payment =
                Payment
                        .builder()
                        .tax(15000)
                        .insurance(12000)
                        .item(item)
                        .transportation(transportation)
                        .company(company1)
                        .build();

        PaymentBl.getPaymentBl().save(payment);
    }
}
