import java.io.Serializable;

public class StudentCourse implements Serializable{
    private String CourseCode; // changed int to string cuz eg. CE2001
    private String CourseName;
    private String CourseDescription;
    private int AU;
    private Cindex index;

    public StudentCourse(String courseCode, String courseName, String courseDescription, Cindex index)  {
        CourseCode = courseCode;
        CourseName = courseName;
        CourseDescription = courseDescription;
        this.index = index;
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

    public int getAUawarded() {
        return AU;
    }

    public void setAUawarded(int aUawarded) {
        this.AU = aUawarded;
    }

    public Cindex getIndex() {
        return index;
    }

    public void setIndex(Cindex index) {
        this.index = index;
    }

   

}
