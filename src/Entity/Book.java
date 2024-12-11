package Entity;

import javax.swing.*;
import java.io.File;

public class Book {
    private File f = null;
    private String path = null;
    private ImageIcon format = null;
    private byte[] pImage = null;

    private String title;
    private String author;
    private String category;
    private String imageSrc;
    private int ISBN;
    private int quantity;
    private int borrowed;

    public Book(String title, String author, String category, String imageSrc, int isbn, int qty, int borrowed) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.ISBN = isbn;
        this.quantity = qty;
        this.borrowed = borrowed;
        this.imageSrc = imageSrc;
    }

    public Book(String title, String author, String imageSrc, int quantity) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.imageSrc = imageSrc;
        String genre = null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return category;
    }

    public void setGenre(String genre) {
        this.category = genre;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(int borrowed) {
        this.borrowed = borrowed;
    }

    public Object getCategory() {
        return category;
    }

    public void setCategory(Object category) {
        this.category = category.toString();
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }
}
