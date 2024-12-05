package Function;

import java.sql.*;
import java.util.LinkedList;

import Entity.Book;
import LinkedList.*;

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
                String imageSrc = rs.getString("imageSrc");
                int isbn = rs.getInt("isbn");
                String category = rs.getString("category_id");
                int quantity = rs.getInt("quantity");
                int borrowed = rs.getInt("borrowed");   //0 if available, signify the number of books borrowed

                Book nBook = new Book(title, author, category, imageSrc, isbn, quantity, borrowed);

                //Insert books in order
                books.insertNOrder(nBook);
            }
            return books;
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void resetAutoIncrement(Connection conn, String table, String column) {
        try {
            int id = 0;
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
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();

        }
    }
}
