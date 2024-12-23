package Entity;

public class Staff {
    private int StaffID;
    private String fName;
    private String lName;
    private String email;
    private String pass;
    private String position;


    public Staff(int StaffID,String fName, String lName, String email, String pass, String position) {
        this.StaffID = StaffID;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.pass = pass;
        this.position = position;
    }

    public Staff(String fName, String lName, String email, String pass, int StaffID, String position) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.pass = pass;
        this.StaffID = StaffID;
        this.position = position;
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

    public int getStaffID() {
        return StaffID;
    }

    public void setStaffID(int StaffID) {
        this.StaffID = StaffID;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
