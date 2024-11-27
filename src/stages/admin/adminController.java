package stages.admin;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.*;


public class adminController {

    @FXML
    private ComboBox<String> accountbutton;

    public void initialize() {
        accountbutton.getItems().addAll("Student", "Staff");
        accountbutton.setOnAction(event -> {
            String selected = accountbutton.getValue();
            System.out.println("Selected: " + selected);
        });
    }

    @FXML
    private AnchorPane mngcont;



    private void adminManagebooks() {
        try {

            Stage stage = (Stage) mngcont.getScene().getWindow();
            Parent newRoot = FXMLLoader.load(getClass().getResource("../admin/admin_managebooks.fxml"));
            Scene newScene = new Scene(newRoot);
            stage.setScene(newScene);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    public void goAdmgBooks(ActionEvent event) throws Exception {
        adminManagebooks(); // Slide sidedoor to the left
    }

    public void close(ActionEvent event) throws Exception {
        System.exit(0); // Slide sidedoor to the left
    }





}
