package exportation.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class WindowsManager {

    public static void showMainSheet() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(
                FXMLLoader.load(Objects.requireNonNull(WindowsManager.class.getResource("/exportation/view/main.fxml")))
        );

        stage.setScene(scene);
        stage.setTitle("Main");
        stage.show();
    }

    public static void showCompanySheet() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(
                FXMLLoader.load(Objects.requireNonNull(WindowsManager.class.getResource("/exportation/view/company.fxml")))
        );

        stage.setScene(scene);
        stage.setTitle("Company");
        stage.show();
    }

    public static void showCountrySheet() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(
                FXMLLoader.load(Objects.requireNonNull(WindowsManager.class.getResource("/exportation/view/country.fxml")))
        );

        stage.setScene(scene);
        stage.setTitle("Country");
        stage.show();
    }

    public static void showTradeSheet() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(
                FXMLLoader.load(Objects.requireNonNull(WindowsManager.class.getResource("/exportation/view/trade.fxml")))
        );

        stage.setScene(scene);
        stage.setTitle("Trade");
        stage.show();
    }

    public static void showExportSheet() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(
                FXMLLoader.load(Objects.requireNonNull(WindowsManager.class.getResource("/exportation/view/export.fxml")))
        );

        stage.setScene(scene);
        stage.setTitle("Exportation");
        stage.show();
    }

    public static void showItemSheet() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(
                FXMLLoader.load(Objects.requireNonNull(WindowsManager.class.getResource("/exportation/view/item.fxml")))
        );

        stage.setScene(scene);
        stage.setTitle("Product");
        stage.show();
    }

    public static void showPaymentSheet() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(
                FXMLLoader.load(Objects.requireNonNull(WindowsManager.class.getResource("/exportation/view/payment.fxml")))
        );

        stage.setScene(scene);
        stage.setTitle("Price Structure");
        stage.show();
    }

    public static void showPersonSheet() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(
                FXMLLoader.load(WindowsManager.class.getResource("/exportation/view/person.fxml"))
        );

        stage.setScene(scene);
        stage.setTitle("Middleman");
        stage.show();
    }

    public static void showTransportationSheet() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(
                FXMLLoader.load(Objects.requireNonNull(WindowsManager.class.getResource("/exportation/view/transportation.fxml")))
        );

        stage.setScene(scene);
        stage.setTitle("Transportation");
        stage.show();
    }
}