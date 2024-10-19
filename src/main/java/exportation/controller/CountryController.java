package exportation.controller;


import exportation.model.bl.CountryBl;
import exportation.model.bl.PersonBl;
import exportation.model.entity.Country;
import exportation.model.entity.Person;
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
public class CountryController implements Initializable {

    @FXML
    private TextField idTxt, nameTxt, tariffTxt, phCodeTxt, importRTxt, popTxt, carRTxt, neighborsTxt, fByIdTxt, fByNameTxt;

    @FXML
    private Button saveBtn, editBtn, removeBtn, findAllBtn;

    @FXML
    private TableView<Country> countryTbl;

    @FXML
    private TableColumn<Country, Integer> idCln, tariffCln;

    @FXML
    private TableColumn<Country, Long> importRCln, popCln, carRCln;

    @FXML
    private TableColumn<Country, String> nameCln, phCodeCln, neighborCln;


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

                Country country = Country
                        .builder()
                        .name(Validator.nameValidator(nameTxt.getText(), "Invalid Name"))
                        .tariff(Integer.parseInt(tariffTxt.getText()))
                        .phoneCode(phCodeTxt.getText())
                        .importRate(Long.parseLong(importRTxt.getText()))
                        .population(Long.parseLong(popTxt.getText()))
                        .carRate(Long.parseLong(carRTxt.getText()))
                        .neighbors(neighborsTxt.getText())
                        .build();
                CountryBl.getCountryBl().save(country);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Country Saved\n" + country);
                alert.show();
                resetForm();
                log.info("Country Saved " + country);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Country Save Error\n" + e.getMessage());
                alert.show();
                log.error("Save Error : " + e.getMessage());
            }

        });

        editBtn.setOnAction(event -> {
            try {

                Country country = Country
                        .builder()
                        .id(Integer.parseInt(idTxt.getText()))
                        .name(Validator.nameValidator(nameTxt.getText(), "Invalid Name"))
                        .tariff(Integer.parseInt(tariffTxt.getText()))
                        .phoneCode(phCodeTxt.getText())
                        .importRate(Long.parseLong(importRTxt.getText()))
                        .population(Long.parseLong(popTxt.getText()))
                        .carRate(Long.parseLong(carRTxt.getText()))
                        .neighbors(neighborsTxt.getText())
                        .build();
                CountryBl.getCountryBl().edit(country);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Country Edited\n" + country);
                alert.show();
                resetForm();
                log.info("Country Edited " + country);
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, " Country Edit Error\n" + e.getMessage());
                alert.show();
                log.error("Edit Error : " + e.getMessage());
            }

        });

        removeBtn.setOnAction(event -> {
            try {
                CountryBl.getCountryBl().remove(Integer.parseInt(idTxt.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Country Removed\n" + idTxt.getText());
                alert.show();
                log.info("Country Removed " + idTxt.getText());
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Country Remove Error\n" + e.getMessage());
                alert.show();
                log.error("Remove Error : " + e.getMessage());
            }
        });

        fByIdTxt.setOnKeyReleased((event) -> {
            try {
                Country country = CountryBl.getCountryBl().findById(Integer.parseInt(fByIdTxt.getText()));
                if (country != null) {
                    showDataOnTable(Collections.singletonList(country));
                    log.info("Country Searched By Id: " + fByIdTxt.getText());
                } else {
                    showDataOnTable(Collections.emptyList());
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Country\n" + e.getMessage());
                alert.show();
                log.error("Find By Id Error: " + e.getMessage());
            }
        });

        fByNameTxt.setOnKeyReleased((event) -> {
            try {
                showDataOnTable(CountryBl.getCountryBl().findByName(fByNameTxt.getText()));
                log.info("Country Searched By Name : " + fByNameTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Country\n" + e.getMessage());
                alert.show();
                log.error("Find By Name Error : " + e.getMessage());
            }
        });

        findAllBtn.setOnAction((event) -> {
            try {
                showDataOnTable(CountryBl.getCountryBl().findAll());
                log.info("ALL Country Searched : ");
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Country\n" + e.getMessage());
                alert.show();
                log.error("Find ALL Country Error : " + e.getMessage());
            }
        });

        countryTbl.setOnMouseClicked((event) -> {
            Country country = countryTbl.getSelectionModel().getSelectedItem();
            idTxt.setText(String.valueOf(country.getId()));
            nameTxt.setText(country.getName());
            tariffTxt.setText(String.valueOf(country.getTariff()));
            phCodeTxt.setText(country.getPhoneCode());
            importRTxt.setText(String.valueOf(country.getImportRate()));
            popTxt.setText(String.valueOf(country.getPopulation()));
            carRTxt.setText(String.valueOf(country.getCarRate()));
            neighborsTxt.setText(country.getNeighbors());
        });
    }

    private void showDataOnTable(List<Country> countryList) {
        System.out.println(countryList);
        ObservableList<Country> observableList = FXCollections.observableList(countryList);
        idCln.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCln.setCellValueFactory(new PropertyValueFactory<>("name"));
        tariffCln.setCellValueFactory(new PropertyValueFactory<>("tariff"));
        phCodeCln.setCellValueFactory(new PropertyValueFactory<>("phoneCode"));
        importRCln.setCellValueFactory(new PropertyValueFactory<>("importRate"));
        popCln.setCellValueFactory(new PropertyValueFactory<>("population"));
        carRCln.setCellValueFactory(new PropertyValueFactory<>("carRate"));
        neighborCln.setCellValueFactory(new PropertyValueFactory<>("neighbors"));
        countryTbl.setItems(observableList);
    }

    private void resetForm() throws Exception {
        idTxt.clear();
        nameTxt.clear();
        tariffTxt.clear();
        phCodeTxt.clear();
        importRTxt.clear();
        popTxt.clear();
        carRTxt.clear();
        neighborsTxt.clear();
        showDataOnTable(CountryBl.getCountryBl().findAll());

    }
}

