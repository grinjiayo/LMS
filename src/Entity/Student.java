package Entity;

public class Student {
    private String fName;
    private String lName;
    private int collegeID;
    private int programID;
    private int yearLvl;
    private String email;
    private String pass;
    private double penalty;
    private String studentID;

    public Student (String studentID, String fName, String lName, int college, int program, int yearLvl, String email,
                    String pass, double penalty) {
        this.fName = fName;
        this.lName = lName;
        this.collegeID = college;
        this.programID = program;
        this.yearLvl = yearLvl;
        this.email = email;
        this.pass = pass;
        this.penalty = penalty;
        this.studentID = studentID;
    }

    public Student (String studentID, String fName, String lName, int college, int program, int yearLvl, String email,
                    String pass) {
        this.fName = fName;
        this.lName = lName;
        this.collegeID = college;
        this.programID = program;
        this.yearLvl = yearLvl;
        this.email = email;
        this.pass = pass;
        this.studentID = studentID;
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

    public int getCollege() {
        return collegeID;
    }

    public void setCollege(int college) {
        this.collegeID = college;
    }

    public int getProgram() {
        return programID;
    }

    public void setProgram(int program) {
        this.programID = program;
    }

    public int getYearLvl() {
        return yearLvl;
    }

    public void setYearLvl(int yearLvl) {
        this.yearLvl = yearLvl;
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

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
}
