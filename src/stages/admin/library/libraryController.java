package stages.admin.library;

import Function.Function;
import LinkedList.DoublyLinkList;
import Entity.Book;
import LinkedList.Link;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class libraryController implements Initializable {

    Function func = new Function();

    @FXML
    private HBox bookLayout;
    @FXML
    private HBox bookLayout2;
    @FXML
    private HBox bookLayout3;
    @FXML
    private VBox cabinet;
    @FXML
    private Label bookNum;
    @FXML
    private Button moveLeftBtn;
    @FXML
    private Button moveRightBtn;
    @FXML
    private Label cabinetPos;

    private DoublyLinkList books;
    private int cabinetIndex = 0;
    private int cabinetCount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            books = new DoublyLinkList(libraryBooks()); //Get the books
            moveLeftBtn.setDisable(true);

            int bookCount = books.getSize();      //Indicates the number of books
            bookNum.setText(Integer.toString(bookCount));
            cabinetPos.setText(Integer.toString(cabinetIndex+1));
            cabinetCount = bookCount/42;      //Number of cabinets(each contains 42 books;
            if(bookCount%42!=0) {       //Add another cabinet
                cabinetCount++;
            }

            int count = 0;
            Link current = books.getFirst();
            while (current != null) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/stages/admin/library/Book.fxml"));

                HBox bookBox = fxmlLoader.load();
                BookController bookController = fxmlLoader.getController();
                bookController.setData(current.getElement());
                if(count<14) {
                    bookLayout.getChildren().add(bookBox);
                }else if(count<28) {
                    bookLayout2.getChildren().add(bookBox);
                }else if(count<42) {
                    bookLayout3.getChildren().add(bookBox);
                }
                count++;
                current = current.getNext();
            }

        }catch(IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    void moveLeftEvt(ActionEvent event) {
        try {
            if(cabinetIndex>=0) {
                moveRightBtn.setDisable(false); //Update the moving btn cause he can now move right
                cabinetIndex--;
                int start = cabinetIndex * 42;
                int end = start + 42;
                DoublyLinkList bookList = copyValueList(books, start, end);

                Link current = bookList.getFirst();

                bookLayout.getChildren().clear();
                bookLayout2.getChildren().clear();
                bookLayout3.getChildren().clear();

                int count = 0;
                while (current != null) {               //Load to display book in cabinet
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/stages/admin/library/Book.fxml"));

                    HBox bookBox = fxmlLoader.load();
                    BookController bookController = fxmlLoader.getController();
                    bookController.setData(current.getElement());       //Set the data of the book
                    if (count < 14) {
                        bookLayout.getChildren().add(bookBox);
                    } else if (count < 28) {
                        bookLayout2.getChildren().add(bookBox);
                    } else if (count < 42) {
                        bookLayout3.getChildren().add(bookBox);
                    } else break;
                    count++;
                    current = current.getNext();
                }

                //Update the cabinet index
                cabinetPos.setText(Integer.toString(cabinetIndex+1));

                moveLeftBtn.setDisable(cabinetIndex == 0);
            }
        }catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    void moveRightEvt(ActionEvent event) {
        try {
            if(cabinetIndex<cabinetCount) {
                moveLeftBtn.setDisable(false); //Update the moving btn cause he can now move right
                cabinetIndex++;
                cabinetPos.setText(Integer.toString(cabinetIndex));
                int start = cabinetIndex * 42;
                int end = start + 42;
                DoublyLinkList bookList = copyValueList(books, start, end);

                int bookCount = bookList.getSize();      //Indicates the number of books
                Link current = bookList.getFirst();

                bookLayout.getChildren().clear();
                bookLayout2.getChildren().clear();
                bookLayout3.getChildren().clear();
                int count = 0;
                while (current != null) {               //Load to display book in cabinet
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/stages/admin/library/Book.fxml"));

                    HBox bookBox = fxmlLoader.load();
                    BookController bookController = fxmlLoader.getController();
                    bookController.setData(current.getElement());       //Set the data of the book
                    if (count < 14) {
                        bookLayout.getChildren().add(bookBox);
                    } else if (count < 28) {
                        bookLayout2.getChildren().add(bookBox);
                    } else if (count < 42) {
                        bookLayout3.getChildren().add(bookBox);
                    } else break;
                    count++;
                    current = current.getNext();
                }

                //Update the cabinet index
                cabinetPos.setText(Integer.toString(cabinetIndex+1));

                moveRightBtn.setDisable(cabinetIndex == cabinetCount-1);
            }
        }catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    //TO BE ERASED
    private DoublyLinkList libraryBooks() { //Create a list of books
        DoublyLinkList books = new DoublyLinkList();
        books.insertNOrder(new Book("Call of the Wild", "Adriano Santos", "/bookImages/call_Book.png", 3));
        books.insertNOrder(new Book("Dracula", "Adriano Santos", "/bookImages/dracula_book.jpg", 3));
        books.insertNOrder(new Book("Odyssey", "Adriano Santos", "/bookImages/odyssey_book.jpg", 3));
        books.insertNOrder(new Book("Percy Jackson", "Adriano Santos", "/bookImages/percy_book.jpg", 3));
        books.insertNOrder(new Book("Howl", "Adriano Santos", "/bookImages/howl_Book.jpg", 3));
        books.insertNOrder(new Book("Percy Jackson", "Adriano Santos", "/bookImages/percy_book.jpg", 3));
        books.insertNOrder(new Book("Call of the Wild", "Adriano Santos", "/bookImages/call_Book.png", 3));
        books.insertNOrder(new Book("Dracula", "Adriano Santos", "/bookImages/dracula_book.jpg", 3));
        books.insertNOrder(new Book("Odyssey", "Adriano Santos", "/bookImages/odyssey_book.jpg", 3));
        books.insertNOrder(new Book("Percy Jackson", "Adriano Santos", "/bookImages/percy_book.jpg", 3));
        books.insertNOrder(new Book("Howl", "Adriano Santos", "/bookImages/howl_Book.jpg", 3));
        books.insertNOrder(new Book("Percy Jackson", "Adriano Santos", "/bookImages/percy_book.jpg", 3));
        books.insertNOrder(new Book("Call of the Wild", "Adriano Santos", "/bookImages/call_Book.png", 3));
        books.insertNOrder(new Book("Dracula", "Adriano Santos", "/bookImages/dracula_book.jpg", 3));
        books.insertNOrder(new Book("Odyssey", "Adriano Santos", "/bookImages/odyssey_book.jpg", 3));
        books.insertNOrder(new Book("Percy Jackson", "Adriano Santos", "/bookImages/percy_book.jpg", 3));
        books.insertNOrder(new Book("Howl", "Adriano Santos", "/bookImages/howl_Book.jpg", 3));
        books.insertNOrder(new Book("Percy Jackson", "Adriano Santos", "/bookImages/percy_book.jpg", 3));
        books.insertNOrder(new Book("Call of the Wild", "Adriano Santos", "/bookImages/call_Book.png", 3));
        books.insertNOrder(new Book("Dracula", "Adriano Santos", "/bookImages/dracula_book.jpg", 3));
        books.insertNOrder(new Book("Odyssey", "Adriano Santos", "/bookImages/odyssey_book.jpg", 3));
        books.insertNOrder(new Book("Percy Jackson", "Adriano Santos", "/bookImages/percy_book.jpg", 3));
        books.insertNOrder(new Book("Howl", "Adriano Santos", "/bookImages/howl_Book.jpg", 3));
        books.insertNOrder(new Book("Percy Jackson", "Adriano Santos", "/bookImages/percy_book.jpg", 3));
        books.insertNOrder(new Book("Call of the Wild", "Adriano Santos", "/bookImages/call_Book.png", 3));
        books.insertNOrder(new Book("Dracula", "Adriano Santos", "/bookImages/dracula_book.jpg", 3));
        books.insertNOrder(new Book("Odyssey", "Adriano Santos", "/bookImages/odyssey_book.jpg", 3));
        books.insertNOrder(new Book("Percy Jackson", "Adriano Santos", "/bookImages/percy_book.jpg", 3));
        books.insertNOrder(new Book("Howl", "Adriano Santos", "/bookImages/howl_Book.jpg", 3));
        books.insertNOrder(new Book("Percy Jackson", "Adriano Santos", "/bookImages/percy_book.jpg", 3));
        books.insertNOrder(new Book("Call of the Wild", "Adriano Santos", "/bookImages/call_Book.png", 3));
        books.insertNOrder(new Book("Dracula", "Adriano Santos", "/bookImages/dracula_book.jpg", 3));
        books.insertNOrder(new Book("Odyssey", "Adriano Santos", "/bookImages/odyssey_book.jpg", 3));
        books.insertNOrder(new Book("Percy Jackson", "Adriano Santos", "/bookImages/percy_book.jpg", 3));
        books.insertNOrder(new Book("Howl", "Adriano Santos", "/bookImages/howl_Book.jpg", 3));
        books.insertNOrder(new Book("Percy Jackson", "Adriano Santos", "/bookImages/percy_book.jpg", 3));
        books.insertNOrder(new Book("Call of the Wild", "Adriano Santos", "/bookImages/call_Book.png", 3));
        books.insertNOrder(new Book("Dracula", "Adriano Santos", "/bookImages/dracula_book.jpg", 3));
        books.insertNOrder(new Book("Odyssey", "Adriano Santos", "/bookImages/odyssey_book.jpg", 3));
        books.insertNOrder(new Book("Percy Jackson", "Adriano Santos", "/bookImages/percy_book.jpg", 3));
        books.insertNOrder(new Book("Howl", "Adriano Santos", "/bookImages/howl_Book.jpg", 3));
        books.insertNOrder(new Book("Percy Jackson", "Adriano Santos", "/bookImages/percy_book.jpg", 3));
        books.insertNOrder(new Book("Call of the Wild", "Adriano Santos", "/bookImages/call_Book.png", 3));
        books.insertNOrder(new Book("Dracula", "Adriano Santos", "/bookImages/dracula_book.jpg", 3));
        books.insertNOrder(new Book("Odyssey", "Adriano Santos", "/bookImages/odyssey_book.jpg", 3));
        books.insertNOrder(new Book("Percy Jackson", "Adriano Santos", "/bookImages/percy_book.jpg", 3));
        books.insertNOrder(new Book("Howl", "Adriano Santos", "/bookImages/howl_Book.jpg", 3));
        books.insertNOrder(new Book("Percy Jackson", "Adriano Santos", "/bookImages/percy_book.jpg", 3));
        books.insertNOrder(new Book("Call of the Wild", "Adriano Santos", "/bookImages/call_Book.png", 3));
        books.insertNOrder(new Book("Dracula", "Adriano Santos", "/bookImages/dracula_book.jpg", 3));
        books.insertNOrder(new Book("Odyssey", "Adriano Santos", "/bookImages/odyssey_book.jpg", 3));
        books.insertNOrder(new Book("Percy Jackson", "Adriano Santos", "/bookImages/percy_book.jpg", 3));
        books.insertNOrder(new Book("Howl", "Adriano Santos", "/bookImages/howl_Book.jpg", 3));
        books.insertNOrder(new Book("Percy Jackson", "Adriano Santos", "/bookImages/percy_book.jpg", 3));
        books.insertNOrder(new Book("Call of the Wild", "Adriano Santos", "/bookImages/call_Book.png", 3));
        books.insertNOrder(new Book("Dracula", "Adriano Santos", "/bookImages/dracula_book.jpg", 3));
        books.insertNOrder(new Book("Odyssey", "Adriano Santos", "/bookImages/odyssey_book.jpg", 3));
        books.insertNOrder(new Book("Percy Jackson", "Adriano Santos", "/bookImages/percy_book.jpg", 3));
        books.insertNOrder(new Book("Howl", "Adriano Santos", "/bookImages/howl_Book.jpg", 3));
        books.insertNOrder(new Book("Percy Jackson", "Adriano Santos", "/bookImages/percy_book.jpg", 3));
        books.insertNOrder(new Book("Call of the Wild", "Adriano Santos", "/bookImages/call_Book.png", 3));
        books.insertNOrder(new Book("Dracula", "Adriano Santos", "/bookImages/dracula_book.jpg", 3));
        books.insertNOrder(new Book("Odyssey", "Adriano Santos", "/bookImages/odyssey_book.jpg", 3));
        books.insertNOrder(new Book("Percy Jackson", "Adriano Santos", "/bookImages/percy_book.jpg", 3));
        books.insertNOrder(new Book("Howl", "Adriano Santos", "/bookImages/howl_Book.jpg", 3));
        books.insertNOrder(new Book("Percy Jackson", "Adriano Santos", "/bookImages/percy_book.jpg", 3));
        books.insertNOrder(new Book("Call of the Wild", "Adriano Santos", "/bookImages/call_Book.png", 3));
        books.insertNOrder(new Book("Dracula", "Adriano Santos", "/bookImages/dracula_book.jpg", 3));
        books.insertNOrder(new Book("Odyssey", "Adriano Santos", "/bookImages/odyssey_book.jpg", 3));
        books.insertNOrder(new Book("Percy Jackson", "Adriano Santos", "/bookImages/percy_book.jpg", 3));
        books.insertNOrder(new Book("Howl", "Adriano Santos", "/bookImages/howl_Book.jpg", 3));
        books.insertNOrder(new Book("Percy Jackson", "Adriano Santos", "/bookImages/percy_book.jpg", 3));
        books.insertNOrder(new Book("Call of the Wild", "Adriano Santos", "/bookImages/call_Book.png", 3));
        books.insertNOrder(new Book("Dracula", "Adriano Santos", "/bookImages/dracula_book.jpg", 3));
        books.insertNOrder(new Book("Odyssey", "Adriano Santos", "/bookImages/odyssey_book.jpg", 3));
        books.insertNOrder(new Book("Percy Jackson", "Adriano Santos", "/bookImages/percy_book.jpg", 3));
        books.insertNOrder(new Book("Howl", "Adriano Santos", "/bookImages/howl_Book.jpg", 3));
        books.insertNOrder(new Book("Percy Jackson", "Adriano Santos", "/bookImages/percy_book.jpg", 3));

        return books;
    }

    //To retrieve the range of books
    public DoublyLinkList copyValueList(DoublyLinkList books, int atStart, int atEnd) {
        DoublyLinkList list = new DoublyLinkList(); //Store the copy here

        if(atStart < 0) { //Invalid value of start and end
            Alert alert = new Alert(Alert.AlertType.WARNING, "Library View Copy Error");
            return list;
        }else {
            //Go to the desired start of list
            Link current = books.getFirst();
            int i; //index

            for(i = 0; i<atStart; i++) {
                current = current.getNext();
            }


            //Add the range until reaches the end
            for(; current!=null && i < atEnd; i++) {        //Copy the value to list
                list.insertNOrder(current.getElement());
                current = current.getNext();
            }
        }
        return list;
    }
}
