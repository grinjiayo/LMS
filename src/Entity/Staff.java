package Entity;

public class Staff {
    private String fName;
    private String lName;
    private String email;
    private String pass;
    private int staffKey;

    public Staff(String fName, String lName, String email, String pass) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.pass = pass;
    }

    public Staff(String fName, String lName, String email, String pass, int staffKey) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.pass = pass;
        this.staffKey = staffKey;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getStaffKey() {
        return staffKey;
    }

    public void setStaffKey(int staffKey) {
        this.staffKey = staffKey;
    }
}
