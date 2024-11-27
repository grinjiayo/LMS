package Function;

import javax.swing.*;
import java.util.Date;

public class Function {
    Date dateNow = new Date();
    int year = dateNow.getYear();

    public boolean passLengthChecker(String s) {
        if(s.length()>=8) return true;
        else return false;
    }

    public boolean digitChecker(String s) {
        for(int i = 0; i<s.length(); i++) {
            if(Character.isDigit(s.charAt(i))==false) {
                return false;
            }
        }
        return true;
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

    public boolean staffIDChecker(String s) {
        return true;
//        int i = 0;
//        if(s.charAt(i)=='S') {  //The staffID should start at 0
//        }else return false;
    }
}
