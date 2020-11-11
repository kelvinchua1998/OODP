import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {
    private String CourseCode; // changed int to string cuz eg. CE2001
    private String CourseName;
    private String CourseDescription;
    private int AU;
    private ArrayList<Cindex> listCindex;

    public Course(String cc, String cn, String d, int AU, ArrayList<Student> registeredStudents, ArrayList<Cindex> ListCindex) {
        this.CourseCode = cc;
        this.CourseName = cn;
        this.CourseDescription = d;
        this.AU = AU;
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

    
    
    public static void main(String[] args) {
        ArrayList<Course> courseList;
        ArrayList<Student> studentList;
        ArrayList<Cindex> CindexList;
        DatabaseManager databaseManager = new DatabaseManager();

        courseList = databaseManager.DeserializeCourseList();
        for(int i = 0; i<courseList.size(); i++){
            System.out.println(courseList.get(i).getCourseCode());   
            System.out.println(courseList.get(i).getCourseDescription());  
            System.out.println(courseList.get(i).getCourseName());  
        }
        CindexList = courseList.get(0).getListCindex();
        System.out.println(CindexList.get(0).getIndex());


        // studentList = databaseManager.DeserializeStudentList();
        // Student studentobj = studentList.get(0);
        // courseList.get(0).getRegisteredStudents().add(studentobj);

        // databaseManager.SerializeCourseList(courseList);
        

    }

}
