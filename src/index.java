import LinkedList.DoublyLinkList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import stages.login.LoginController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import Function.*;

import java.io.IOException;
public class index extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("stages/login/logFXML/login_view.fxml"));
            Parent root = loader.load();

            // Set stage title and icon
            stage.setTitle("LMS!");
            Image icon = new Image(getClass().getResource("icons/icon_LMS.png").toExternalForm());
            stage.getIcons().add(icon);

            // Get the controller instance
            LoginController controller = loader.getController();

            // Create and configure the scene
            Scene scene = new Scene(root);
            scene.setOnKeyPressed(event -> {
                if (event.isControlDown() && event.isShiftDown() && event.getCode() == KeyCode.S) {
                    try {
                        // Trigger admin login
                        controller.adminLogin(event);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            // Configure the stage
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            //Copy the book in database
            globalVariable.bookList = globalVariable.dbFnc.retrieveBooksnOrder();
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.setTitle("Indexing Error");
            alert.show();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
