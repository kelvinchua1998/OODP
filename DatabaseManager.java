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

   public ArrayList<Student> getStudentListbyCourse(String coursecode){
      ArrayList<Student> studentList = new ArrayList<Student>();
      Course courseObj = searchCourse(coursecode);

      for(int i=0; i<courseObj.getListCindex().size(); i++){
         Cindex cindexObj = courseObj.getListCindex().get(i);
         for(int j=0; j<cindexObj.getRegisteredStudents().size(); j++){
            studentList.add(cindexObj.getRegisteredStudents().get(j));
         }
      }

      return studentList;
   }
   

   //print courses in database
   public ArrayList<Course> getCourseList(){
      ArrayList<Course> courseList = new ArrayList<Course>();

      DatabaseManager databaseManager = new DatabaseManager();
      courseList = databaseManager.DeserializeCourseList();

         return courseList;
      
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
   
   public void printCourseMain(String matricNum) {
		ArrayList<Student> studentList = (ArrayList<Student>) DeserializeStudentList();

		int index = getIndexbyMatricNum(matricNum);

		ArrayList<StudentCourse> registercourses =studentList.get(index).getRegisteredCourse();

		System.out.println("registered Courses: ");
		for (int i = 0; i < registercourses.size(); i++) {
			System.out.printf("%d. %s %s", i, registercourses.get(i).getCourseCode(),
					registercourses.get(i).getCourseName());
		}
	}
   
   public void addStudent(String firstName, String lastName, String gender, String nationality, String matricNum, String username, String pwd, Calendar AccessStartTime, Calendar AccessEndTime) {
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

   public boolean checkClashforStudent(String matricNum, String courseCode, String Cindex){

      Student stud = getStudentbyMatricNum(matricNum);

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
      ArrayList<User> adminList = DeserializeUserList();

		for (int i = 0; i < adminList.size(); i++) {
			if (adminList.get(i).getUsername().equals(username)) {
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
}
