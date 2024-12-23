package Function;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import Entity.*;
import LinkedList.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;

import javax.swing.*;

import static Function.globalVariable.fnc;

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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle(title);
        alert.show();
    }

    public boolean insertBookDB(Book book, String imgName) {
        try{
            conn = connectToDB();
            String sqlInsertBook = "INSERT INTO librarydb.book" +
                    "(title, author, isbn, ctgry, quantity, borrowed, imgID)" +
                    "VALUES (?, ?, ?, ?, ?, 0, ?)";
            pstmt = conn.prepareStatement(sqlInsertBook);
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getISBN());
            pstmt.setString(4, book.getCategory());
            pstmt.setInt(5, book.getQuantity());
            pstmt.setString(6, imgName);
            pstmt.executeUpdate();
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

    public String insertBookImageDB(Image img, String imgTitle) {
        String imgName = null;
        try {
            // Ensure the directory for storing images exists
            File directory = new File("src/bookImages");
            if (!directory.exists()) {
                directory.mkdirs(); // Create the directory if it doesn't exist
            }

            imgName = imgTitle.replaceAll("\\s+", "_") + ".png";

            // Define the file path for the image
            String imagePath = "src/bookImages/" + imgName;

            // Convert the JavaFX Image to a byte array
            byte[] imgBytes = fnc.convertImageToByteArray(img);

            // Save the byte array as a file in the directory
            File imageFile = new File(imagePath);
            try (FileOutputStream fos = new FileOutputStream(imageFile)) {
                fos.write(imgBytes);
            }

            System.out.println("Image saved as: " + imgName);
            return imgName; // Return the name of the image file
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.setTitle("InsertBkImgError");
            alert.show();
            System.out.println(e.getMessage());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.setTitle("InsertBkImgError");
            alert.show();
            System.out.println(e.getMessage());
        }
        return imgName;
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

    public int insertStaffDB(Staff staff) {
        int staffId = 0;
        try {
            Connection conn = connectToDB();
            staffId = resetAutoIncrement(conn, "staff", "staff_id");
            String sqlInsertStaff = "INSERT INTO librarydb.staff " +
                    "(staff_id, fName, lName, position, email, password) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertStaff);
            pstmt.setInt(1, staff.getStaffID());
            pstmt.setString(2, staff.getfName());
            pstmt.setString(3, staff.getlName());
            pstmt.setString(4, staff.getPosition());
            pstmt.setString(5, staff.getEmail());
            pstmt.setString(6, staff.getPass());

            pstmt.execute();
            return staffId + 1;
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("InsertStaffError", e.getMessage());
        }
        return staffId;
    }

    public void updateStudentDB(Student student) {
        try {
            Connection conn = connectToDB();
            String sqlUpdateStudent = "UPDATE librarydb.student SET " +
                    "fName = ?, lName = ?, section = ?, email = ?, password = ?, penalty = ? " +
                    "WHERE stud_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlUpdateStudent);
            pstmt.setString(1, student.getfName());
            pstmt.setString(2, student.getlName());
            pstmt.setString(3, student.getSection());
            pstmt.setString(4, student.getEmail());
            pstmt.setString(5, student.getPass());
            pstmt.setDouble(6, student.getPenalty());
            pstmt.setInt(7, student.getSchoolID());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("UpdateStudentError", e.getMessage());
        }
    }

    public void updateStaffDB(Staff staff) {
        try {
            Connection conn = connectToDB();
            String sqlUpdateStaff = "UPDATE librarydb.staff SET " +
                    "fName = ?, lName = ?, position = ?, email = ?, password = ? " +
                    "WHERE staff_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlUpdateStaff);
            pstmt.setString(1, staff.getfName());
            pstmt.setString(2, staff.getlName());
            pstmt.setString(3, staff.getPosition());
            pstmt.setString(4, staff.getEmail());
            pstmt.setString(5, staff.getPass());
            pstmt.setInt(6, staff.getStaffID());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("UpdateStaffError", e.getMessage());
        }
    }

    public void deleteStaffDB(int staffId) {
        try {
            Connection conn = connectToDB();
            String sqlDeleteStaff = "DELETE FROM librarydb.staff WHERE staff_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlDeleteStaff);
            pstmt.setInt(1, staffId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("DeleteStaffError", e.getMessage());
        }
    }

    public void deleteStudentDB(int studentId) {
        try {
            Connection conn = connectToDB();
            String sqlDeleteStudent = "DELETE FROM librarydb.student WHERE stud_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlDeleteStudent);
            pstmt.setInt(1, studentId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("DeleteStudentError", e.getMessage());
        }
    }

    public List<Staff> sortStaffByLastNameASC() {
        List<Staff> staffList = new ArrayList<>();
        try {
            Connection conn = connectToDB();
            String sqlSortStaff = "SELECT * FROM librarydb.staff ORDER BY lName ASC";
            PreparedStatement pstmt = conn.prepareStatement(sqlSortStaff);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Staff staff = new Staff();
                staff.setStaffID(rs.getInt("staff_id"));
                staff.setfName(rs.getString("fName"));
                staff.setlName(rs.getString("lName"));
                staff.setPosition(rs.getString("position"));
                staff.setEmail(rs.getString("email"));
                staff.setPass(rs.getString("password"));
                staffList.add(staff);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("SortStaffError", e.getMessage());
        }
        return staffList;
    }

    public List<Student> sortStudentsByLastNameASC() {
        List<Student> studentList = new ArrayList<>();
        try {
            Connection conn = connectToDB();
            String sqlSortStudents = "SELECT * FROM librarydb.student ORDER BY lName ASC";
            PreparedStatement pstmt = conn.prepareStatement(sqlSortStudents);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setSchoolID(rs.getInt("school_id"));
                student.setfName(rs.getString("fName"));
                student.setlName(rs.getString("lName"));
                student.setSection(rs.getString("section"));
                student.setEmail(rs.getString("email"));
                student.setPass(rs.getString("password"));
                student.setPenalty(rs.getDouble("penalty"));
                studentList.add(student);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("SortStudentsError", e.getMessage());
        }
        return studentList;
    }

    public List<Student> sortStudentsByLastNameDESC() {
        List<Student> studentList = new ArrayList<>();
        try {
            Connection conn = connectToDB();
            String sqlSortStudentsDesc = "SELECT * FROM librarydb.student ORDER BY lName DESC";
            PreparedStatement pstmt = conn.prepareStatement(sqlSortStudentsDesc);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setSchoolID(rs.getInt("school_id"));
                student.setfName(rs.getString("fName"));
                student.setlName(rs.getString("lName"));
                student.setSection(rs.getString("section"));
                student.setEmail(rs.getString("email"));
                student.setPass(rs.getString("password"));
                student.setPenalty(rs.getDouble("penalty"));
                studentList.add(student);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("SortStudentsDescError", e.getMessage());
        }
        return studentList;
    }

    public List<Staff> sortStaffByLastNameDESC() {
        List<Staff> staffList = new ArrayList<>();
        try {
            Connection conn = connectToDB();
            String sqlSortStaffDesc = "SELECT * FROM librarydb.staff ORDER BY lName DESC";
            PreparedStatement pstmt = conn.prepareStatement(sqlSortStaffDesc);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Staff staff = new Staff();
                staff.setStaffID(rs.getInt("staff_id"));
                staff.setfName(rs.getString("fName"));
                staff.setlName(rs.getString("lName"));
                staff.setPosition(rs.getString("position"));
                staff.setEmail(rs.getString("email"));
                staff.setPass(rs.getString("password"));
                staffList.add(staff);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("SortStaffDescError", e.getMessage());
        }
        return staffList;
    }


    public Student searchStudentBySchoolID(int schoolID) {
        Student student = null;
        try {
            Connection conn = connectToDB();
            String sqlSearchStudent = "SELECT * FROM librarydb.student WHERE school_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlSearchStudent);
            pstmt.setInt(1, schoolID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                student = new Student();
                student.setSchoolID(rs.getInt("school_id"));
                student.setfName(rs.getString("fName"));
                student.setlName(rs.getString("lName"));
                student.setSection(rs.getString("section"));
                student.setEmail(rs.getString("email"));
                student.setPass(rs.getString("password"));
                student.setPenalty(rs.getDouble("penalty"));
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("SearchStudentError", e.getMessage());
        }
        return student;
    }

    public Staff searchStaffByStaffID(int staffID) {
        Staff staff = null;
        try {
            Connection conn = connectToDB();
            String sqlSearchStaff = "SELECT * FROM librarydb.staff WHERE staff_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlSearchStaff);
            pstmt.setInt(1, staffID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                staff = new Staff();
                staff.setStaffID(rs.getInt("staff_id"));
                staff.setfName(rs.getString("fName"));
                staff.setlName(rs.getString("lName"));
                staff.setPosition(rs.getString("position"));
                staff.setEmail(rs.getString("email"));
                staff.setPass(rs.getString("password"));
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("SearchStaffError", e.getMessage());
        }
        return staff;
    }

    public ObservableList<Book> inventoryBookView() {
        try {
            conn = connectToDB();
            stmt = conn.createStatement();
            ObservableList<Book> inventoryBook = FXCollections.observableArrayList();
            String query = "SELECT * FROM book";
            // Execute the query
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                String category = rs.getString("ctgry");
                String ISBN = rs.getString("isbn");
                int quantity = rs.getInt("quantity");
                String imgID = rs.getString("imgID");
                int borrowed = rs.getInt("borrowed");

                Image bkImage = fnc.getImage(imgID);

                // Create a new book object and add it to the list
                Book book = new Book(title, author, category, bkImage, ISBN, quantity, borrowed);
                inventoryBook.add(book);
            }
            return inventoryBook;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
