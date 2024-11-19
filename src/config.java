import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class config {
    Connection conn;
    Statement stmt;

    public config() {
        createDB();
    }

    public void createDB() {
        try {
            //Establish connection
            String url = "jdbc:mysql://localhost:3306/";
            String user = "root";
            String password = "";
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            //Create database
            String sqlCreateDB = "CREATE DATABASE IF NOT EXISTS librarydb";
            stmt.executeUpdate(sqlCreateDB);
            System.out.println("Database created successfully");

            //Switched to library db
            String sqlUseDb = "USE librarydb";
            stmt.executeUpdate(sqlUseDb);
            System.out.println("Switched to database librarydb");

            //Create tables
            createTable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Create Database Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Create Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void createTable() {
        try {
            //Establish connection
            String url = "jdbc:mysql://localhost:3306/";
            String user = "root";
            String password = "";
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            //Create Book Table
            String sqlTableBook = "CREATE TABLE IF NOT EXISTS librarydb.book (" +
                    "BOOK_ID INT NOT NULL AUTO_INCREMENT, " +
                    "TITLE VARCHAR(64) NOT NULL, " +
                    "AUTHOR VARCHAR(64) NOT NULL, " +
                    "ISBN INT(13) NOT NULL, " +
                    "CATEGORY VARCHAR(64) NOT NULL, " +
                    "QUANTITY INT(24) NOT NULL, " +
                    "BORROWED INT NOT NULL," +             //0 If all available, more than 0 if there is borrowed
                    "PRIMARY KEY(BOOK_ID))";
            stmt.executeUpdate(sqlTableBook);
            System.out.println("Table 'book' created succesfully");

            String sqlTableTransact = "CREATE TABLE IF NOT EXISTS librarydb.transact (" +
                    "TRANS_ID INT NOT NULL AUTO_INCREMENT, " +
                    "BORROWER_ID INT NOT NULL, " +
                    "BOOK_ID INT NOT NULL, " +
                    "BORROW_DATE DATE NOT NULL, " +
                    "PENALTY_COST DOUBLE," +
                    "RETURN_DATE DATE, " +
                    "PRIMARY KEY(TRANS_ID))";
            stmt.executeUpdate(sqlTableTransact);
            System.out.println("Table 'transact' created succesfully");

            String sqlTableStaff = "CREATE TABLE IF NOT EXISTS librarydb.staff (" +
                    "STAFF_ID INT NOT NULL AUTO_INCREMENT, " +
                    "FNAME VARCHAR(64) NOT NULL, " +
                    "LNAME VARCHAR(64) NOT NULL, " +
                    "EMAIL VARCHAR(64) NOT NULL, " +
                    "PASSWORD VARCHAR(64) NOT NULL, " +
                    "PRIMARY KEY(STAFF_ID))";
            stmt.executeUpdate(sqlTableStaff);
            System.out.println("Table 'staff' created successfully");

            String sqlTableStudent = "CREATE TABLE IF NOT EXISTS librarydb.student (" +
                    "STUD_ID INT NOT NULL AUTO_INCREMENT, " +
                    "SCHOOL_ID INT (10) NOT NULL, " +
                    "FNAME VARCHAR(64) NOT NULL, " +
                    "LNAME VARCHAR(64) NOT NULL, " +
                    "SECTION VARCHAR(64) NOT NULL, " +
                    "EMAIL VARCHAR(64) NOT NULL, " +
                    "PASSWORD VARCHAR(64) NOT NULL, " +
                    "PENALTY DOUBLE NOT NULL, " +
                    "PRIMARY KEY(STUD_ID))";
            stmt.executeUpdate(sqlTableStudent);
            System.out.println("Table 'student' created successfully");

//            String sqlTableCollege = "CREATE TABLE IF NOT EXISTS librarydb.college (" +
//                    "COLLEGE_ID INT NOT NULL AUTO_INCREMENT, " +
//                    "NAME VARCHAR(64) NOT NULL, " +
//                    "PRIMARY KEY(COLLEGE_ID))";
//            stmt.executeUpdate(sqlTableCollege);
//            System.out.println("Table 'college' created successfully");
//
//            String sqlTableProgram = "CREATE TABLE IF NOT EXISTS librarydb.program (" +
//                    "PROG_ID INT NOT NULL AUTO_INCREMENT, " +
//                    "COLL_ID INT NOT NULL, " +
//                    "NAME VARCHAR(64) NOT NULL, " +
//                    "PRIMARY KEY(PROG_ID))";
//            stmt.executeUpdate(sqlTableProgram);
//            System.out.println("Table 'program' created successfully");

            insertSampleBook();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Create Table Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Create Table Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void insertSampleBook() {
        try {
            //Establish connection
            String url = "jdbc:mysql://localhost:3306/librarydb";
            String user = "root";
            String password = "";
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            String insertSql = "INSERT INTO book (TITLE, AUTHOR, ISBN, CATEGORY, QUANTITY, BORROWED) VALUES ('HarryPotter', 'JK Rowling', 01023840, 'SciFi', 12, 0)";
            String insertSql2 = "INSERT INTO book (TITLE, AUTHOR, ISBN, CATEGORY, QUANTITY, BORROWED) VALUES ('King', 'AntMan', 01023840, 'Fiction', 13, 1)";
            String insertSql3 = "INSERT INTO book (TITLE, AUTHOR, ISBN, CATEGORY, QUANTITY, BORROWED) VALUES ('Ashland', 'JK Rowling', 01023840, 'SciFi', 12, 0)";
            stmt.executeUpdate(insertSql);
            stmt.executeUpdate(insertSql2);
            stmt.executeUpdate(insertSql3);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "INSERT BOOK ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "INSERT BOOK ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(config::new);
    }
}


