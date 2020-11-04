

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

public class Course implements Serializable{
    private String CourseCode;         // changed int to string cuz eg. CE2001
    private String CourseName;
    private String CourseDescription;
    private static int AUawarded;
    private ArrayList<Cindex> ListCindex;

    public Course(String cc, String cn, String d, int AU) {
        CourseCode = cc;
        CourseName = cn;
        CourseDescription = d;
        AUawarded = AU;
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

    public void setAU(int au){
        AUawarded = au;
    }

    public static int getAU(){
        return AUawarded;
    }

    public Cindex[] getListCindex() {
        return ListCindex;
    }

    public void setListCindex(Cindex[] listCindex) {
        ListCindex = listCindex;
    }

    private static void SerializeCourseList(List<Course> courseList){
        FileOutputStream fileOut ;
        ObjectOutputStream out;
        try {
            fileOut = new FileOutputStream("course.ser");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(courseList);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved\n");
          } catch (IOException i) {
            i.printStackTrace();
          }
    
    }
    
    private static List<Course> DeserializeCourseList() {
        try{
         List<Course> courseList2;
         FileInputStream fileIn = new FileInputStream("course.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         courseList2 = (List<Course>) in.readObject();
         in.close();
         fileIn.close();
          return courseList2;
       } catch (IOException i) {
         i.printStackTrace();
       } catch (ClassNotFoundException e) {
         e.printStackTrace();
       }
       return null;
    }
    
    public static void main(String[] args) {
        //mock data
        
        Course course1 = new Course("CE2001", "Algorithms", "description");
        Course course2 = new Course("CE2005", "Operating Systems", "description");
        Course course3 = new Course("CE2006", "Software Engineering", "description");
        Course course4 = new Course("CE1007", "Data Structures", "description");
        Course course5 = new Course("CE1105", "Digital Logic", "description");
    
        List<Course> courseList = new ArrayList<Course>();
        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);
        courseList.add(course4);
        courseList.add(course5);
    
        SerializeCourseList(courseList);
        courseList = null;
        courseList = DeserializeCourseList();
    
        ListIterator<Course> ListItr = courseList.listIterator();
        while (ListItr.hasNext()) {
           System.out.println("index:" + ListItr.nextIndex() + " value:" + ListItr.next().CourseDescription);
        }
    
    }

    private static Course searchSingleCourse(String courseCode){
        List<Course> courseList= null;

        courseList = DeserializeCourseList();

        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseCode().equals(courseCode) )
                return courseList.get(i);
        }

        return null;
    }

    public static String getCourseDescription( String courseCode) {
        Course singleCourse = searchSingleCourse(courseCode);
        return singleCourse.getCourseDescription();
    }
}

