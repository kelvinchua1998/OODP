import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
         System.out.printf("Serialized data is saved\n");
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

   public void SerializeCindexList(ArrayList<Cindex> cindexList) {
      FileOutputStream fileOut;
      ObjectOutputStream out;
      try {
         fileOut = new FileOutputStream(FILEPATH + "cindex.ser");
         out = new ObjectOutputStream(fileOut);
         out.writeObject(cindexList);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved\n");
      } catch (IOException i) {
         i.printStackTrace();
      }

   }

   public ArrayList<Cindex> DeserializeCindexList() {
      try {
        ArrayList<Cindex> cindexList2;
         FileInputStream fileIn = new FileInputStream(FILEPATH + "cindex.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         cindexList2 = (ArrayList<Cindex>) in.readObject();
         in.close();
         fileIn.close();
         return cindexList2;
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
         System.out.printf("Serialized data is saved\n");
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

   public void SerializeLabsList(ArrayList<Labs> labsList) {
      FileOutputStream fileOut;
      ObjectOutputStream out;
      try {
         fileOut = new FileOutputStream(FILEPATH + "labs.ser");
         out = new ObjectOutputStream(fileOut);
         out.writeObject(labsList);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved\n");
      } catch (IOException i) {
         i.printStackTrace();
      }
   }

   public ArrayList<Labs> DeserializeLabsList() {
      try {
        ArrayList<Labs> labsList2;
         FileInputStream fileIn = new FileInputStream(FILEPATH + "labs.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         labsList2 = (ArrayList<Labs>) in.readObject();
         in.close();
         fileIn.close();
         return labsList2;
      } catch (IOException i) {
         i.printStackTrace();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
      return null;
   }

   public void SerializeLectureList(ArrayList<Lecture> lectureList) {
      FileOutputStream fileOut;
      ObjectOutputStream out;
      try {
         fileOut = new FileOutputStream(FILEPATH + "lecture.ser");
         out = new ObjectOutputStream(fileOut);
         out.writeObject(lectureList);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved\n");
      } catch (IOException i) {
         i.printStackTrace();
      }
   }

   public ArrayList<Lecture> DeserializeLectureList() {
      try {
        ArrayList<Lecture> lectureList2;
         FileInputStream fileIn = new FileInputStream(FILEPATH + "lecture.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         lectureList2 = (ArrayList<Lecture>) in.readObject();
         in.close();
         fileIn.close();
         return lectureList2;
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
         System.out.printf("Serialized data is saved\n");
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

   public void SerializeTutorialList(ArrayList<Tutorial> tutorialList) {
      FileOutputStream fileOut;
      ObjectOutputStream out;
      try {
         fileOut = new FileOutputStream(FILEPATH + "tutorial.ser");
         out = new ObjectOutputStream(fileOut);
         out.writeObject(tutorialList);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved\n");
      } catch (IOException i) {
         i.printStackTrace();
      }
   }

   public ArrayList<Tutorial> DeserializeTutorialList() {
      try {
        ArrayList<Tutorial> tutorialList2;
         FileInputStream fileIn = new FileInputStream(FILEPATH + "tutorial.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         tutorialList2 = (ArrayList<Tutorial>) in.readObject();
         in.close();
         fileIn.close();
         return tutorialList2;
      } catch (IOException i) {
         i.printStackTrace();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
      return null;
   }
}
