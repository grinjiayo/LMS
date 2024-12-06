package stages.admin.library;

import LinkedList.DoublyLinkList;
import Entity.Book;
import LinkedList.Link;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class libraryController implements Initializable {

    @FXML
    private HBox bookLayout;
    @FXML
    private HBox bookLayout2;
    @FXML
    private HBox bookLayout3;

    private DoublyLinkList books;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            books = new DoublyLinkList(libraryBooks());
            Link first = books.getFirst();
            int count = 0;      //Indicates the number of books
            while (first != null) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/stages/admin/library/Book.fxml"));

                HBox bookBox = fxmlLoader.load();
                BookController bookController = fxmlLoader.getController();
                bookController.setData(first.getElement());
                if(count<14) {
                    bookLayout.getChildren().add(bookBox);
                }else if(count<28) {
                    bookLayout2.getChildren().add(bookBox);
                }else if(count<42) {
                    bookLayout3.getChildren().add(bookBox);
                }
                count++;
                first = first.getNext();
            }
        }catch(IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    private DoublyLinkList libraryBooks() {
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

        return books;
    }

    //To retrieve the range of books
    public DoublyLinkList copyValueList(DoublyLinkList books, int atStart, int atEnd) {
        DoublyLinkList list = new DoublyLinkList(); //Store the copy here

        if(atStart < 0 || atEnd> books.getSize()) { //Invalid value of start and end
            Alert alert = new Alert(Alert.AlertType.WARNING, "Library View Copy Error");
            return null;
        }else {
            //Go to the desired start of list
            Link current = books.getFirst();
            int i; //index

            for(i = 0; i<atStart; i++) {
                current = current.getNext();
            }

            //Add the range until reaches the end
            for(; i < atEnd; i++) {
                list.insertNOrder(current.getElement());
            }
        }
        return list;
    }
}
