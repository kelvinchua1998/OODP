package classes;
import java.io.Serializable;
/**
 * StudentCourse class that is used to store the required information in the student's registered List and waitlist
 * @author kelvin melvin
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
     * default constructor for the student course
     * @param courseCode course code of the student Course
     * @param courseName course name of the student Course
     * @param courseDescription course description of the student Course
     * @param index index of the student Course
     */
    public StudentCourse(String courseCode, String courseName, String courseDescription, String index)  {
        CourseCode = courseCode;
        CourseName = courseName;
        CourseDescription = courseDescription;
        courseIndex = index;
    }
/**
 * returns the courseCode of the course
 * @return course code
 */
    public String getCourseCode() {
        return CourseCode;
    }
/**
 * sets the courseCode of the course
 * @param courseCode course code to be set
 */
    public void setCourseCode(String courseCode) {
        CourseCode = courseCode;
    }
/**
 * returns the course name of the course
 * @return course neme of the course
 */
    public String getCourseName() {
        return CourseName;
    }
/**
 * sets the course name of the course
 * @param courseName course name to be set
 */
    public void setCourseName(String courseName) {
        CourseName = courseName;
    }
/**
 * returns the course description of the course
 * @return course description
 */
    public String getCourseDescription() {
        return CourseDescription;
    }
/**
 * set the course description of the course
 * @param courseDescription dexcription to be set
 */
    public void setCourseDescription(String courseDescription) {
        CourseDescription = courseDescription;
    }
/**
 * returns the number of AUs for the course
 * @return the number of AUs for the course
 */
    public int getAUawarded() {
        return AU;
    }
/**
 * set the number of AUs for the course
 * @param auAwarded set the number of AUs for this course
 */
    public void setAUawarded(int auAwarded) {
        this.AU = auAwarded;
    }
/**
 * returns the course index for the course
 * @return course index
 */
    public String getCourseIndex() {
        return courseIndex;
    }
/**
 * sets the course index of the course
 * @param index index to be set
 */
    public void setCourseIndex(String index) {
        this.courseIndex = index;
    }
}
