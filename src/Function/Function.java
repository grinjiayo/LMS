package Function;

public class Function {

    public boolean emailChecker(String s) { //returns true if the email contains @ otherwise false
        char[] charEmail = s.toCharArray();
        int i = 0;
        boolean isAt = false;

        for(i=0; isAt==false; i++) {
            System.out.println("ch-" + charEmail[i] + " ");

            if (Character.isLetterOrDigit(charEmail[i]) == true) {       //Checks if it is letter or digit and stops if reached @
                continue;
            }else if(charEmail[i]=='@'){    //is End of emailName
                isAt=true;
            }else {
                System.out.println("INVALID: Invalid email format");
                return false;
            }

            //Reach the end of email without @
            if(i==charEmail.length && charEmail[i]!='@') {
                return false;
            }
        }

        for(; i<charEmail.length; i++) {
            System.out.println("ch2-" + charEmail[i] + " ");

            if(Character.isLetterOrDigit(charEmail[i])==true || charEmail[i]=='.') {
                continue;
            }else {
                System.out.println("INVALID: Incorrect email format");
                return false;
            }
        }
        return true;
    }

    public boolean passLengthChecker(String s) {
        if(s.length()>=8) return true;
        else return false;
    }

    public boolean staffIDChecker(String s) {
        return true;
//        int i = 0;
//        if(s.charAt(i)=='S') {  //The staffID should start at 0
//            f
//        }else return false;
    }

    public boolean digitChecker(String s) {
        for(int i = 0; i<s.length(); i++) {
            if(Character.isDigit(s.charAt(i))==false) {
                return false;
            }
        }
        return true;
    }
}
