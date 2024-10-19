package exportation.controller;
import exportation.model.bl.CompanyBl;
import exportation.model.bl.PersonBl;
import exportation.model.entity.Company;
import exportation.model.entity.Country;
import exportation.model.entity.Person;
import exportation.model.entity.enums.Brand;
import exportation.model.entity.enums.CompanyType;
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
public class CompanyController implements Initializable {

    @FXML
    private TextField idTxt,nameTxt,prodTxt,manageTxt,countryTxt,emailTxt,phoneTxt,findByIdTxt;

    @FXML
    private ComboBox <String> typeCombo;

    @FXML
    private TextArea addressTxtA;

    @FXML
    private Button saveBtn, editBtn,removeBtn,findAllBtn;

    @FXML
    private TableView <Company> companyTbl;

    @FXML
    private TableColumn <Company, Integer>idCln,manageCln,countryCln;

    @FXML
    private TableColumn <Company, String> nameCln,prodCln,typeCln,emailCln,phoneCln,addressCln;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        log.info("App Started");
        for (CompanyType value : CompanyType.values()) {
            typeCombo.getItems().add(value.name());
        }

        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Load Error\n" + e.getMessage());
            alert.show();
        }


        saveBtn.setOnAction(event -> {
                    try {
                        Company company = Company
                                .builder()
                                .name(nameTxt.getText())
                                .product(prodTxt.getText())
                                .address(addressTxtA.getText())
                                .email(emailTxt.getText())
                                .phoneNumber(phoneTxt.getText())
                                .person(Person.builder().id(Integer.parseInt(manageTxt.getText())).build())
                                .country(Country.builder().id(Integer.parseInt(countryTxt.getText())).build())
                                .companyType(CompanyType.valueOf(typeCombo.getSelectionModel().getSelectedItem()))
                                .build();

                        CompanyBl.getCompanyBl().save(company);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, " Company Saved\n" + company);
                        alert.show();
                        resetForm();
                        log.info("Company Saved" + company);
                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, " Company Save Error\n" + e.getMessage());
                        alert.show();
                        log.error("Save Error : " + e.getMessage());
                    }
                });

            editBtn.setOnAction(event -> {
                try {
                    Company company = Company
                            .builder()
                            .id(Integer.parseInt(idTxt.getText()))
                            .name(nameTxt.getText())
                            .product(prodTxt.getText())
                            .address(addressTxtA.getText())
                            .email(emailTxt.getText())
                            .phoneNumber(phoneTxt.getText())
                            .person(Person.builder().id(Integer.parseInt(manageTxt.getText())).build())
                            .country(Country.builder().id(Integer.parseInt(countryTxt.getText())).build())
                            .companyType(CompanyType.valueOf(typeCombo.getSelectionModel().getSelectedItem()))
                            .build();

                    CompanyBl.getCompanyBl().edit(company);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, " Company Edited\n" + company);
                    alert.show();
                    resetForm();
                    log.info("Company Edited" + company);
                } catch (Exception e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR, " Company Edit Error\n" + e.getMessage());
                    alert.show();
                    log.error("Edit Error : " + e.getMessage());
                }
            });

            removeBtn.setOnAction(event-> {
                 try {
                      CompanyBl.getCompanyBl().remove(Integer.parseInt(idTxt.getText()));
                      Alert alert = new Alert(Alert.AlertType.INFORMATION, " Company Removed\n" + idTxt.getText());
                      alert.show();
                      log.info("Company Removed " + idTxt.getText());
                      resetForm();
                 } catch (Exception e) {
                      Alert alert = new Alert(Alert.AlertType.ERROR, " Company Remove Error\n" + e.getMessage());
                      alert.show();
                      log.error("Remove Error : " + e.getMessage());}
                    });


            findByIdTxt.setOnKeyReleased((event) -> {
                try {
                    Company company = CompanyBl.getCompanyBl().findById(Integer.parseInt(findByIdTxt.getText()));
                    if (company != null) {
                        showDataOnTable(Collections.singletonList(company));
                        log.info("Company Searched By Id: " + findByIdTxt.getText());
                    } else {
                        showDataOnTable(Collections.emptyList());
                    }
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Company\n" + e.getMessage());
                    alert.show();
                    log.error("Find By Id Error: " + e.getMessage());
                }
            });

            findAllBtn.setOnAction((event) -> {
                try {
                    showDataOnTable(CompanyBl.getCompanyBl().findAll());
                    log.info("All Company Searched : " + findAllBtn);
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, " Company\n" + e.getMessage());
                    alert.show();
                    log.error("Find All Error : " + e.getMessage());
                }
            });

            companyTbl.setOnMouseClicked((event) -> {
                Company company =companyTbl.getSelectionModel().getSelectedItem();
                idTxt.setText(String.valueOf(company.getId()));
                nameTxt.setText(company.getName());
                prodTxt.setText(company.getAddress());
                addressTxtA.setText(company.getAddress());
                emailTxt.setText(company.getEmail());
                phoneTxt.setText(company.getPhoneNumber());
                manageTxt.setText(String.valueOf(company.getPerson().getId()));
                countryTxt.setText(String.valueOf(company.getCountry().getId()));
                typeCombo.getSelectionModel().select(company.getCompanyType().ordinal());
            });
    }

    private void showDataOnTable (List <Company> companyList) {

        ObservableList<Company> observableList = FXCollections.observableList(companyList);
        idCln.setCellValueFactory(new PropertyValueFactory<>("id"));
        manageCln.setCellValueFactory(new PropertyValueFactory<>("person"));
        countryCln.setCellValueFactory(new PropertyValueFactory<>("country"));
        nameCln.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodCln.setCellValueFactory(new PropertyValueFactory<>("product"));
        typeCln.setCellValueFactory(new PropertyValueFactory<>("companyType"));
        emailCln.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneCln.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressCln.setCellValueFactory(new PropertyValueFactory<>("address"));
        companyTbl.setItems(observableList);
    }

    private void resetForm() throws Exception {
        idTxt.clear();
        nameTxt.clear();
        prodTxt.clear();
        addressTxtA.clear();
        emailTxt.clear();
        phoneTxt.clear();
        manageTxt.clear();
        countryTxt.clear();
        typeCombo.getSelectionModel().select(0);
        showDataOnTable(CompanyBl.getCompanyBl().findAll());
    }
}
