package exportation.controller;

import exportation.model.bl.ItemBl;
import exportation.model.bl.PersonBl;
import exportation.model.bl.TradeBl;
import exportation.model.bl.TransportationBl;
import exportation.model.entity.*;
import exportation.model.entity.enums.Brand;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;

import java.awt.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

@Log4j
public class TransportationController implements Initializable {

    @FXML
    private TextField idTxt, coIdTxt, productTxt, countryTx, directionTxt, freightTxt, tradeIdTxt, fByIdTxt;
    @FXML
    private Button saveBtn, editBtn, removeBtn, countryBtn, tradeBtn, exportationBtn, findAllBtn;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TableView<Transportation> transportTbl;
    @FXML
    private TableColumn<Transportation, Integer> idCln;
    @FXML
    private TableColumn<Transportation, String> directionCln;
    @FXML
    private TableColumn<Transportation, Float> freightCln;
    @FXML
    private TableColumn<Transportation, Trade> tradeCln;
    @FXML
    private TableColumn<Transportation, Item> productCln;
    @FXML
    private TableColumn<Transportation, Company> companyCln;
    @FXML
    private TableColumn<Transportation, Country> countryCln;
    @FXML
    private TableColumn<Transportation, LocalDate> dateCln;
    @FXML
    private TableColumn<Transportation, ExportTracing> expoCln;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        log.info("App Started");
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Load Error\n" + e.getMessage());
            alert.show();
        }

        countryBtn.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/exportation/view/country.fxml")));
                stage.setScene(scene);
                stage.setTitle("Country Info");
                stage.show();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });

        tradeBtn.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/exportation/view/trade.fxml")));
                stage.setScene(scene);
                stage.setTitle("Trade Info");
                stage.show();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });

        exportationBtn.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/exportation/view/export.fxml")));
                stage.setScene(scene);
                stage.setTitle("Exportation Info");
                stage.show();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });

        saveBtn.setOnAction(event -> {
            try {
                Transportation transportation = Transportation
                        .builder()
                        .direction(directionTxt.getText())
                        .freight(Float.parseFloat(freightTxt.getText()))
                        .item(Item.builder().id(Integer.parseInt(productTxt.getText())).build())
                        .company(Company.builder().name((coIdTxt.getText())).build())
                        .country(Country.builder().id(Integer.parseInt(countryTx.getText())).build())
                        .date(datePicker.getValue())
                        .build();
                TransportationBl.getTransportationBl().save(transportation);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Transportation Saved\n" + transportation);
                alert.show();
                resetForm();
                log.info("Transportation Saved " + transportation);
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, " Transportation Save Error\n" + e.getMessage());
                alert.show();
                log.error("Save Error : " + e.getMessage());
            }

        });

        editBtn.setOnAction(event -> {
            try {

                Transportation transportation = Transportation
                        .builder()
                        .id(Integer.parseInt(idTxt.getText()))
                        .direction(directionTxt.getText())
                        .freight(Float.parseFloat(freightTxt.getText()))
                        .item(Item.builder().id(Integer.parseInt(productTxt.getText())).build())
                        .company(Company.builder().name((coIdTxt.getText())).build())
                        .country(Country.builder().id(Integer.parseInt(countryTx.getText())).build())
                        .date(datePicker.getValue())
                        .build();
                TransportationBl.getTransportationBl().edit(transportation);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "  Transportation Edited\n" + transportation);
                alert.show();
                resetForm();
                log.info(" Transportation Edited" + transportation);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "  Transportation Edit Error\n" + e.getMessage());
                alert.show();
                log.error("Edit Error : " + e.getMessage());
            }

        });

        removeBtn.setOnAction(event -> {
            try {
                TransportationBl.getTransportationBl().remove(Integer.parseInt(idTxt.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Transportation Removed\n" + idTxt.getText());
                alert.show();
                log.info("Transportation Removed " + idTxt.getText());
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Transportation Remove Error\n" + e.getMessage());
                alert.show();
                log.error("Remove Error : " + e.getMessage());
            }
        });

        findAllBtn.setOnAction((event) -> {
            try {
                showDataOnTable(TransportationBl.getTransportationBl().findAll());
                log.info("All Transportation Searched : " + findAllBtn);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Transportation\n" + e.getMessage());
                alert.show();
                log.error("Find All Error : " + e.getMessage());
            }
        });

        fByIdTxt.setOnKeyReleased((event) -> {
            try {
                Transportation transportation = TransportationBl.getTransportationBl().findById(Integer.parseInt(fByIdTxt.getText()));
                if (transportation != null) {
                    showDataOnTable(Collections.singletonList(transportation));
                    log.info("Transportation Searched By Id: " + fByIdTxt.getText());
                } else {
                    showDataOnTable(Collections.emptyList());
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Transportation\n" + e.getMessage());
                alert.show();
                log.error("Find By Id Error: " + e.getMessage());
            }
        });

        transportTbl.setOnMouseClicked((event) -> {
            Transportation transportation = transportTbl.getSelectionModel().getSelectedItem();
            idTxt.setText(String.valueOf(transportation.getId()));
            directionTxt.setText(transportation.getDirection());
            freightTxt.setText(String.valueOf(transportation.getFreight()));
            productTxt.setText(String.valueOf(transportation.getItem().getId()));
            coIdTxt.setText(String.valueOf(transportation.getCompany().getId()));
            countryTx.setText(String.valueOf(transportation.getCountry().getId()));
            datePicker.setValue(LocalDate.now());
        });
    }

    private void showDataOnTable(List<Transportation> transportationList) {

        ObservableList<Transportation> observableList = FXCollections.observableList(transportationList);
        idCln.setCellValueFactory(new PropertyValueFactory<>("id"));
        directionCln.setCellValueFactory(new PropertyValueFactory<>("direction"));
        freightCln.setCellValueFactory(new PropertyValueFactory<>("freight"));
        tradeCln.setCellValueFactory(new PropertyValueFactory<>("exportTracing"));
        productCln.setCellValueFactory(new PropertyValueFactory<>("item"));
        companyCln.setCellValueFactory(new PropertyValueFactory<>("company"));
        countryCln.setCellValueFactory(new PropertyValueFactory<>("country"));
        dateCln.setCellValueFactory(new PropertyValueFactory<>("date"));
        transportTbl.setItems(observableList);
    }

    private void resetForm() throws Exception {
        idTxt.clear();
        directionTxt.clear();
        freightTxt.clear();
        productTxt.clear();
        coIdTxt.clear();
        countryTx.clear();
        datePicker.setValue(null);
        showDataOnTable(TransportationBl.getTransportationBl().findAll());
    }
}
