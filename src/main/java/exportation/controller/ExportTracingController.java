package exportation.controller;
import exportation.model.bl.ExportTracingBl;
import exportation.model.bl.PersonBl;
import exportation.model.entity.ExportTracing;
import exportation.model.entity.Person;
import exportation.model.entity.Trade;
import exportation.model.entity.Transportation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

@Log4j
public class ExportTracingController implements Initializable {

    @FXML
    private TextField idTxt,transIdText,tradeTxt,fById;

    @FXML
    private DatePicker date;

    @FXML
    private RadioButton loadN,loadY,payN,payY,checkN,checkY;

    @FXML
    private ToggleGroup loadingToggle,paymentToggle,checkToggle;

    @FXML
    private Button saveBtn,editBtn,removeBtn,findAllBtn,tradeBtn,transBtn,payBtn;

    @FXML
    private TableView < ExportTracing> exportTbl;

    @FXML
    private TableColumn < ExportTracing, Integer> idCln,tradeCln,transportCln;

    @FXML
    private TableColumn < ExportTracing, Boolean> loadingCln,payCln,checkCln;

    @FXML
    private TableColumn < ExportTracing, LocalDate> dateCln;

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

                RadioButton loadingStatus = (RadioButton) loadingToggle.getSelectedToggle();
                RadioButton prePayment = (RadioButton) paymentToggle.getSelectedToggle();
                RadioButton checkout = (RadioButton) checkToggle.getSelectedToggle();
                ExportTracing exportTracing = ExportTracing
                        .builder()
                        .loadingStatus(loadingStatus.isSelected())
                        .prePayment(prePayment.isSelected())
                        .checkout(checkout.isSelected())
                        .transportation(Transportation.builder().id(Integer.parseInt(transIdText.getText())).build())
                        .trade(Trade.builder().id(Integer.parseInt(tradeTxt.getText())).build())
                        .date(date.getValue())
                        .build();
                ExportTracingBl.getExportTracingBl().save(exportTracing);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Exportation Saved\n" + exportTracing);
                alert.show();
                resetForm();
                log.info("Exportation Saved " + exportTracing);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Exportation Save Error\n" + e.getMessage());
                alert.show();
                log.error("Save Error : " + e.getMessage());
            }

        });

        editBtn.setOnAction(event -> {
            try {

                RadioButton loadingStatus = (RadioButton) loadingToggle.getSelectedToggle();
                RadioButton prePayment = (RadioButton) paymentToggle.getSelectedToggle();
                RadioButton checkout = (RadioButton) checkToggle.getSelectedToggle();
                ExportTracing exportTracing = ExportTracing
                        .builder()
                        .loadingStatus(loadingStatus.isSelected())
                        .prePayment(prePayment.isSelected())
                        .checkout(checkout.isSelected())
                        .transportation(Transportation.builder().id(Integer.parseInt(transIdText.getText())).build())
                        .trade(Trade.builder().id(Integer.parseInt(tradeTxt.getText())).build())
                        .date(date.getValue())
                        .build();
                ExportTracingBl.getExportTracingBl().edit(exportTracing);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Exportation Edited\n" + exportTracing);
                alert.show();
                resetForm();
                log.info("Exportation Edited " + exportTracing);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Exportation Edit Error\n" + e.getMessage());
                alert.show();
                log.error("Edit Error : " + e.getMessage());
            }

        });

        removeBtn.setOnAction(event -> {
            try {
                ExportTracingBl.getExportTracingBl().remove(Integer.parseInt(idTxt.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION," Exportation removed\n" + idTxt.getText());
                log.info("Exportation Removed " + idTxt.getText());
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Exportation Remove Error\n" + e.getMessage());
                alert.show();
                log.error("Remove Error : " + e.getMessage());
            }
        });

        tradeBtn.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/exportation/view/trade.fxml")));
                stage.setScene(scene);
                stage.setTitle("Trade");
                stage.show();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });

        transBtn.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/exportation/view/transportation.fxml")));
                stage.setScene(scene);
                stage.setTitle("Transportation");
                stage.show();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });

        payBtn.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/exportation/view/payment.fxml")));
                stage.setScene(scene);
                stage.setTitle("Price Structure");
                stage.show();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });

        fById.setOnKeyReleased((event) -> {
            try {
                ExportTracing exportTracing = ExportTracingBl.getExportTracingBl().findById(Integer.parseInt(fById.getText()));
                if (exportTracing != null) {
                    showDataOnTable(Collections.singletonList(exportTracing));
                    log.info("Exportation Searched By Id: " + fById.getText());
                } else {
                    showDataOnTable(Collections.emptyList());
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Exportation\n" + e.getMessage());
                alert.show();
                log.error("Find By Id Error: " + e.getMessage());
            }
        });

        findAllBtn.setOnAction((event) -> {
            try {
                showDataOnTable(ExportTracingBl.getExportTracingBl().findAll());
                log.info("All Exportation Searched: " + findAllBtn);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Exportation\n" + e.getMessage());
                alert.show();
                log.error("Find All Exportation Error: " + e.getMessage());
            }
        });

        exportTbl.setOnMouseClicked((event) -> {
            ExportTracing exportTracing =exportTbl.getSelectionModel().getSelectedItem();
            idTxt.setText(String.valueOf(exportTracing.getId()));
            if (exportTracing.isLoadingStatus()) {
                loadY.setSelected(true);
            } else {
                loadN.setSelected(true);
            }
            if (exportTracing.isPrePayment()) {
                payY.setSelected(true);
            } else {
                payN.setSelected(true);
            }
            if (exportTracing.isPrePayment()) {
                checkY.setSelected(true);
            } else {
                checkN.setSelected(true);
            }
            transIdText.setText(String.valueOf(exportTracing.getTransportation().getId()));
            tradeTxt.setText(String.valueOf(exportTracing.getTrade().getId()));
            date.setValue(exportTracing.getDate());
        });
    }
    private void showDataOnTable(List<ExportTracing> exportTracingList) {
        ObservableList<ExportTracing> observableList = FXCollections.observableList(exportTracingList);
        idCln.setCellValueFactory(new PropertyValueFactory<>("id"));
        loadingCln.setCellValueFactory(new PropertyValueFactory<>("loadingStatus"));
        payCln.setCellValueFactory(new PropertyValueFactory<>("prePayment"));
        checkCln.setCellValueFactory(new PropertyValueFactory<>("checkout"));
        transportCln.setCellValueFactory(new PropertyValueFactory<>("transportation"));
        tradeCln.setCellValueFactory(new PropertyValueFactory<>("trade"));
        dateCln.setCellValueFactory(new PropertyValueFactory<>("date"));
        exportTbl.setItems(observableList);
    }

    private void resetForm() throws Exception {
        idTxt.clear();
        loadN.setSelected(true);
        payN.setSelected(true);
        checkN.setSelected(true);
        transIdText.clear();
        tradeTxt.clear();
        date.setValue(null);
        showDataOnTable(ExportTracingBl.getExportTracingBl().findAll());

    }

}
