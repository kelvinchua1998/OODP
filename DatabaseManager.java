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

   for (int i = 0; i < courseList.size(); i++) {
       if (courseList.get(i).getCourseCode().equals(coursecode)) {
           Course courseObj = courseList.get(i);
           ArrayList<Cindex> CindexObj = courseObj.getListCindex();
           for (int j = 0; j < CindexObj.size(); j++) {
               if (CindexObj.get(j).getIndex().equals(Cindex)) {
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

   public ArrayList<Student> getStudentListbyCourse(String coursecode){
      Course singleCourse = searchCourse(coursecode);
      ArrayList<Student> temp = new ArrayList<Student>();

      for(int i=0; i<singleCourse.getListCindex().size(); i++)
      {
         if(singleCourse.getListCindex().get(i) != null)
            temp.addAll(singleCourse.getListCindex().get(i).getRegisteredStudents());
      }
      return temp;
   }
   

   //print courses in database
   public ArrayList<Course> getCourseList(){
      ArrayList<Course> courseList = new ArrayList<Course>();
      ArrayList<Course> temp = new ArrayList<Course>();

      DatabaseManager databaseManager = new DatabaseManager();
      courseList = databaseManager.DeserializeCourseList();

        // return courseList;
        for (int i = 0; i < courseList.size(); i++) {
             temp.add(courseList.get(i));
        }    
             
         return temp;
         
      
   }
   public void addStudentintoStudentDB(Student studentObj){
      ArrayList<Student> studentList = DeserializeStudentList();
      studentList.add(studentObj);
      SerializeStudentList(studentList);
   }

   public void EditStudentAccessPeriod(String matricNum, Calendar newAccessStartDateTime,
		Calendar newAccessEndDateTime) {

		ArrayList<Student> StudentList = DeserializeStudentList();
		Student StudentObj = getStudentbyMatricNum(matricNum);
		int index = getIndexbyMatricNum(matricNum);

		long newAccessStartDateTimeInms = newAccessStartDateTime.getTimeInMillis();
		long newAccessEndDateTimeInms = newAccessEndDateTime.getTimeInMillis();
		StudentObj.setAccessStartTime(newAccessStartDateTimeInms);
		StudentObj.setAccessStartTime(newAccessEndDateTimeInms);

		StudentList.set(index, StudentObj);

		SerializeStudentList(StudentList);
   }
   
   public Student getStudentbyMatricNum(String matricNum ) {
      ArrayList<Student> studentList = DeserializeStudentList();

		for (int i = 0; i < studentList.size(); i++) {
			if (studentList.get(i).getMatricNum() == matricNum) {
				return studentList.get(i);
			}
		}
		return null;
   }
   
   private int getIndexbyMatricNum(String matricNum) {
      ArrayList<Student> studentList = DeserializeStudentList();

		for (int i = 0; i < studentList.size(); i++) {
			if (studentList.get(i).getMatricNum() == matricNum) {
				return i;
			}
		}
		return -1;
   }
   
   public void removeCourseMain(String matricNum, String CourseCode) {
		DatabaseManager databaseManager = new DatabaseManager();

		ArrayList<Student> studentList = (ArrayList<Student>) databaseManager.DeserializeStudentList();

		int index = getIndexbyMatricNum(matricNum);

      Student studentObj = studentList.get(index);
      studentObj.removeCourse(CourseCode);
      
		databaseManager.SerializeStudentList(studentList);
   }
   
   public void printCourseRegistered(String username) {
		ArrayList<Student> studentList = (ArrayList<Student>) DeserializeStudentList();

		int index = getIndexByUsername(username);

		ArrayList<StudentCourse> registercourses =studentList.get(index).getRegisteredCourse();

      if(registercourses.size() != 0){
         System.out.println("registered Courses: ");
         for (int i = 0; i < registercourses.size(); i++) {
            System.out.printf("%d. %s %s", i, registercourses.get(i).getCourseCode(), registercourses.get(i).getCourseName());
         }
      }else{
         System.out.println("you do not hav any course registered!");
      }
		
	}
   
   public void addStudent(String firstName, String lastName, String gender, String nationality,String matricNum, String username, String pwd, Calendar AccessStartTime, Calendar AccessEndTime) {
		ArrayList<Student> StudentList = DeserializeStudentList();

		long AccessStartTimeInms = AccessStartTime.getTimeInMillis();
      long AccessEndTimeInms = AccessEndTime.getTimeInMillis();
      int numAU = 0;

		Student newStudent = new Student(firstName, lastName, gender, nationality, matricNum, username,pwd, numAU,  AccessStartTimeInms, AccessEndTimeInms);
      StudentList.add(newStudent);

      SerializeStudentList(StudentList);

   }
   
   public void adduser(User userObj){
      ArrayList<User> userList = DeserializeUserList();
      userList.add(userObj);
      SerializeUserList(userList);
   }

   public boolean checkClashforStudent(String username, String courseCode, String Cindex){

      Student stud = (Student) getObjectbyUsername(username);

      Cindex courseIndex = searchCindex(courseCode, Cindex);

      return stud.checkClash(courseIndex);
   }

   public int getIndexByCourseCode(String courseCode){
      ArrayList<Course> courseList = DeserializeCourseList();

		for (int i = 0; i < courseList.size(); i++) {
			if (courseList.get(i).getCourseCode().equals(courseCode)) {
				return i;
			}
		}
		return -1;
   }

   public int getIndexByUsername(String username){
      ArrayList<User> userList = DeserializeUserList();

		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getUsername().equals(username)) {
				return i;
			}
		}
		return -1;
   }

   public void updateDatabase(Object obj){
      if (obj instanceof Student){
         Student stud = (Student) obj;

         // search student in array list and replace
         ArrayList<Student> studentList  = DeserializeStudentList();
         studentList.set(getIndexbyMatricNum(stud.getMatricNum()),stud);
         SerializeStudentList(studentList);
      }
      else if (obj instanceof Course){
         Course c = (Course) obj;

         // search student in array list and replace
         ArrayList<Course> clist  = DeserializeCourseList();
         clist.set(getIndexByCourseCode(c.getCourseCode()),c);
         SerializeCourseList(clist);
         
      }
      else if (obj instanceof Admin){
         // no other attribute to change other than primary key AdminID
         
      }else if (obj instanceof User){
         User userobj = (User) obj;

         // search student in array list and replace
         ArrayList<User> adminList  = DeserializeUserList();
         adminList.set(getIndexByUsername(userobj.getUsername()),userobj);
         SerializeUserList(adminList);
      }
      else{
         //no object
      }
      
   }

   public Object getObjectbyUsername(String username){
      DatabaseManager databaseManager = new DatabaseManager();
      ArrayList<User> userList = databaseManager.DeserializeUserList();
      String usertype = "";
      
      for(int i=0; i<userList.size(); i++){
         if(userList.get(i).getUsername().equals(username)){
            usertype = userList.get(i).getUserType();
         }
      }

      switch(usertype){
         case "admin":{
            ArrayList<Admin> adminList = databaseManager.DeserializeAdminList();
            for(int j=0; j<adminList.size();j++){
               if(adminList.get(j).getUsername().equals(username)){
                  return adminList.get(j);
               }
            }
         }
         case "student":{
            ArrayList<Student> studentList = databaseManager.DeserializeStudentList();
            for(int j=0; j<studentList.size();j++){
               if(studentList.get(j).getUsername().equals(username)){
                  return studentList.get(j);
               }
            }
         }
         default:{
            System.out.println("Object does not exist with the username");
            return null;
         }
      }

   }

   public boolean verifyUniqueCourseCode(String courseCode) {
		ArrayList<Course> courseList = DeserializeCourseList();

		for (int i = 0; i < courseList.size(); i++) {
			if(courseList.get(i).getCourseCode() == courseCode) {
				return false;
			}
		}
		return true;
   }
   
   public boolean verifyUniqueMatricNum(String matricNum) {
		ArrayList<Student> StudentList = DeserializeStudentList();

		for (int i = 0; i < StudentList.size(); i++) {
			if (StudentList.get(i).getMatricNum() == matricNum) {
				return false;
			}
		}
		return true;
	}
}
