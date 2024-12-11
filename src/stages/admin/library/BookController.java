package stages.admin.library;

import Entity.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class BookController {

    @FXML
    private VBox box;

    @FXML
    private ImageView bookImage;

    @FXML
    private VBox imageContainer;

    @FXML
    private Label bookTitle;

    @FXML
    private Label bookInitial;

    public void setData(Book book) {
        Image image = new Image(getClass().getResourceAsStream(book.getImageSrc()));

        bookImage.setImage(image);

        bookTitle.setText(book.getTitle());
        bookInitial.setText(Character.toString(book.getTitle().charAt(0)));
    }

}
