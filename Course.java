import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {
    private String CourseCode; // changed int to string cuz eg. CE2001
    private String CourseName;
    private String CourseDescription;
    private int AU;
    private ArrayList<Cindex> listCindex;
    private ArrayList<Student> registeredStudents;

    public Course(String cc, String cn, String d, int AU, ArrayList<Student> registeredStudents, ArrayList<Cindex> ListCindex) {
        this.CourseCode = cc;
        this.CourseName = cn;
        this.CourseDescription = d;
        this.AU = AU;
        this.registeredStudents = registeredStudents;
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

    public String getCourseDescription(String courseCode) {
        Course singleCourse = searchSingleCourse(courseCode);
        return singleCourse.getCourseDescription();
    }

    public ArrayList<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public void setRegisteredStudents(ArrayList<Student> registeredStudents) {
        this.registeredStudents = registeredStudents;
    }

    public static ArrayList<Student> getStudentList(String coursecode) {
        Course singleIndex = searchSingleCourse(coursecode);

        return singleIndex.getRegisteredStudents();
    }

    private static Course searchSingleCourse(String courseCode) {
        ArrayList<Course> courseList = null;

        DatabaseManager databaseManager = new DatabaseManager();
        courseList = databaseManager.DeserializeCourseList();

        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseCode().equals(courseCode))
                return courseList.get(i);
        }

        return null;
    }

    // public void addNewCourse(String CourseCode, String CourseName, String CourseDescription, int AU){
    //     ArrayList<Student> studentList = new ArrayList<Student>();
    //     ArrayList<Cindex> CindexList = new ArrayList<Cindex>();

    //     Course courseObj = new Course(CourseCode,CourseName,CourseDescription,AU,studentList,CindexList);

    //     System.out.println("Add new index: ");
    //     System.out.println("0.Quit");
    //     System.out.println("1.Add new index");
    //     Scanner sc = new Scanner(System.in);
    //     int choice = -1;
        
    //     while(choice != 0){
    //         choice = sc.nextInt();
    //         switch(choice){
    //             case 0:
    //                 break;
    //             case 1:
    //                 addNewCindex();
    //         }
    //     }

    //     DatabaseManager databaseManager = new DatabaseManager();
    //     ArrayList<Course> courseList = databaseManager.DeserializeCourseList();
    //     courseList.add(courseObj);
    //     databaseManager.SerializeCourseList(courseList);
    //     System.out.println("Course added");
    // }

}
