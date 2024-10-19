package exportation.test;

import exportation.model.da.PaymentDa;
import exportation.model.da.TransportationDa;
import exportation.model.entity.*;

import java.sql.SQLException;
import java.time.LocalDate;

public class TransportationTest {
    public static void main(String[] args) throws Exception {
//        Transportation transportation = Transportation.builder()
//                .id(1)
//                .exportTracing(ExportTracing.builder().trade(Trade.builder().id(1).build()).build())
//                .freight(1200)
//                .country(Country.builder().id(1).build())
//                .item(Item.builder().id(1).build())
//                .date(LocalDate.now())
//                .direction("Asdasd")
//                .company(Company.builder().id(1).name("varta").build())
//                .build();
//        TransportationDa transportationDa = new TransportationDa();
//        transportationDa.save(transportation);

        Payment payment = Payment.builder()
                .id(1)
                .transportation(Transportation.builder().freight(1500).build())
                .tax(10)
                .item(Item.builder().cost(850710).build())
//                .item(Item.builder().palletCapacity(1800).build())
                .insurance(45.3F)
//                .company(Company.builder().country(Country.builder().tariff(5).build()).build())
                .build();
        PaymentDa paymentDa = new PaymentDa();
        paymentDa.save(payment);
        System.out.println(payment);

    }
}
