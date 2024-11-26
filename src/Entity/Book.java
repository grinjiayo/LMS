package Entity;

public class Book {
    private String title;
    private String author;
    private String category;
    private int ISBN;
    private int quantity;
    private int borrowed;

    public Book(String title, String author, String category, int isbn, int qty, int borrowed) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.ISBN = ISBN;
        this.quantity = quantity;
        this.borrowed = borrowed;
    }

    public Book(String title, String author, int quantity) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        int ISBN = 0;
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
}
