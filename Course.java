
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
//import java.sql.Time;
//import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Course implements Serializable {
    private String CourseCode; // changed int to string cuz eg. CE2001
    private String CourseName;
    private String CourseDescription;
    private int AU;
    private ArrayList<Cindex> ListCindex;
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
        return ListCindex;
    }

    public void setListCindex(ArrayList<Cindex> listCindex) {
        ListCindex = listCindex;
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

    public ArrayList<Student> getStudentList(String coursecode) {
        Course singleIndex = searchSingleCourse(coursecode);

        return singleIndex.getRegisteredStudents();
    }

    private Course searchSingleCourse(String courseCode) {
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

    public static void main(String[] args) {
        // mock data

        Course course1 = new Course("CE2001", "Algorithms", "description");
        Course course2 = new Course("CE2005", "Operating Systems", "description");
        Course course3 = new Course("CE2006", "Software Engineering", "description");
        Course course4 = new Course("CE1007", "Data Structures", "description");
        Course course5 = new Course("CE1105", "Digital Logic", "description");

        ArrayList<Course> courseList = new ArrayList<Course>();
        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);
        courseList.add(course4);
        courseList.add(course5);

        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.SerializeCourseList(courseList);
        courseList = null;
        courseList = databaseManager.DeserializeCourseList();

        ListIterator<Course> ListItr = courseList.listIterator();
        while (ListItr.hasNext()) {
            System.out.println("index:" + ListItr.nextIndex() + " value:" + ListItr.next().CourseDescription);
        }

    }

}
