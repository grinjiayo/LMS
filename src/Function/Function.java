package Function;

import LinkedList.DoublyLinkList;
import LinkedList.Link;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import Entity.*;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritablePixelFormat;

public class Function {
    Date dateNow = new Date();
    int year = dateNow.getYear();

    public boolean digitChecker(String s) {
        for(int i = 0; i<s.length(); i++) {
            if(Character.isDigit(s.charAt(i))==false) {
                return false;
            }
        }
        return true;
    }

    public int countBkQuantity(DoublyLinkList list) {
        int bookQuantity = 0;
        Link current = list.getFirst();
        while(current!=null) {
            Book book = current.getElement();
            bookQuantity += book.getQuantity();
            current=current.getNext();
        }
        return bookQuantity;
    }

    public String retrieveStudentID(String ID)  {       //2024-00001
        String studentID = null;
        if (!ID.isEmpty()) {
            if (ID.matches("\\d{4}-\\d{5}")) {
                studentID = ID.replace("-", "");
            } else {
                return null;
            }
        } else {
            return null;
        }
        return studentID;
    }

    public byte[] convertImageToByteArray(Image image) {
        try {
            // Get image width and height
            int width = (int) image.getWidth();
            int height = (int) image.getHeight();

            // Create a pixel reader
            PixelReader pixelReader = image.getPixelReader();

            // Byte array output stream to store pixel data
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            // Create a buffer to store raw pixel data
            byte[] buffer = new byte[width * height * 4]; // Assuming ARGB format (4 bytes per pixel)
            WritablePixelFormat<ByteBuffer> pixelFormat = WritablePixelFormat.getByteBgraInstance();

            // Read pixels from the image
            pixelReader.getPixels(0, 0, width, height, pixelFormat, buffer, 0, width * 4);

            // Write the buffer to the output stream
            outputStream.write(buffer);

            // Return the byte array
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] arrayListToStringArray(ArrayList<String> strs) {
        String[] strings = new String[strs.size()];
        for(int i = 0; i<strs.size(); i++) {
            strings[i] = strs.get(i);
        }
        return strings;
    }

    public int countUniBkQuantity(DoublyLinkList list) {
        int uniqueBook = 0;
        Link current = list.getFirst();
        while(current!=null) {
            uniqueBook++;
            current = current.getNext();
        }
        return uniqueBook;
    }

    public String getDateNowStr() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public Date getDateNow() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        Date date = new Date(dtf.format(now));
        return date;
    }

    public int countBkBorrow(DoublyLinkList list) {
        int bookBorrow = 0;
        Link current = list.getFirst();
        while(current!=null) {
            Book book = current.getElement();
            bookBorrow += book.getBorrowed();
            current = current.getNext();
        }
        return bookBorrow;
    }

    public int studentIDChecker(String s) { //Returns the int value of id otherwise 0;
        int studentId = 0;
        int idYrInt, idInt;
        String idYrStr, idStr;
        boolean validYr = false, validId = false;

        //Checks the year
        idYrStr = s.substring(0, 4);
        System.out.println(idYrStr);
        if(digitChecker(idYrStr)) {    //Valid year
            idYrInt = Integer.parseInt(idYrStr);
            if(idYrInt>1900 && idYrInt<year) {  //Year is greater than 1900 and less than this year/ VALID
                validYr = true;
            }else  {        //INVALID year
                JOptionPane.showMessageDialog(null,
                        "ID year should be between 1900-"+year,
                        "Invalid ID format", 0);
                validYr= false;
            }
        }else {     //Year is not all digits
            validYr=false;
            JOptionPane.showMessageDialog(null,
                    "ID format should be (YYYY-NNNNN)",
                    "Invalid ID format", 0);
        }

        if(validYr) {   //Checks the (-id)
            if(s.charAt(4)=='-') {  //Valid apostrophe
                idStr = s.substring(5);
                System.out.println(idStr);
                if(digitChecker(idStr)) {
                    idInt = Integer.parseInt(idStr);

                    //Returns the ID cause valid
                    String idString = idYrStr + idStr;
                    studentId = Integer.parseInt(idString);

                    validId = true;
                }
            }else { //Wrong apostrophe
                validId = false;
                JOptionPane.showMessageDialog(null,
                        "ID format should be (YYYY-NNNNN)",
                        "Invalid ID format", 0);
            }
        }

        return studentId;
    }

    public boolean staffIDChecker(String s) {   //Correct format lName+staffID
        String pattern = "^[a-zA-Z]+\\d{3}$";
        return s.matches(pattern);
    }

    public boolean passwordChecker(String s) { //password should have letter and a number or special character greater than 8 chars
        if (s.length() < 8) {
            return false;
        }
        String pattern = "^(?=.*[a-zA-Z])(?=.*[\\d\\W]).+$";
        return s.matches(pattern);
    }

    public DoublyLinkList selectCategoryBooks(Category ctgry) {
        DoublyLinkList bookList = globalVariable.bookList;
        DoublyLinkList categoryList = new DoublyLinkList();

        if(bookList.isEmpty())  return null;

        Link current = bookList.getFirst();
        while (current != null) {
            Book bk = current.getElement();
            if (bk.getCategory().equals(ctgry)) {
                categoryList.insertNOrder(bk);
            }
            current = current.getNext(); // Advance to the next link
        }

        return categoryList;
    }
}
