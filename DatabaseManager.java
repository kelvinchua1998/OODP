import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

   String FILEPATH = "database\\";

   public void SerializeCourseList(ArrayList<Course> courseList) {
      FileOutputStream fileOut;
      ObjectOutputStream out;
      try {
         fileOut = new FileOutputStream(FILEPATH + "course.ser");
         out = new ObjectOutputStream(fileOut);
         out.writeObject(courseList);
         out.close();
         fileOut.close();
         System.out.println("Serialized data is saved");
      } catch (IOException i) {
         i.printStackTrace();
      }

   }

   public ArrayList<Course> DeserializeCourseList() {
      try {
        ArrayList<Course> courseList2;
         FileInputStream fileIn = new FileInputStream(FILEPATH + "course.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         courseList2 = (ArrayList<Course>) in.readObject();
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

   public void SerializeAdminList(ArrayList<Admin> adminList) {
      try {
         FileOutputStream fileOut = new FileOutputStream(FILEPATH + "admin.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(adminList);
         out.close();
         fileOut.close();
         System.out.println("Serialized data is saved");
          } catch (IOException i) {
         i.printStackTrace();
      }
   }

   public ArrayList<Admin> DeserializeAdminList() {
      try {
        ArrayList<Admin> adminList2;
         FileInputStream fileIn = new FileInputStream(FILEPATH + "admin.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         adminList2 = (ArrayList<Admin>) in.readObject();
         in.close();
         fileIn.close();
         return adminList2;
      } catch (IOException i) {
         i.printStackTrace();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
      return null;
   }


   public void SerializeStudentList(ArrayList<Student> studentList) {
      try {
         FileOutputStream fileOut = new FileOutputStream(FILEPATH + "student.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(studentList);
         out.close();
         fileOut.close();
         System.out.println("Serialized data is saved");
      } catch (IOException i) {
         i.printStackTrace();
      }
   }

   public ArrayList<Student> DeserializeStudentList() {
      try {
        ArrayList<Student> studentsList2;
         FileInputStream fileIn = new FileInputStream(FILEPATH + "student.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         studentsList2 = (ArrayList<Student>) in.readObject();
         in.close();
         fileIn.close();
         return studentsList2;
      } catch (IOException i) {
         i.printStackTrace();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
      return null;
   }


   public int getVacancyCindex(String coursecode, String index) {
      Cindex singleIndex = searchCindex(coursecode, index);

      if (singleIndex != null) {
          return singleIndex.getCurrentVacancy();
      } else {
          return -1;
      }
  }

  public Cindex searchCindex(String coursecode, String Cindex) {
   ArrayList<Course> courseList = new ArrayList<Course>();

   DatabaseManager databaseManager = new DatabaseManager();
   courseList = databaseManager.DeserializeCourseList();

   for (int i = 0; i < courseList.size(); i++) {
       if (courseList.get(i).getCourseCode().equals(coursecode)) {
           Course courseObj = courseList.get(i);
           ArrayList<Cindex> CindexObj = courseObj.getListCindex();
           for (int j = 0; j < CindexObj.size(); j++) {
               if (CindexObj.get(j).equals(Cindex)) {
                   return CindexObj.get(j);
               }
           }

       }
   }

   return null;

}

   public ArrayList<Student> getStudentList(String coursecode, String index) {
      Cindex singleIndex = searchCindex(coursecode, index);

      return singleIndex.getRegisteredStudents();
   }

   //for print student list by course
   public Course searchCourse(String coursecode) {
      ArrayList<Course> courseList = new ArrayList<Course>();
   
      DatabaseManager databaseManager = new DatabaseManager();
      courseList = databaseManager.DeserializeCourseList();
   
      for (int i = 0; i < courseList.size(); i++) {
          if (courseList.get(i).getCourseCode().equals(coursecode)) {
              Course courseObj = courseList.get(i);
              return courseObj;
          }
      }
   
      return null;
   
   }

   public ArrayList<Student> getStudentList(String coursecode){
      Course singleCourse = searchCourse(coursecode);

      return singleCourse.getRegisteredStudents();
   }
}
