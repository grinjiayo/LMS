package stages.admin;

import Entity.Book;
import Entity.Category;
import LinkedList.DoublyLinkList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import stages.admin.library.BookSelectionService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Function.*;
import stages.admin.library.libraryController;

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

    @FXML
    private ChoiceBox<Category> categoryCB;

    @FXML
    private libraryController libraryCtrl; // Reference to the libraryController

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // Load the categories into the ChoiceBox as before
            ArrayList<Category> categories = globalVariable.dbFnc.retrieveCategories();
            categories.add(new Category(0, "All"));
            if (categories.size() != 0) {
                categoryCB.getItems().addAll(categories);
                categoryCB.setValue(categories.get(0));
            }

            // Add a listener to the selected item property
            categoryCB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    System.out.println("Selected: " + newValue.getName());
                    // Call the handleChoice method when category is selected
                    handleChoice(newValue);
                }
            });

            // Load the library view and get the controller instance
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(bkManageController.class.getResource("/stages/admin/library/libraryView.fxml"));
            VBox libraryView = fxmlLoader.load();
            libraryBox.getChildren().add(libraryView);

            // Get the libraryController instance from the FXMLLoader
            libraryCtrl = fxmlLoader.getController();

            BookSelectionService.getInstance().selectedBookProperty().addListener((observable, oldBook, newBook) -> {
                if (newBook != null) {
                    // Call setBookData to update the fields with the selected book's data
                    setBookData(newBook);
                }
            });

        } catch (IOException e) {
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
        Parent root = FXMLLoader.load(getClass().getResource("/stages/admin/adminFXML/staff/admin_acctStaffsAdd.fxml"));
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
    private void goReports(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/stages/admin/adminFXML/reports/admin_acc_reports.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void handleChoice(Category category) {
        if (category.getName().equals("All")) {
            DoublyLinkList bookList = globalVariable.bookList;
            // Initialize the library view with all books
            if (libraryCtrl != null) {
                libraryCtrl.initializeLibraryView(bookList);
            }
        } else {
            DoublyLinkList categoryList = globalVariable.fnc.selectCategoryBooks(category);
            // Initialize the library view with the selected category's books
            if (libraryCtrl != null) {
                libraryCtrl.initializeLibraryView(categoryList);
            }
        }
    }

}
