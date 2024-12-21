package stages.admin;

import Entity.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import stages.admin.library.BookSelectionService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class bkManageController implements Initializable {

    @FXML
    private VBox libraryBox;

    @FXML
    private TextField bkTitleField;

    @FXML
    private TextField bkAuthorField;

    @FXML
    private TextField bkISBNField;

    @FXML
    private TextField bkCtgryField;

    @FXML
    private TextField bkQtyField;

    @FXML
    private ImageView bkImage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // Load the library view
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(bkManageController.class.getResource("/stages/admin/library/libraryView.fxml"));
            VBox libraryView = fxmlLoader.load();
            libraryBox.getChildren().add(libraryView);

            // Listen for changes in the selected book
            BookSelectionService.getInstance().selectedBookProperty().addListener((observable, oldBook, newBook) -> {
                if (newBook != null) {
                    setBookData(newBook);
                }
            });

        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            error.showAndWait();
        }
    }

    public void setBookData(Book book) {
        if (book != null) {
            bkImage.setImage(book.getImageSrc());
            bkTitleField.setText(book.getTitle());
            bkAuthorField.setText(book.getAuthor());
            bkISBNField.setText(book.getISBN());
            bkCtgryField.setText(book.getCategory());
            bkQtyField.setText(Integer.toString(book.getQuantity()));
        }
    }

    @FXML
    private void goDashboard(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/stages/admin/adminFXML/admin_dashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void goAccountStaff(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/stages/admin/adminFXML/admin_acctStaffs.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void goBorrowTransact(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/stages/admin/adminFXML/transact/admin_transact.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void goInventory(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/stages/admin/adminFXML/inventory/admin_inventory.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void goLogout(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Do you want to continue?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("You successfully logged out!");
            Parent root = FXMLLoader.load(getClass().getResource("/stages/login/logFXML/login_view.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

//    @FXML
//    private void goManageBooks(MouseEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/stages/admin/adminFXML/admin_bkManage.fxml"));
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(new Scene(root));
//        stage.show();
//    }

    @FXML
    private void goProfileAdmin(MouseEvent event) {

    }

    @FXML
    public void goReports(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/stages/admin/adminFXML/admin_reports.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


}
