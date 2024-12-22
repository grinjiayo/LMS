package Function;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

import Entity.Book;
import Entity.Category;
import Entity.Student;
import LinkedList.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;

import javax.swing.*;

public class dbFunction {
    Connection conn;
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs;

    public void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to find and load driver", "Driver Error", 0);
        }
    }

    public Connection connectToDB() {
        try{
            loadDriver();
            String url = "jdbc:mysql://localhost:3306/librarydb";
            String user = "root";
            String password = "";
            Connection connect = DriverManager.getConnection(url, user, password);
            return connect;
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<Category> retrieveCategories() {
        ArrayList<Category> categories = new ArrayList<Category>();
        try{
            conn = connectToDB();

            String sqlGetCategory = "SELECT ctgry_id, ctgry_name FROM bkcategory";
            pstmt = conn.prepareStatement(sqlGetCategory);

            //Retrieve the category starting from id 1
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("ctgry_id");
                String ctgryName = rs.getString("ctgry_name");
                categories.add(new Category(id, ctgryName));
                System.out.println("Inserted category" + id + ", " + ctgryName);
            }
        }catch(SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.setTitle("RetrieveCategoryError");
            alert.show();
        }catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.setTitle("RetrieveCategoryError");
            alert.show();
        }
        return categories;
    }

    //LINKED LIST FUNCTION
    public DoublyLinkList retrieveBooksnOrder() {
        try {
            DoublyLinkList books = new DoublyLinkList();

            conn = connectToDB();

            String sqlGetBook = "SELECT * FROM books";
            rs = pstmt.executeQuery(sqlGetBook);
            while(rs.next()) {
                //Retrieve all
                String title = rs.getString("title");
                String author = rs.getString("author");
                Image imageSrc =(Image) rs.getBlob("imageSrc");
                String isbn = rs.getString("isbn");
                String category = rs.getString("category");
                int quantity = rs.getInt("quantity");
                int borrowed = rs.getInt("borrowed");   //0 if available, signify the number of books borrowed

                Book nBook = new Book(title, author, category, imageSrc, isbn, quantity, borrowed);

                //Insert books in order
                books.insertNOrder(nBook);
            }
            return books;
        }catch(SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.setTitle("RetrieveBookError");
            alert.show();
        }catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.setTitle("RetrieveBookError");
            alert.show();
        }
        return null;
    }

    public int resetAutoIncrement(Connection conn, String table, String column) {
        int id = 0;
        try {
            Statement stmt = conn.createStatement();
            String sqlChecker = "SELECT MAX(" + column + ") FROM " + table + " ;";
            ResultSet rs = stmt.executeQuery(sqlChecker);

            if (rs.next() && rs.getString("MAX(" + column + ")") != null) {
                String maxID = rs.getString("MAX(" + column + ")");
                id = Integer.parseInt(maxID);
            } else {
                id = 0;
            }
            String sqlAlterInc = "ALTER TABLE " + table + " AUTO_INCREMENT=" + (id + 1) + ";";
            stmt.executeUpdate(sqlAlterInc);
            return id+1;
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.setTitle("ResetIncrementError");
            alert.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.setTitle("ResetIncrementError");
            alert.show();
        }
        return id+1;
    }

    public boolean insertBookDB(Book book, int imageID) {
        try{
            conn = connectToDB();
            String sqlInsertBook = "INSERT INTO librarydb.book" +
                    "(title, author, isbn, category_id, quantity, borrowed, imgID)" +
                    "VALUES (?, ?, ?, ?, ?, 0, ?)";
            pstmt = conn.prepareStatement(sqlInsertBook);
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getISBN());
            pstmt.setString(4, book.getCategory());
            pstmt.setBlob(5, (Blob) book.getImageSrc());
            pstmt.setInt(6, imageID);
            rs = pstmt.executeQuery();
            return true;
        }catch(SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.setTitle("InsertBookError");
            alert.show();
        }catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.setTitle("InsertBookError");
            alert.show();
        }
        return false;
    }

    public int insertBookImageDB(Image img) {
        int id = 0;
        try{
            conn = connectToDB();
            id = resetAutoIncrement(conn, "image", "imgID");
            String sqlInsertImage = "INSERT INTO librarydb.image(imgID, imgFile) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sqlInsertImage);
            pstmt.setInt(1, id);
            pstmt.setBlob(2, (Blob) img);
            pstmt.execute();
            return id;
        }catch(SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.setTitle("InsertBkImgError");
            alert.show();
        }catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.setTitle("InsertBkImgError");
            alert.show();
        }
        return id;
    }

    public int insertStudentDB(Student student) {
        int staffId = 0;
        try{
            conn = connectToDB();
            staffId = resetAutoIncrement(conn, "student", "stud_id");
            String sqlInsertStudent = "INSERT INTO librarydb.student" +
                    "(school_id, fName, lName, section, email, password, penalty)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sqlInsertStudent);
            pstmt.setInt(1, student.getSchoolID());
            pstmt.setString(2, student.getfName());
            pstmt.setString(3, student.getlName());
            pstmt.setString(4, student.getSection());
            pstmt.setString(5, student.getEmail());
            pstmt.setString(6, student.getPass());
            pstmt.setDouble(7, student.getPenalty());

            pstmt.execute();
            return staffId+1;
        }catch(SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.setTitle("InsertBkImgError");
            alert.show();
        }catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.setTitle("InsertBkImgError");
            alert.show();
        }
        return staffId;
    }
}
