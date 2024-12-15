package stages.admin;

import Entity.Book;
import Entity.Category;
import Function.*;
import Function.globalVariable;
import LinkedList.DoublyLinkList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class inventoryInsertController implements Initializable {

    @FXML
    private TextField tfAuthor;

    @FXML
    private ChoiceBox<Category> tfCategory;

    @FXML
    private TextField tfISBN;

    @FXML
    private TextField tfQuantity;

    @FXML
    private TextField tfTitle;

    @FXML
    private TextField pathField;

    @FXML
    private ImageView imgView;

    @FXML
    private Label lblError;

    private Image newImage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Category> category = globalVariable.dbFnc.retrieveCategories();
        tfCategory.getItems().addAll(category);
        tfCategory.setValue(category.get(0));
    }

//SWITCHING MENU
    @FXML
    private void goDashboard(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/stages/admin/adminFXML/admin_dashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void goAccountStaff(MouseEvent event) throws IOException {
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

//    @FXML
//    private void goInventory(MouseEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/stages/admin/adminFXML/admin_inventory.fxml"));
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(new Scene(root));
//        stage.show();
//    }

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

    @FXML
    private void goManageBooks(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/stages/admin/adminFXML/admin_bkManage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void goProfileAdmin(MouseEvent event) {

    }

    @FXML
    private void goReports(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/stages/admin/adminFXML/admin_reports.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //PROCEED TO SUB MENU
    @FXML
    private void doInsert(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/stages/admin/adminFXML/inventory/admin_inventoryInsert.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void doModify(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/stages/admin/adminFXML/inventory/admin_inventoryModify.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //INSERT FUNCTIONS
    @FXML
    private void browseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an Image");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            String filePath = file.toURI().toString();
            pathField.setText(filePath);

            newImage = new Image(filePath);

            imgView.setImage(newImage);
            imgView.setFitWidth(120);
            imgView.setFitHeight(144);
            imgView.setPreserveRatio(false);
        }
    }

    @FXML
    private void createBook(ActionEvent event) {
        if(newImage==null) {
            lblError.setText("No image selected");  return;
        }else if(tfTitle.getText()==null) {
            lblError.setText("Title is blank");  return;
        }else if(tfAuthor.getText()==null) {
            lblError.setText("Author is blank"); return;
        }else if(tfISBN.getText()==null) {
            lblError.setText("ISBN is blank");return;
        }else if(globalVariable.fnc.digitChecker(tfISBN.getText())==false) {
            lblError.setText("ISBN should be all digits"); return;
        }else if(tfCategory.getSelectionModel()==null) {
            lblError.setText("No category selected"); return;
        }else if(tfQuantity.getText()==null) {
            lblError.setText("Quantity is blank"); return;
        }else if(globalVariable.fnc.digitChecker(tfQuantity.getText()) == false) {
            lblError.setText("Quantity should be digits"); return;
        }

        //Upload the image to database
        int id = globalVariable.dbFnc.insertBookImageDB(newImage);

        String bkTitle = tfTitle.getText();
        String bkAuthor = tfAuthor.getText();
        String bkISBN = tfISBN.getText();
        Category ctgryObj = tfCategory.getSelectionModel().getSelectedItem();
        String category = ctgryObj.getName();
        int quantity = Integer.parseInt(tfQuantity.getText());

        Book newBook = new Book(bkTitle, bkAuthor, category, newImage, bkISBN, quantity);

        //Upload the book to database
        boolean ifSuccess = globalVariable.dbFnc.insertBookDB(newBook, id);
        if(ifSuccess) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Book inserted successfully", ButtonType.OK);
            alert.setTitle("Book Insert");
            alert.show();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Book is not inserted", ButtonType.OK);
            alert.setTitle("Book Insert");
            alert.show();
        }
    }

}
