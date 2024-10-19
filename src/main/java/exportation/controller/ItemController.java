package exportation.controller;

import exportation.model.bl.CountryBl;
import exportation.model.bl.ItemBl;
import exportation.model.bl.PersonBl;
import exportation.model.da.ItemDa;
import exportation.model.entity.Country;
import exportation.model.entity.Item;
import exportation.model.entity.Person;
import exportation.model.entity.enums.Brand;
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
import java.util.regex.Pattern;


@Log4j
public class ItemController implements Initializable {

    @FXML
    private TextField idTxt,nameTxt,hsTxt,modelTxt,dOfUnitTxt,dOfPalletTxt,wOfUnitTxt,wOfPalletTxt,pCapacityTxt,costTxt,fByIdTxt,fByModelTxt;

    @FXML
    private ComboBox<String>brandCombo;

    @FXML
    private Button saveBtn,editBtn,removeBtn,findAllBtn;

    @FXML
    private TableView <Item> itemTbl;

    @FXML
    private TableColumn<Item,String> nameCln,modelCln,dOfUnitCln,dOfPalletCln;

    @FXML
    private TableColumn<Item,Long> hsCln;

    @FXML
    private TableColumn<Item,Brand>brandCln;

    @FXML
    private TableColumn<Item,Integer> idCln,pCapacityCln;

    @FXML
    private TableColumn<Item,Float> wOfUnitCln,wOfPalletCln,costCln;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        log.info("App Started");
        for (Brand value : Brand.values()) {
            brandCombo.getItems().add(value.name());
        }

        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Load Error\n" + e.getMessage());
            alert.show();
        }

        saveBtn.setOnAction(event -> {
            try {

                Item item = Item
                        .builder()
                        .name(nameTxt.getText())
                        .brand(Brand.valueOf(brandCombo.getSelectionModel().getSelectedItem()))
                        .model(modelTxt.getText())
                        .dimensionOfUnite(dOfUnitTxt.getText())
                        .dimensionOfPallet(dOfPalletTxt.getText())
                        .palletCapacity(Integer.parseInt(pCapacityTxt.getText()))
                        .cost(Float.parseFloat(costTxt.getText()))
                        .Hs_Code(Long.parseLong(hsTxt.getText()))
                        .weightOfUnit(Float.parseFloat(wOfUnitTxt.getText()))
                        .weightOfPallet(Float.parseFloat(wOfPalletTxt.getText()))
                        .build();
                ItemBl.getItemBl().save(item);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Item Saved\n" + item);
                alert.show();
                resetForm();
                log.info("Item Saved " + item);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Item Save Error\n" + e.getMessage());
                alert.show();
                log.error("Save Error : " + e.getMessage());
            }

        });

        editBtn.setOnAction(event -> {
            try {

                Item item = Item
                        .builder()
                        .id(Integer.parseInt(idTxt.getText()))
                        .name(nameTxt.getText())
                        .brand(Brand.valueOf(brandCombo.getSelectionModel().getSelectedItem()))
                        .model(modelTxt.getText())
                        .dimensionOfUnite(dOfUnitTxt.getText())
                        .dimensionOfPallet(dOfPalletTxt.getText())
                        .palletCapacity(Integer.parseInt(pCapacityTxt.getText()))
                        .cost(Float.parseFloat(costTxt.getText()))
                        .Hs_Code(Long.parseLong(hsTxt.getText()))
                        .weightOfUnit(Float.parseFloat(wOfUnitTxt.getText()))
                        .weightOfPallet(Float.parseFloat(wOfPalletTxt.getText()))
                        .build();
                ItemBl.getItemBl().edit(item);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Item Edited\n" + item);
                alert.show();
                resetForm();
                log.info("Item Edited " + item);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Item Edit Error\n" + e.getMessage());
                alert.show();
                log.error("Edit Error : " + e.getMessage());
            }

        });

        removeBtn.setOnAction(event -> {
            try {
                ItemBl.getItemBl().remove(Integer.parseInt(idTxt.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Item Removed\n" + idTxt.getText());
                alert.show();
                log.info("Item Removed " + idTxt.getText());
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Item Remove Error\n" + e.getMessage());
                alert.show();
                log.error("Remove Error : " + e.getMessage());
            }
        });

        fByIdTxt.setOnKeyReleased((event) -> {
            try {
                Item item = ItemBl.getItemBl().findById(Integer.parseInt(fByIdTxt.getText()));
                if (item != null) {
                    showDataOnTable(Collections.singletonList(item));
                    log.info("Item Searched By Id: " + fByIdTxt.getText());
                } else {
                    showDataOnTable(Collections.emptyList());
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Item\n" + e.getMessage());
                alert.show();
                log.error("Find By Id Error: " + e.getMessage());
            }
        });

        fByModelTxt.setOnKeyReleased((event) -> {
            try {
                showDataOnTable( ItemBl.getItemBl().findByModel(fByModelTxt.getText()));
                log.info("Item Searched By Model : " + fByModelTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Item\n" + e.getMessage());
                alert.show();
                log.error("Find By Model Error : " + e.getMessage());
            }
        });

        findAllBtn.setOnAction((event) -> {
            try {
                showDataOnTable(ItemBl.getItemBl().findAll());
                log.info("ALL Item Searched : " + findAllBtn);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Item\n" + e.getMessage());
                alert.show();
                log.error("Find ALL Item Error : " + e.getMessage());
            }
        });

        itemTbl.setOnMouseClicked((event) -> {
            Item item = itemTbl.getSelectionModel().getSelectedItem();
            idTxt.setText(String.valueOf(item.getId()));
            nameTxt.setText(item.getName());
            brandCombo.getSelectionModel().select(item.getBrand().ordinal());
            modelTxt.setText(item.getModel());
            dOfUnitTxt.setText(item.getDimensionOfUnite());
            dOfPalletTxt.setText(item.getDimensionOfUnite());
            pCapacityTxt.setText(String.valueOf(item.getPalletCapacity()));
            hsTxt.setText(String.valueOf(item.getHs_Code()));
            costTxt.setText(String.valueOf(item.getCost()));
            wOfUnitTxt.setText(String.valueOf(item.getWeightOfUnit()));
            wOfPalletTxt.setText(String.valueOf(item.getWeightOfPallet()));
        });
    }

    private void showDataOnTable(List<Item> itemList) {
        ObservableList<Item> observableList = FXCollections.observableList(itemList);
        idCln.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCln.setCellValueFactory(new PropertyValueFactory<>("name"));
        brandCln.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelCln.setCellValueFactory(new PropertyValueFactory<>("model"));
        dOfUnitCln.setCellValueFactory(new PropertyValueFactory<>("dimensionOfUnite"));
        dOfPalletCln.setCellValueFactory(new PropertyValueFactory<>("dimensionOfPallet"));
        pCapacityCln.setCellValueFactory(new PropertyValueFactory<>("palletCapacity"));
        hsCln.setCellValueFactory(new PropertyValueFactory<>("Hs_Code"));
        costCln.setCellValueFactory(new PropertyValueFactory<>("cost"));
        wOfUnitCln.setCellValueFactory(new PropertyValueFactory<>("weightOfUnit"));
        wOfPalletCln.setCellValueFactory(new PropertyValueFactory<>("weightOfPallet"));
        itemTbl.setItems(observableList);
    }

    private void resetForm() throws Exception {
        idTxt.clear();
        nameTxt.clear();
        brandCombo.getSelectionModel().select(0);
        modelTxt.clear();
        dOfUnitTxt.clear();
        dOfPalletTxt.clear();
        pCapacityTxt.clear();
        hsTxt.clear();
        costTxt.clear();
        wOfUnitTxt.clear();
        wOfPalletTxt.clear();
        showDataOnTable(ItemBl.getItemBl().findAll());

    }
}
