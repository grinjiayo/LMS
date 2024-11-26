import Entity.Book;
import Function.dbFunction;
import LinkedList.DoublyLinkList;
import LinkedList.Link;

public class SimulationMain {



    public static void main(String[] args) {

        dbFunction dbFnc = new dbFunction();

//        DoublyLinkList books = new DoublyLinkList();
//
//        Book harryPotter = new Book("Harry Potter", "J.K Rowling", "Fantasy", 20288, 5, 0);
//        Book onePiece = new Book("One Piece", "Oda Sensei", "Manga", 20202, 2, 1);
//        Book noliMeTangere = new Book("Noli Me Tangere", "Dr. Jose Rizal", "Novel", 30012, 12, 3);
//        Book pBook = new Book("pBook", "Dr. Jose Rizal", "General", 20231, 12, 4);
//        Book aBook = new Book("aBook", "Dr. Jose Rizal", "General", 20232, 12, 5);
//        Book yBook = new Book("yBook", "Dr. Jose Rizal", "General", 20233, 12, 0);
//
//        books.insertNOrder(harryPotter);
//        books.insertNOrder(onePiece);
//        books.insertNOrder(noliMeTangere);
//        books.insertNOrder(pBook);
//        books.insertNOrder(aBook);
//        books.insertNOrder(yBook);
//
//        displayBookList(books);

        DoublyLinkList books = dbFnc.retrieveBooksnOrder();
        displayBookList(books);
    }

    public static void displayBookList(DoublyLinkList books) {

        if(books==null) return;

        Link first = books.getFirst();

        Link current = first;
        System.out.println("BOOKS");
        System.out.printf("%-15s %-15s %-15s %-10s %-10s %-10s %n", "Title", "Author", "Category", "ISBN", "Quantity", "Borrowed");
        while(current.getNext()!=null) {
            Book book = current.getElement();

            //Display the book details
            System.out.printf("%-15s %-15s %-15s %-10d %-10d %-10d %n", book.getTitle(), book.getAuthor(), book.getCategory(), book.getISBN(), book.getQuantity(), book.getBorrowed());

            current = current.getNext();
        }
    }
}
