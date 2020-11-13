import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    private String CourseCode; // changed int to string cuz eg. CE2001
    private String CourseName;
    private String CourseDescription;
    private String School;
    private int AU;
    private ArrayList<Cindex> listCindex;

    public Course(String cc, String cn, String d,String school, int AU, ArrayList<Student> registeredStudents, ArrayList<Cindex> ListCindex) {
        this.CourseCode = cc;
        this.CourseName = cn;
        this.CourseDescription = d;
        this.AU = AU;
        this.School = school;
    }


    public String getCourseCode() {
        return CourseCode;
    }

    public void setCourseCode(String courseCode) {
        CourseCode = courseCode;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getCourseDescription() {
        return CourseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        CourseDescription = courseDescription;
    }

    public void setAU(int au) {
        this.AU = au;
    }

    public int getAU() {
        return this.AU;
    }

    public ArrayList<Cindex> getListCindex() {
        return listCindex;
    }

    public void setListCindex(ArrayList<Cindex> Cindex) {
        listCindex = Cindex;
    }

    public String getSchool() {
        return School;
    }

    public void setSchool(String school) {
        School = school;
    }
    
    public static void main(String[] args) {
        ArrayList<Course> courseList = new ArrayList<Course>();
        DatabaseManager databaseManager = new DatabaseManager();

        databaseManager.SerializeCourseList(courseList);

    }

}
