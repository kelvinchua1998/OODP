import java.io.Serializable;
/**
 * StudentCourse class that is used to store the required information in the student's registered List and waitlist
 */
public class StudentCourse implements Serializable{
    /**
     * courseCode of the course
     */
    private String CourseCode;
    /**
     * course name of the course
     */
    private String CourseName;
    /**
     * course description of the course
     */
    private String CourseDescription;
    /**
     * the number of AUs for the course
     */
    private int AU;
    /**
     * the index which the student is registered or in wailist for the particular course
     */
    private String courseIndex;

    /**
     * default constructor for the StudentCourse class
     */
    public StudentCourse(String courseCode, String courseName, String courseDescription, String index)  {
        CourseCode = courseCode;
        CourseName = courseName;
        CourseDescription = courseDescription;
        courseIndex = index;
    }
/**
 * returns the courseCode of the course
 * @return string
 */
    public String getCourseCode() {
        return CourseCode;
    }
/**
 * sets the courseCode of the course
 * @param courseCode
 */
    public void setCourseCode(String courseCode) {
        CourseCode = courseCode;
    }
/**
 * returns the course name of the course
 * @return string
 */
    public String getCourseName() {
        return CourseName;
    }
/**
 * sets the course name of the course
 * @param courseName
 */
    public void setCourseName(String courseName) {
        CourseName = courseName;
    }
/**
 * returns the course description of the course
 * @return string
 */
    public String getCourseDescription() {
        return CourseDescription;
    }
/**
 * set the course description of the course
 * @param courseDescription
 */
    public void setCourseDescription(String courseDescription) {
        CourseDescription = courseDescription;
    }
/**
 * returns the number of AUs for the course
 * @return int
 */
    public int getAUawarded() {
        return AU;
    }
/**
 * set the number of AUs for the course
 */
    public void setAUawarded(int aUawarded) {
        this.AU = aUawarded;
    }
/**
 * reutrns the course index for the course
 * @return string
 */
    public String getCourseIndex() {
        return courseIndex;
    }
/**
 * sets the course index of the course
 * @param index
 */
    public void setCourseIndex(String index) {
        this.courseIndex = index;
    }
}
