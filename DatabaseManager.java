import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
/**
 * Database Manager Class for any database related methods
 */
public class DatabaseManager {
/**
 * Constant relative filepath tot he database
 */
   String FILEPATH = "database\\";
/**
 * method to serialize the course list
 * @param courseList ArrayList<Course>
 */
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
/**
 * method to desirialize the course list
 * @return ArrayList of class Course
 */
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
/**
 * serialize the user list. The user database contains the login infomation for all the users.
 * @param UserList ArrayList<User>
 */
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
/**
 * this method is to deserialize the userlist
 * @return ArrayList of class User
 */
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
/**
 * serialise an array List of admins into the database
 * @param adminList ArrayList
 */
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
/**
 * desrialise admin list from the database
 * @return ArrayList<Admin>
 */
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
/**
 * serialize a list of student into the database
 * @param studentList
 */
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
/**
 * desrialize the student list from the database
 * @return ArrayList<Student>
 */
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
/**
 * get vacaney for the specified course and index
 * @param coursecode string
 * @param index string
 * @return int
 */
   public int getVacancyCindex(String coursecode, String index) {
      Cindex singleIndex = searchCindex(coursecode, index);

      if (singleIndex != null) {
         return singleIndex.getCurrentVacancy();
      } else {
         return -1;
      }
   }
/**
 * returns the Cindex object from the specified CourseCode and Cindex
 * @param coursecode string
 * @param Cindex string
 * @return Cindex
 */
   public Cindex searchCindex(String coursecode, String Cindex) {
      ArrayList<Course> courseList;

      DatabaseManager databaseManager = new DatabaseManager();
      courseList = databaseManager.DeserializeCourseList();

      if (courseList != null) {
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
/**
 * return the student list from the database
 * @param coursecode string
 * @param index string
 * @return ArrayList<String>
 */
   public ArrayList<String> getStudentList(String coursecode, String index) {
      Cindex singleIndex = searchCindex(coursecode, index);

      if (singleIndex == null) { // error checking for invalid course index
         ArrayList<String> temp = new ArrayList<String>();
         System.out.println("Course/index not found!");
         return temp;
      }
      return singleIndex.getRegisteredStudents();
   }
/**
 * returns the specified course from the database
 * @param coursecode string
 * @return Course
 */
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
/**
 * get all the student registered for the course
 * @param coursecode string
 * @return ArrayList <String>
 */
   public ArrayList<String> getStudentListbyCourse(String coursecode) {
      Course singleCourse = searchCourse(coursecode);

      ArrayList<String> temp = new ArrayList<String>();

      if (singleCourse == null) {
         System.out.println("Course does not exists in Database!");
         return null;
      }

      for (int i = 0; i < singleCourse.getListCindex().size(); i++) {
         if (singleCourse.getListCindex().get(i) != null)
            temp.addAll(singleCourse.getListCindex().get(i).getRegisteredStudents());
      }
      return temp;

   }
/**
 * add a single Student Object into the database
 * @param studentObj Student
 */
   public void addStudentintoStudentDB(Student studentObj) {
      ArrayList<Student> studentList = DeserializeStudentList();
      studentList.add(studentObj);
      SerializeStudentList(studentList);
   }
/**
 * update the student's start access date time and his end access date time
 * @param matricNum string
 * @param newAccessStartDateTime Calender
 * @param newAccessEndDateTime Calender
 */
   public void EditStudentAccessPeriod(String matricNum, Calendar newAccessStartDateTime,
         Calendar newAccessEndDateTime) {

      ArrayList<Student> StudentList = DeserializeStudentList();
      int index = getIndexbyMatricNum(matricNum);

      Student StudentObj = getStudentbyMatricNum(matricNum);
      long newAccessStartDateTimeInms = newAccessStartDateTime.getTimeInMillis();
      long newAccessEndDateTimeInms = newAccessEndDateTime.getTimeInMillis();
      if (newAccessEndDateTimeInms > newAccessStartDateTimeInms) {
         StudentObj.setAccessStartTime(newAccessStartDateTimeInms);
         StudentObj.setAccessEndTime(newAccessEndDateTimeInms);

         StudentList.set(index, StudentObj);

         SerializeStudentList(StudentList);
         System.out.printf("Access time for %s changed!\n", matricNum);
      } else {
         System.out.println("End Time must be later than Start Time!");
      }
   }
/**
 * returns Student Object by Matric number 
 * @param matricNum string 
 * @return Student 
 */
   public Student getStudentbyMatricNum(String matricNum) {
      ArrayList<Student> studentList = DeserializeStudentList();

      for (int i = 0; i < studentList.size(); i++) {
         if (studentList.get(i).getMatricNum().equals(matricNum)) {
            return studentList.get(i);
         }
      }
      return null;
   }
/**
 * get the index of the student in the student database
 * @param matricNum string
 * @return int
 */
   private int getIndexbyMatricNum(String matricNum) {
      ArrayList<Student> studentList = DeserializeStudentList();

      for (int i = 0; i < studentList.size(); i++) {
         if (studentList.get(i).getMatricNum().equals(matricNum)) {
            return i;
         }
      }
      return -1;
   }
/**
 * the method for the drop course function
 * @param username
 * @param courseCode
 */
   public void removeCourseMain(String username, String courseCode) {
      Student studentObj = (Student) getObjectbyUsername(username);

      Course courseObj = searchCourse(courseCode);

      //checks if the sutdent is in the course registered and waitlist
      if(courseObj.checkIfStudentFromRegAndWaitlist(username)){
         // true means that the student is registered in the course or is in the waitlist
         studentObj.removeCourseFromRegORWait(courseCode);

         studentObj.plusAU(courseObj);

         courseObj.removeStudentFromIndex(studentObj.getUsername());

         updateDatabase(courseObj);
         updateDatabase(studentObj);
      } else {
         /// false means that the student is not reg
         System.out.println("you are not registered or in the waitlist for the course!");
      }

   }
/**
 * print all the course registered by the student
 * @param username
 */
   public void printCourseRegistered(String username) {
      Student studentObj = (Student) getObjectbyUsername(username);

      ArrayList<StudentCourse> registercourses = studentObj.getRegisteredCourse();

      if (registercourses.size() != 0) {
         System.out.println("registered Courses: ");
         for (int i = 0; i < registercourses.size(); i++) {
            System.out.println("---------------------------------------");
            System.out.printf("%d. %s %s index %s\n", i + 1, registercourses.get(i).getCourseCode(),
                  registercourses.get(i).getCourseName(), registercourses.get(i).getCourseIndex());

            Cindex cindex = searchCindex(registercourses.get(i).getCourseCode(), registercourses.get(i).getCourseIndex());
            ArrayList<Lesson> Schedule = cindex.getSchedule();
            System.out.println("Lesson Type\tStart Time\tEnd Time:\tVenue:\tDay of Week:");
            for(int j=0; j<Schedule.size();j++){
               Main.LESSON_TYPE lessonType = Schedule.get(j).getLessonType();
               switch(lessonType){
                  case LECTURE:{
                     Lecture lectureObj = (Lecture) Schedule.get(j);

                     DateFormat dateFormat = new SimpleDateFormat("HH:mm");  

                     Date startTime = lectureObj.getStartTime();  
                     String startTimeStr = dateFormat.format(startTime);
                     
                     Date endTime = lectureObj.getEndTime();
                     String endTimeStr = dateFormat.format(endTime);

                     System.out.printf("Lecture\t\t%s\t\t%s\t\t%s\t%s\n",startTimeStr,endTimeStr,lectureObj.getVenue(),lectureObj.getDayoftheWeek());
                     break;
                  }
                  case TUTORIAL:{
                     Tutorial tutorialObj = (Tutorial) Schedule.get(j);

                     DateFormat dateFormat = new SimpleDateFormat("HH:mm");  

                     Date startTime = tutorialObj.getStartTime();  
                     String startTimeStr = dateFormat.format(startTime);
                     
                     Date endTime = tutorialObj.getEndTime();
                     String endTimeStr = dateFormat.format(endTime);

                     System.out.printf("Tutorial\t%s\t\t%s\t\t%s\t%s\n",startTimeStr,endTimeStr,tutorialObj.getVenue(),tutorialObj.getDayoftheWeek());
                     break;
                  }
                  case LAB:{
                     Labs labsObj = (Labs) Schedule.get(j);

                     DateFormat dateFormat = new SimpleDateFormat("HH:mm");  

                     Date startTime = labsObj.getStartTime();  
                     String startTimeStr = dateFormat.format(startTime);
                     
                     Date endTime = labsObj.getEndTime();
                     String endTimeStr = dateFormat.format(endTime);

                     System.out.printf("Lab\t\t%s\t\t%s\t\t%s\t%s %s\n",startTimeStr,endTimeStr,labsObj.getVenue(),labsObj.getDayoftheWeek(),labsObj.getOddorEven());
                     break;
                  }
               }
            }
            System.out.println("---------------------------------------");
         }
      } else {
         System.out.println("You do not have any course registered!");
      }

      ArrayList<StudentCourse> waitlistCourses = studentObj.getWaitlist();

      if (waitlistCourses.size() != 0) {
         System.out.println("waitlist Courses: ");
         for (int i = 0; i < waitlistCourses.size(); i++) {
            System.out.println("---------------------------------------");
            System.out.printf("%d. %s %s index %s\n", i + 1, waitlistCourses.get(i).getCourseCode(), waitlistCourses.get(i).getCourseName(), waitlistCourses.get(i).getCourseIndex());

            Cindex cindex = searchCindex(waitlistCourses.get(i).getCourseCode(), waitlistCourses.get(i).getCourseIndex());
            ArrayList<Lesson> Schedule = cindex.getSchedule();
            System.out.println("Lesson Type\tStart Time\tEnd Time:\tVenue:\tDay of Week:");
            for(int j=0; j<Schedule.size();j++){
               Main.LESSON_TYPE lessonType = Schedule.get(j).getLessonType();
               switch(lessonType){
                  case LECTURE:{
                     Lecture lectureObj = (Lecture) Schedule.get(j);

                     DateFormat dateFormat = new SimpleDateFormat("HH:mm");  

                     Date startTime = lectureObj.getStartTime();  
                     String startTimeStr = dateFormat.format(startTime);
                     
                     Date endTime = lectureObj.getEndTime();
                     String endTimeStr = dateFormat.format(endTime);

                     System.out.printf("Lecture\t\t%s\t\t%s\t\t%s\t%s\n",startTimeStr,endTimeStr,lectureObj.getVenue(),lectureObj.getDayoftheWeek());
                     break;
                  }
                  case TUTORIAL:{
                     Tutorial tutorialObj = (Tutorial) Schedule.get(j);

                     DateFormat dateFormat = new SimpleDateFormat("HH:mm");  

                     Date startTime = tutorialObj.getStartTime();  
                     String startTimeStr = dateFormat.format(startTime);
                     
                     Date endTime = tutorialObj.getEndTime();
                     String endTimeStr = dateFormat.format(endTime);

                     System.out.printf("Tutorial\t%s\t\t%s\t\t%s\t%s\n",startTimeStr,endTimeStr,tutorialObj.getVenue(),tutorialObj.getDayoftheWeek());
                     break;
                  }
                  case LAB:{
                     Labs labsObj = (Labs) Schedule.get(j);

                     DateFormat dateFormat = new SimpleDateFormat("HH:mm");  

                     Date startTime = labsObj.getStartTime();  
                     String startTimeStr = dateFormat.format(startTime);
                     
                     Date endTime = labsObj.getEndTime();
                     String endTimeStr = dateFormat.format(endTime);

                     System.out.printf("Lab\t\t%s\t\t%s\t\t%s\t%s %s\n",startTimeStr,endTimeStr,labsObj.getVenue(),labsObj.getDayoftheWeek(),labsObj.getOddorEven());
                     break;
                  }
               }
            }
            System.out.println("---------------------------------------");
         }
      } else {
         System.out.println("You do not have any courses in waitlist!");
      }

   }
/**
 * add a single student into the database
 * @param firstName string
 * @param lastName string
 * @param gender Main.GENDER
 * @param nationality String
 * @param matricNum String
 * @param username String
 * @param password String
 * @param AccessStartTime Long
 * @param AccessEndTimE Long
 * @param email String
 */
   public void addStudent(String firstName, String lastName, Main.GENDER gender, String nationality, String matricNum,
         String username, String password, Calendar AccessStartTime, Calendar AccessEndTime, String email) {
      ArrayList<Student> StudentList = DeserializeStudentList();

      long AccessStartTimeInms = AccessStartTime.getTimeInMillis();
      long AccessEndTimeInms = AccessEndTime.getTimeInMillis();

      Student newStudent = new Student(firstName, lastName, gender, nationality, matricNum, username, password,
            AccessStartTimeInms, AccessEndTimeInms, email);

      StudentList.add(newStudent);

      SerializeStudentList(StudentList);
   }
/**
 * add a single User object into the user database
 * @param userObj User
 */
   public void adduser(User userObj) {
      ArrayList<User> userList = DeserializeUserList();
      userList.add(userObj);
      SerializeUserList(userList);
   }
/**
 * Check clash method for checking clashes for whether a specific Cindex clashes with the student's registered course. returns true if there is any clashes, otherwise false.
 * @param username String
 * @param courseCode String
 * @param Cindex String
 * @return boolean
 */
   public boolean checkClashforStudent(String username, String courseCode, String Cindex) {

      Student stud = (Student) getObjectbyUsername(username);

      Cindex courseIndex = searchCindex(courseCode, Cindex);

      return stud.checkClash(courseIndex);
   }
/**
 * returns the index for the course in the database
 * @param courseCode
 * @return int
 */
   public int getIndexByCourseCode(String courseCode) {
      ArrayList<Course> courseList = DeserializeCourseList();

      for (int i = 0; i < courseList.size(); i++) {
         if (courseList.get(i).getCourseCode().equals(courseCode)) {
            return i;
         }
      }
      return -1;
   }
/**
 * get the index of the user in the database
 * @param username
 * @return
 */
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
/**
 * a general method that updates the database according to the type of object passed in. only works for overwriting an existing object in the database, does not add or remove the obejcts
 * @param obj
 */
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
/**
 * returns the Student or Admin Object depending on the username
 * @param username String
 * @return Object
 */
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
/**
 * method to check whether the course code is unique. returns true if it is unique, otherwise false
 * @param courseCode string
 * @return boolean
 */
   public boolean verifyUniqueCourseCode(String courseCode) {
      ArrayList<Course> courseList = DeserializeCourseList();

      for (int i = 0; i < courseList.size(); i++) {
         if (courseList.get(i).getCourseCode().equals(courseCode)) {
            return false;
         }
      }
      return true;
   }
/**
 * method to check whether the matric number is unique. returns true if it is unique, otherwise false
 * @param matricNum string
 * @return boolean
 */
   public boolean verifyUniqueMatricNum(String matricNum) {
      ArrayList<Student> StudentList = DeserializeStudentList();

      for (int i = 0; i < StudentList.size(); i++) {
         if (StudentList.get(i).getMatricNum().equals(matricNum)) {
            return false;
         }
      }
      return true;
   }
/**
 * method to check whether the username is unique. returns true if it is unique, otherwise false
 * @param username string
 * @return boolean
 */
   public boolean verifyUniqueUsername(String username) {
      ArrayList<Student> StudentList = DeserializeStudentList();

      for (int i = 0; i < StudentList.size(); i++) {
         if (StudentList.get(i).getMatricNum().equals(username)) {
            return false;
         }
      }
      return true;
   }
/**
 * check whether the student have already registered for the course. returns true if the student is already registered, otherwise false.
 * @param stud Student
 * @param singleCourse Course
 * @return Boolean
 */
   public boolean checkStudentRegisteredCourses(Student stud, Course singleCourse) {
      // returns true if the course is already registered
      for (int i = 0; i < stud.getRegisteredCourse().size(); i++) {
         if (stud.getRegisteredCourse().get(i).getCourseCode().equals(singleCourse.getCourseCode())) {
            return true;
         }
      }
      return false;
   }
/**
 * Cheak whether the course is the student's registered or waitlist Courses. returns true if the course is found in the student's registered or waitlist courses, otherwise false.
 * @param username String
 * @param courseCode String
 * @return boolean
 */
   public boolean checkStudentRegAndWaitList(String username, String courseCode) {
      Student studentObj = (Student) getObjectbyUsername(username);
      ArrayList regC = studentObj.getRegisteredCourse();
      for (int i = 0; i < regC.size(); i++) {
         if (studentObj.getRegisteredCourse().get(i).getCourseCode().equals(courseCode)) {
            return true;
         }
      }
      ArrayList waitC = studentObj.getWaitlist();
      for (int i = 0; i < waitC.size(); i++) {
         if (studentObj.getWaitlist().get(i).getCourseCode().equals(courseCode)) {
            return true;
         }
      }
      return false;
   }
/**
 * Main method to update the the specifed Cindex and course
 * @param courseCode String
 * @param cindexObj String
 */
   public void updatecindex(String courseCode, Cindex cindexObj) {
      Course courseObj = searchCourse(courseCode);
      ArrayList<Cindex> cindexList = courseObj.getListCindex();
      for (int i = 0; i < cindexList.size(); i++) {
         if (cindexList.get(i).getIndexName().equals(cindexObj.getIndexName())) {
            cindexList.set(i, cindexObj);
         }
      }
      courseObj.setListCindex(cindexList);
      updateDatabase(courseObj);
   }
/**
 * main method to add a new Cindex object into an existing Course
 */
   public void addcindex(String courseCode, Cindex cindexObj) {
      Course courseObj = searchCourse(courseCode);
      ArrayList<Cindex> cindexList = courseObj.getListCindex();
      cindexList.add(cindexObj);

      courseObj.setListCindex(cindexList);
      updateDatabase(courseObj);
   }
/**
 * print all students in the database
 */
public void printAllStudents() {
   ArrayList<Student> studList = DeserializeStudentList();

   System.out.printf("%-5s%-15s%-15s%-15s%-8s%-15s%-30s%-5s%-20s%-20s\n", "No", "MatricNum", "First name", "Last Name", "Gender", "Nationality", "Email", "AUs", "AccessStartDateTime", "AccessEndDateTime");

   if(studList.size() != 0){
      for(int i =0; i < studList.size();i++){
         Student singleStud = studList.get(i);
         Calendar calendar = Calendar.getInstance();
         calendar.setTimeInMillis(singleStud.getAccessStartTime());
         Calendar calendar2 = Calendar.getInstance();
         calendar2.setTimeInMillis(singleStud.getAccessEndTime());

         String starttimeStr = calendar.get(Calendar.DAY_OF_MONTH) +"/"+ calendar.get(Calendar.MONTH)+1 +"/"+ calendar.get(Calendar.YEAR) + "  " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
         String endtimeStr = calendar2.get(Calendar.DAY_OF_MONTH) +"/"+ calendar2.get(Calendar.MONTH)+1 +"/"+ calendar2.get(Calendar.YEAR) + "  " + calendar2.get(Calendar.HOUR_OF_DAY) + ":" + calendar2.get(Calendar.MINUTE);

         System.out.printf("%-5d%-15s%-15s%-15s%-8s%-15s%-30s%-5s%-20s%-20s\n",i+1,singleStud.getMatricNum(), singleStud.getFirstName(),singleStud.getLastName(), singleStud.getGender(), singleStud.getNationality(), singleStud.getEmail(), singleStud.getNumAuRegistered(), starttimeStr, endtimeStr);

      }
   }else{
      System.out.println("no student in database!");
   }
}
/**
 * print all courses in the database
 */
public void printAllCourses() {
   ArrayList<Course> courseList = DeserializeCourseList();

   if(courseList.size() != 0){
      for(int i =0; i < courseList.size();i++){
         System.out.printf("%-5s%-15s%-15s%-10s%-12s%-30s\n","No.", "Course Code:", "Course Name:", "School:", "Num of AUs:", "Course Description:");

         Course singleCourse = courseList.get(i);
         System.out.printf("%-5d%-15s\n%-15s%-10s%-12d\n%-30s\n",i+1,singleCourse.getCourseCode(), singleCourse.getCourseName(),singleCourse.getSchool(), singleCourse.getAU(), singleCourse.getCourseDescription());
        
         //there shouldnt be any courses with no index
         System.out.println("-----------------------------------------------------------------");
         System.out.printf("\t%-5s%-6s%-10s%-8s%-9s\n","No." ,"Index" ,"Capacity" ,"Vacancy" ,"Waitlist");
         for(int j =0; j < singleCourse.getListCindex().size();j++){
            Cindex singleCindex = singleCourse.getListCindex().get(j);

            

            System.out.printf("\t%-5d%-6s%-10d%-8d%-9d\n",j+1,singleCindex.getIndexName(), singleCindex.getCapacity(),singleCindex.getCurrentVacancy(), singleCindex.getWaitList().size());
         }
         System.out.println("-----------------------------------------------------------------");
      }
   }else{
      System.out.println("no course in database!");
   }
}
/**
 * update all the student registered for the course with the new courseCode
 */
   public void updateStudentCourseNewCourseCode(String oldCourseCode,String newCourseCode){
      Course coursObj = searchCourse(oldCourseCode);
      ArrayList<Cindex> cindexList = coursObj.getListCindex();

      for(int i=0;i<cindexList.size();i++){
         ArrayList<String> registeredStudentstr = cindexList.get(i).getRegisteredStudents();
         for(int j=0;j<registeredStudentstr.size();j++){
            Student studentObj = (Student) getObjectbyUsername(registeredStudentstr.get(j));

            ArrayList<StudentCourse> StudentRegisteredCourse = studentObj.getRegisteredCourse();
            for(int k=0; k<StudentRegisteredCourse.size();k++){
               if(StudentRegisteredCourse.get(k).getCourseCode().equals(oldCourseCode)){
                  StudentRegisteredCourse.get(k).setCourseCode(newCourseCode);
                  break;
               }
            }
            studentObj.setRegisteredCourse(StudentRegisteredCourse);
            updateDatabase(studentObj);
         }
      }
   }
/**
 * update the registered in the Cindex with the new index name
 * @param courseCode String
 * @param oldIndexName String
 * @param newIndexName String
 */
   public void updateStudentCourseNewIndexName(String courseCode,String oldIndexName,String newIndexName){
      Cindex cindexObj = searchCindex(courseCode,oldIndexName);

      ArrayList<String> registeredStudentstr = cindexObj.getRegisteredStudents();
      for(int j=0;j<registeredStudentstr.size();j++){
         Student studentObj = (Student) getObjectbyUsername(registeredStudentstr.get(j));

         ArrayList<StudentCourse> StudentRegisteredCourse = studentObj.getRegisteredCourse();
         for(int k=0; k<StudentRegisteredCourse.size();k++){
            if(StudentRegisteredCourse.get(k).getCourseCode().equals(courseCode)){
               StudentRegisteredCourse.get(k).setCourseIndex(newIndexName);
               break;
            }
         }
         studentObj.setRegisteredCourse(StudentRegisteredCourse);
         updateDatabase(studentObj);
      }
   }
 
   public static void main(String[] args) {
      DatabaseManager databaseManager = new DatabaseManager();
      databaseManager.printAllStudents();
      // databaseManager.printAllCourses();
   }
}