package exportation;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.util.Objects;

@Log4j
public class MyApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/exportation/view/payment.fxml"))));


            primaryStage.setScene(scene);
            primaryStage.setTitle("payment");
            primaryStage.setOnCloseRequest((event) -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to exit the application?");
                if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
                    Platform.exit();
                }
            });
            primaryStage.show();
        } catch (IOException | NullPointerException e) {
            log.error("Error loading FXML", e);
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error loading FXML: " + e.getMessage());
            alert.showAndWait();
            Platform.exit();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
