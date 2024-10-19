package exportation.controller;

import exportation.model.bl.CountryBl;
import exportation.model.bl.ExportTracingBl;
import exportation.model.bl.PaymentBl;
import exportation.model.bl.PersonBl;
import exportation.model.entity.*;
import exportation.model.tools.Validator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
@Log4j

public class PaymentController  implements Initializable {

    @FXML
    private TextField idTxt, taxTxt,insureTxt,cOfPerTxt,freightTxt,tariffTxt,cifTxt,totalTxt,fById, palletTxt;

    @FXML
    private Button saveBtn, editBtn, removeBtn, findAllBtn,cifBtn,totalBtn;

    @FXML
    private TableView<Payment> payTbl;

    @FXML
    private TableColumn<Payment, Integer> idCln,tariffCln,palletCln;

    @FXML
    private TableColumn<Payment, Float>  taxCln,insureCln,cOfPerCln,freightCln;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        log.info("App Started");

        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Load Error\n" + e.getMessage());
            alert.show();
        }

        saveBtn.setOnAction(event -> {
            try {

                Payment payment = Payment
                        .builder()
                        .tax(Float.parseFloat(taxTxt.getText()))
                        .insurance(Float.parseFloat(insureTxt.getText()))
                        .item(Item.builder().cost(Float.parseFloat(cOfPerTxt.getText())).build())
                        .transportation(Transportation.builder().freight(Float.parseFloat(freightTxt.getText())).build())
                        .company(Company.builder().country(Country.builder().tariff(Integer.parseInt(tariffTxt.getText())).build()).build())
                        .item(Item.builder().palletCapacity(Integer.parseInt(palletTxt.getText())).build())
                        .build();
                PaymentBl.getPaymentBl().save(payment);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Payment Saved\n" + payment);
                alert.show();
                resetForm();
                log.info("Payment Saved " + payment);
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, " Payment Save Error\n" + e.getMessage());
                alert.show();
                log.error("Save Error : " + e.getMessage());
            }

        });

        editBtn.setOnAction(event -> {
            try {

                Payment payment = Payment
                        .builder()
                        .id(Integer.parseInt(idTxt.getText()))
                        .tax(Float.parseFloat(taxTxt.getText()))
                        .insurance(Float.parseFloat(insureTxt.getText()))
                        .item(Item.builder().cost(Float.parseFloat(cOfPerTxt.getText())).build())
                        .transportation(Transportation.builder().freight(Float.parseFloat(freightTxt.getText())).build())
                        .company(Company.builder().country(Country.builder().tariff(Integer.parseInt(tariffTxt.getText())).build()).build())
                        .item(Item.builder().palletCapacity(Integer.parseInt(palletTxt.getText())).build())
                        .build();
                PaymentBl.getPaymentBl().save(payment);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Payment Edited\n" + payment);
                alert.show();
                resetForm();
                log.info("Payment Edited " + payment);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Payment Edit Error\n" + e.getMessage());
                alert.show();
                log.error("Edit Error : " + e.getMessage());
            }


        });


       removeBtn.setOnAction(event -> {
        try {
            PaymentBl.getPaymentBl().remove(Integer.parseInt(idTxt.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION," Payment removed\n" + idTxt.getText());
            log.info("Payment Removed " + idTxt.getText());
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, " Payment Remove Error\n" + e.getMessage());
            alert.show();
            log.error("Remove Error : " + e.getMessage());
        }
    });


        fById.setOnKeyReleased((event) -> {
            try {
                Payment payment = PaymentBl.getPaymentBl().findById(Integer.parseInt(fById.getText()));
                if (payment != null) {
                    showDataOnTable(Collections.singletonList(payment));
                    log.info("Payment Searched By Id: " + fById.getText());
                } else {
                    showDataOnTable(Collections.emptyList());
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Payment\n" + e.getMessage());
                alert.show();
                log.error("Find By Id Error: " + e.getMessage());
            }
        });


        findAllBtn.setOnAction((event) -> {
            try {
                showDataOnTable(PaymentBl.getPaymentBl().findAll());
                log.info("ALL Payment Searched : " + findAllBtn);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Exportation\n" + e.getMessage());
                alert.show();
                log.error("Find ALL Payment Error : " + e.getMessage());
            }
        });


        totalBtn.setOnAction((event) ->{
            try {

                float tax = Float.parseFloat(taxTxt.getText());
                float insurance = Float.parseFloat(insureTxt.getText());
                float cost = Float.parseFloat(cOfPerTxt.getText());
                float freight = Float.parseFloat(freightTxt.getText());
                int tariff = Integer.parseInt(tariffTxt.getText());
                int pallet = Integer.parseInt(palletTxt.getText());

                long total = Payment.totalCost(tariff,cost,tax,pallet,insurance,freight);

                totalTxt.setText(String.valueOf(total));

                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Calculation Done\n" + total);
                alert.show();
                resetForm();
                log.info("Calculation Done" + total);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Calculation Error\n" + e.getMessage());
                alert.show();
                log.error("Calculation Error : " + e.getMessage());
            }
        } );


        cifBtn.setOnAction((event) ->{
            try {
                float tax = Float.parseFloat(taxTxt.getText());
                float insurance = Float.parseFloat(insureTxt.getText());
                float cost = Float.parseFloat(cOfPerTxt.getText());
                float freight = Float.parseFloat(freightTxt.getText());
                int tariff = Integer.parseInt(tariffTxt.getText());
                int pallet = Integer.parseInt(palletTxt.getText());

                long cif = Payment.cif(cost,pallet,insurance,freight);

                cifTxt.setText(String.valueOf(cif));

                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Calculation Done\n" + cif);
                alert.show();
                resetForm();
                log.info("Calculation Done" + cif);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Calculation Error\n" + e.getMessage());
                alert.show();
                log.error("Calculation Error : " + e.getMessage());
            }
        } );

        payTbl.setOnMouseClicked((event) -> {
            Payment payment =payTbl.getSelectionModel().getSelectedItem();
            idTxt.setText(String.valueOf(payment.getId()));
            taxTxt.setText(String.valueOf(payment.getTax()));
            insureTxt.setText(String.valueOf(payment.getInsurance()));
            cOfPerTxt.setText(String.valueOf(payment.getItem().getCost()));
            freightTxt.setText(String.valueOf(payment.getTransportation().getFreight()));
            tariffTxt.setText(String.valueOf(payment.getTransportation().getCountry().getTariff()));
            palletTxt.setText(String.valueOf(payment.getItem().getPalletCapacity()));
        });

   }

    private void showDataOnTable(List<Payment> paymentList) {
        ObservableList<Payment> observableList = FXCollections.observableList(paymentList);
        idCln.setCellValueFactory(new PropertyValueFactory<>("id"));
        taxCln.setCellValueFactory(new PropertyValueFactory<>("tax"));
        insureCln.setCellValueFactory(new PropertyValueFactory<>("insurance"));
        cOfPerCln.setCellValueFactory(new PropertyValueFactory<>("cost"));
        freightCln.setCellValueFactory(new PropertyValueFactory<>("freight"));
        tariffCln.setCellValueFactory(new PropertyValueFactory<>("tariff"));
        palletCln.setCellValueFactory(new PropertyValueFactory<>("palletCapacity"));
        payTbl.setItems(observableList);
    }

    private void resetForm() throws Exception {
        idTxt.clear();
        taxTxt.clear();
        insureTxt.clear();
        cOfPerTxt.clear();
        freightTxt.clear();
        tariffTxt.clear();
        palletTxt.clear();
        showDataOnTable(PaymentBl.getPaymentBl().findAll());

    }
}
