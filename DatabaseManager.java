import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

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

   public void SerializeUserList(ArrayList<User> UserList) {
      FileOutputStream fileOut;
      ObjectOutputStream out;
      try {
         fileOut = new FileOutputStream(FILEPATH + "user.ser");
         out = new ObjectOutputStream(fileOut);
         out.writeObject(UserList);
         out.close();
         fileOut.close();
         System.out.println("Serialized data is saved");
      } catch (IOException i) {
         i.printStackTrace();
      }

   }

   public ArrayList<User> DeserializeUserList() {
      try {
         ArrayList<User> userList;
         FileInputStream fileIn = new FileInputStream(FILEPATH + "user.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         userList = (ArrayList<User>) in.readObject();
         in.close();
         fileIn.close();
         return userList;
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
      ArrayList<Course> courseList;

      DatabaseManager databaseManager = new DatabaseManager();
      courseList = databaseManager.DeserializeCourseList();

      if(courseList != null){
      for (int i = 0; i < courseList.size(); i++) {
         if (courseList.get(i).getCourseCode().equals(coursecode)) {
            Course courseObj = courseList.get(i);
            ArrayList<Cindex> CindexObj = courseObj.getListCindex();
            for (int j = 0; j < CindexObj.size(); j++) {
               if (CindexObj.get(j).getIndexName().equals(Cindex)) {
                  return CindexObj.get(j);
               }
            }

         }
      }
      }  
      
      return null;

   }

   public ArrayList<String> getStudentList(String coursecode, String index) {
      Cindex singleIndex = searchCindex(coursecode, index);

      if (singleIndex == null) { // error checking for invalid course index
         ArrayList<String> temp = new ArrayList<String>();
         System.out.println("Course/index not found!");
         return temp;
      }
      return singleIndex.getRegisteredStudents();
   }

   // for print student list by course
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

   public ArrayList<String> getStudentListbyCourse(String coursecode) {
      Course singleCourse = searchCourse(coursecode);

      ArrayList<String> temp = new ArrayList<String>();

      if (singleCourse == null) {
         System.out.println("Course not found!");
         return temp;
      }
      
      for (int i = 0; i < singleCourse.getListCindex().size(); i++) {
         if (singleCourse.getListCindex().get(i) != null)
            temp.addAll(singleCourse.getListCindex().get(i).getRegisteredStudents());
      }
      return temp;
      
   }


   public void addStudentintoStudentDB(Student studentObj) {
      ArrayList<Student> studentList = DeserializeStudentList();
      studentList.add(studentObj);
      SerializeStudentList(studentList);
   }

   public void EditStudentAccessPeriod(String matricNum, Calendar newAccessStartDateTime,
         Calendar newAccessEndDateTime) {

      ArrayList<Student> StudentList = DeserializeStudentList();
      int index = getIndexbyMatricNum(matricNum);

      Student StudentObj = getStudentbyMatricNum(matricNum);
      long newAccessStartDateTimeInms = newAccessStartDateTime.getTimeInMillis();
      long newAccessEndDateTimeInms = newAccessEndDateTime.getTimeInMillis();
      if(newAccessEndDateTimeInms > newAccessStartDateTimeInms){
         StudentObj.setAccessStartTime(newAccessStartDateTimeInms);
         StudentObj.setAccessEndTime(newAccessEndDateTimeInms);

         StudentList.set(index, StudentObj);

         SerializeStudentList(StudentList);
         System.out.printf("Access time for %s changed!\n", matricNum);
      }
      else{
         System.out.println("End Time must be later than Start Time!");
      }
   }

   public Student getStudentbyMatricNum(String matricNum) {
      ArrayList<Student> studentList = DeserializeStudentList();

      for (int i = 0; i < studentList.size(); i++) {
         if (studentList.get(i).getMatricNum().equals(matricNum)) {
            return studentList.get(i);
         }
      }
      return null;
   }

   private int getIndexbyMatricNum(String matricNum) {
      ArrayList<Student> studentList = DeserializeStudentList();

      for (int i = 0; i < studentList.size(); i++) {
         if (studentList.get(i).getMatricNum().equals(matricNum)) {
            return i;
         }
      }
      return -1;
   }
   
   public void removeCourseMain(String username, String courseCode) {
		Student studentObj = (Student) getObjectbyUsername(username);
      
      Course courseObj = searchCourse(courseCode);

      //checks if the sutdent is in the reg course
      if(courseObj.removeStudentFromReg(username)){
         // true means that the student is registered in the course
         // student is removed from the course as well
         studentObj.removeCourse(courseCode);
         studentObj.plusAU(courseObj);
         
         // send email to stud that he has droppe dthe course

         SendMail sendMail = new SendMail();
            String EmailContent = "Dear Sir/Mdm,\n This is a confirmation email that your course "+ courseObj.getCourseCode()+" "+courseObj.getCourseName()+" index "+courseObj.getListCindex().get(0).getIndexName()+"been successfully dropped\n Thank You\n NTU STARS";
            sendMail.sendgmail("melvinchuaqwerty@gmail.com", "melvinchuaqwerty@gmail.com", "s9825202i",
                    studentObj.getEmail(), "Course dropped", EmailContent);

         //check for the whole course has any vacancies

         courseObj.pushWaitlist();
         
         updateDatabase(courseObj);
         updateDatabase(studentObj);
      }else{
         ///false means that the student is not reg
         System.out.println("you are not registered in the course!");
      }

      
   }

   public void printCourseRegistered(String username) {
      Student studentObj = (Student) getObjectbyUsername(username);

      ArrayList<StudentCourse> registercourses = studentObj.getRegisteredCourse();
      
      if (registercourses.size() != 0) {
         System.out.println("registered Courses: ");
         for (int i = 0; i < registercourses.size(); i++) {
            System.out.printf("%d. %s %s index %s\n", i + 1, registercourses.get(i).getCourseCode(), registercourses.get(i).getCourseName(), registercourses.get(i).getCourseIndex());
         }
      } else {
         System.out.println("You do not have any course registered!");
      }

      ArrayList<StudentCourse> waitlistCourses = studentObj.getWaitlist();
      
      if (waitlistCourses.size() != 0) {
         System.out.println("waitlist Courses: ");
         for (int i = 0; i < waitlistCourses.size(); i++) {
            System.out.printf("%d. %s %s index %s\n", i + 1, waitlistCourses.get(i).getCourseCode(),
            waitlistCourses.get(i).getCourseName(), waitlistCourses.get(i).getCourseIndex());
         }
      } else {
         System.out.println("You do not have any courses in waitlist!");
      }

   }

   public void addStudent(String firstName, String lastName, String gender, String nationality, String matricNum,
         String username, String password, Calendar AccessStartTime, Calendar AccessEndTime, String email) {
      ArrayList<Student> StudentList = DeserializeStudentList();

      long AccessStartTimeInms = AccessStartTime.getTimeInMillis();
      long AccessEndTimeInms = AccessEndTime.getTimeInMillis();

      Student newStudent = new Student(firstName, lastName, gender, nationality, matricNum, username, password,
            AccessStartTimeInms, AccessEndTimeInms, email);

      StudentList.add(newStudent);

      SerializeStudentList(StudentList);
   }

   public void adduser(User userObj) {
      ArrayList<User> userList = DeserializeUserList();
      userList.add(userObj);
      SerializeUserList(userList);
   }

   public boolean checkClashforStudent(String username, String courseCode, String Cindex) {

      Student stud = (Student) getObjectbyUsername(username);

      Cindex courseIndex = searchCindex(courseCode, Cindex);

      return stud.checkClash(courseIndex);
   }

   public int getIndexByCourseCode(String courseCode) {
      ArrayList<Course> courseList = DeserializeCourseList();

      for (int i = 0; i < courseList.size(); i++) {
         if (courseList.get(i).getCourseCode().equals(courseCode)) {
            return i;
         }
      }
      return -1;
   }

   public int getIndexByUsername(String username) {
      ArrayList<User> userList = DeserializeUserList();

      for (int i = 0; i < userList.size(); i++) {
         if (userList.get(i).getUsername().equals(username)) {
            if (userList.get(i).userType.equals("student")) {
               ArrayList<Student> studentList = DeserializeStudentList();
               for (int j = 0; j < studentList.size(); j++) {
                  if (studentList.get(j).getUsername().equals(username)) {
                     return j;
                  }
               }
            } else {
               ArrayList<Admin> adminList = DeserializeAdminList();
               for (int j = 0; j < adminList.size(); j++) {
                  if (adminList.get(j).getUsername().equals(username)) {
                     return j;
                  }
               }
            }
         }
      }
      return -1;
   }

   public void updateDatabase(Object obj) {
      if (obj instanceof Student) {
         Student stud = (Student) obj;

         // search student in array list and replace
         ArrayList<Student> studentList = DeserializeStudentList();
         studentList.set(getIndexbyMatricNum(stud.getMatricNum()), stud);
         SerializeStudentList(studentList);
      } else if (obj instanceof Course) {
         Course c = (Course) obj;

         // search student in array list and replace
         ArrayList<Course> clist = DeserializeCourseList();
         clist.set(getIndexByCourseCode(c.getCourseCode()), c);
         SerializeCourseList(clist);

      } else if (obj instanceof Admin) {
         Admin adminobj = (Admin) obj;

         // search student in array list and replace
         ArrayList<Admin> adminlist = DeserializeAdminList();
         adminlist.set(getIndexByUsername(adminobj.getUsername()), adminobj);
         SerializeAdminList(adminlist);

      } else if (obj instanceof User) {
         User userobj = (User) obj;

         // search student in array list and replace
         ArrayList<User> adminList = DeserializeUserList();
         adminList.set(getIndexByUsername(userobj.getUsername()), userobj);
         SerializeUserList(adminList);
      } else {
         // no object
      }

   }

   public Object getObjectbyUsername(String username) {
      DatabaseManager databaseManager = new DatabaseManager();
      ArrayList<User> userList = databaseManager.DeserializeUserList();
      String usertype = "";

      for (int i = 0; i < userList.size(); i++) {
         if (userList.get(i).getUsername().equals(username)) {
            usertype = userList.get(i).getUserType();
         }
      }

      switch (usertype) {
         case "admin": {
            ArrayList<Admin> adminList = databaseManager.DeserializeAdminList();
            for (int j = 0; j < adminList.size(); j++) {
               if (adminList.get(j).getUsername().equals(username)) {
                  return adminList.get(j);

               }
            }
         }
         case "student": {
            ArrayList<Student> studentList = databaseManager.DeserializeStudentList();
            for (int j = 0; j < studentList.size(); j++) {
               if (studentList.get(j).getUsername().equals(username)) {
                  return studentList.get(j);
               }
            }
         }
         default: {
            System.out.println("Object does not exist with the username");
            return null;
         }
      }

   }

   public boolean verifyUniqueCourseCode(String courseCode) {
      ArrayList<Course> courseList = DeserializeCourseList();

      for (int i = 0; i < courseList.size(); i++) {
         if (courseList.get(i).getCourseCode().equals(courseCode)) {
            return false;
         }
      }
      return true;
   }

   public boolean verifyUniqueMatricNum(String matricNum) {
      ArrayList<Student> StudentList = DeserializeStudentList();

      for (int i = 0; i < StudentList.size(); i++) {
         if (StudentList.get(i).getMatricNum().equals(matricNum)) {
            return false;
         }
      }
      return true;
   }

   public boolean verifyUniqueUsername(String username) {
      ArrayList<Student> StudentList = DeserializeStudentList();

      for (int i = 0; i < StudentList.size(); i++) {
         if (StudentList.get(i).getMatricNum().equals(username)) {
            return false;
         }
      }
      return true;
   }

   public boolean checkStudentRegisteredCourses(Student stud, Course singleCourse) {
      // returns true if the course is already registered
      for (int i = 0; i < stud.getRegisteredCourse().size(); i++) {
         if (stud.getRegisteredCourse().get(i).getCourseCode().equals(singleCourse.getCourseCode())) {
            return true;
         }
      }
      return false;
   }

public boolean checkStudentReg(String username,String courseCode) {
   Student studentObj = (Student) getObjectbyUsername(username);
   ArrayList regC = studentObj.getRegisteredCourse();
   for (int i = 0 ; i < regC.size();i++){
      if (studentObj.getRegisteredCourse().get(i).getCourseCode().equals(courseCode)){
         return true;
      }
   }
	return false;
}
public void updatecindex(String courseCode,Cindex cindexObj){
   Course courseObj = searchCourse(courseCode);
   ArrayList<Cindex> cindexList = courseObj.getListCindex();
   for(int i=0; i<cindexList.size();i++){
      if(cindexList.get(i).getIndexName().equals(cindexObj.getIndexName())){
         cindexList.set(i, cindexObj);
      }
   }
   courseObj.setListCindex(cindexList);
   updateDatabase(courseObj);
}
}
