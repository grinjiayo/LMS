package Entity;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

import javax.swing.event.DocumentEvent;
import java.awt.*;
import java.sql.Date;

public class Transact {
    private int transID;
    private int borrowerID; //Borrower
    private String borrowerName;
    private String bookTitle;   //Borrowed
    private String bkIsbn;
    private int bookID;
    private Date borrowDate;
    private String status; //Pending, Borrowed, Returned, Paid
    private double penalty;
    private Date paidDate;

    private Button acceptBtn;
    private Button declineBtn;

    public Transact(int transID, String title, String ISBN, int borrowerID, String borrowerName, int bookID, String status) {
        setButton();
        this.transID = transID;
        this.bookTitle = title;
        this.bkIsbn = ISBN;
        this.borrowerID = borrowerID;
        this.borrowerName = borrowerName;
        this.bookID = bookID;
        this.status = status;
    }

    public int getTransID() {
        return transID;
    }

    public void setTransID(int transID) {
        this.transID = transID;
    }

    public void setButton() {
        acceptBtn = new Button("Accept");
        acceptBtn.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Book Request", ButtonType.NO, ButtonType.YES);
            alert.setTitle("Borrow Request");
            alert.setHeaderText("Accept Borrow Request");
            alert.setContentText("Borrower Info\nBorrower ID:  " + borrowerID +
                    "\nBorrower Name:  " + borrowerName +
                    "\n\nBook Info \nTitle:  " + bookTitle +
                    "\nISBN:  " + bkIsbn
                    );

            if(alert.showAndWait().get() == ButtonType.YES) {
                this.status = "BORROWED";
            }else {
                alert.close();
            }
        });

        declineBtn = new Button("Decline");
        declineBtn.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.NONE, "Do you want to decline \nthe request?", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Borrow Request");

            if(alert.showAndWait().get() == ButtonType.YES) {
                this.status = "DECLINE";
            }else {
                alert.close();
            }
        });
    }

    public int getBorrowerID() {
        return borrowerID;
    }

    public void setBorrowerID(int borrowerID) {
        this.borrowerID = borrowerID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public Button getAcceptBtn() {
        return acceptBtn;
    }

    public void setAcceptBtn(Button acceptBtn) {
        this.acceptBtn = acceptBtn;
    }

    public Button getDeclineBtn() {
        return declineBtn;
    }

    public void setDeclineBtn(Button declineBtn) {
        this.declineBtn = declineBtn;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBkIsbn() {
        return bkIsbn;
    }

    public void setBkIsbn(String bkIsbn) {
        this.bkIsbn = bkIsbn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
