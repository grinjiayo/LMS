package Entity;

import java.sql.Date;

public class Transact {
    private int transID;
    private int borrowerID; //ID of the borrowed student
    private int bookID;
    private Date borrowDate;
    private double penalty;
    private Date paidDate;

    public Transact(int transID, int borrowerID, int bookID, Date borrowDate) {
        this.transID = transID;
        this.borrowerID = borrowerID;
        this.bookID = bookID;
        this.borrowDate = borrowDate;
    }

    public Transact(int transID, int borrowerID, int bookID, Date borrowDate, double penalty, Date paidDate) {
        this.transID = transID;
        this.borrowerID = borrowerID;
        this.bookID = bookID;
        this.borrowDate = borrowDate;
    }

    public int getTransID() {
        return transID;
    }

    public void setTransID(int transID) {
        this.transID = transID;
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
}
