public class StudentCourse {
    private String CourseCode; // changed int to string cuz eg. CE2001
    private String CourseName;
    private String CourseDescription;
    private static int AUawarded;
    private Cindex index;

    public StudentCourse(String courseCode, String courseName, String courseDescription, Cindex index) {
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

    public static int getAUawarded() {
        return AUawarded;
    }

    public static void setAUawarded(int aUawarded) {
        AUawarded = aUawarded;
    }

    public Cindex getIndex() {
        return index;
    }

    public void setIndex(Cindex index) {
        this.index = index;
    }

   

}
