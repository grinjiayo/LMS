package Function;

import LinkedList.DoublyLinkList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class globalVariable {

    public static Function fnc = new Function();
    public static dbFunction dbFnc = new dbFunction();

    public static Group cabinets = new Group();
    public static DoublyLinkList bookList = new DoublyLinkList();

    public static void refresh() {  //Refreshes the book in the bookList

    }

}
