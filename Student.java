import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Student implements Serializable {
private static final long serialVersionUID = 1L;

	private String matricNum;
	private String gender;
	private String firstName;
	private String lastName;
	private String nationality;
	private String username;
	private String password;
	private static int numAU;
	// Courses listC = new Courses[]; //array size set after students setCourses?
	// Courses waitlist = new Courses[];

	// public Student(String firstName, String lastName, String gender, String
	// nationality, String matricNum, int numAU, String pwd)
	private long AccessStartDateTime;
	private long AccessEndDateTime;
	private ArrayList<StudentCourse> registeredCourse;
	private ArrayList<StudentCourse> waitlist;

	public Student(String firstName, String lastName, String gender, String nationality, String matricNum,
			String username, int numAU, String pwd, long accessStartDateTime, long accessEndDateTime) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.nationality = nationality;
		this.matricNum = matricNum;
		Student.numAU = numAU;
		password = pwd;
		// all these info get from separate file?
		this.username = username;
		this.password = pwd;
		this.AccessStartDateTime = accessEndDateTime;
		this.AccessEndDateTime = accessEndDateTime;
		// the course in this array list would ONLY CONTAIN 1 C INDEX
		// wouldnt make sense to create another class
		registeredCourse = new ArrayList<StudentCourse>();
		waitlist = new ArrayList<StudentCourse>();
	}

	public String getPassword() {
		return this.password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getGender() {
		return gender;
	}

	public String getNationality() {
		return nationality;
	}

	public String getMatricNum() {
		return matricNum;
	}

	public static int getNumAuAvail() {
		return numAU;
	}

	// public static void minusAU() { // minus after adding course
	// 	numAU -= Course.getAU();
	// }

	// public static void plusAU() { // plus after dropping course
	// 	numAU += Course.getAU();
	// }

	public Boolean verifyPassword(String enPwd) {
		if (enPwd == password)
			return true;
		else
			return false;
	}

	public long getAccessStartTime() {
		return AccessStartDateTime;
	}

	public void setAccessStartTime(long accessStartDateTime) {
		AccessStartDateTime = accessStartDateTime;
	}

	public long getAccessEndTime() {
		return AccessEndDateTime;
	}

	public void setAccessEndTime(long accessEndDateTime) {
		AccessEndDateTime = accessEndDateTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void addCourse(StudentCourse registeredCourse) {
		this.registeredCourse.add(registeredCourse);
	}

	public ArrayList<StudentCourse> getRegisteredCourse() {
		return registeredCourse;
	}

	public void setRegisteredCourse(ArrayList<StudentCourse> registeredCourse) {
		this.registeredCourse = registeredCourse;
	}

	public ArrayList<StudentCourse> getWaitlist() {
		return waitlist;
	}

	public void setWaitlist(ArrayList<StudentCourse> waitlist) {
		this.waitlist = waitlist;
	}

	public static void removeCourseMain(String matricNum, String CourseCode) {
		DatabaseManager databaseManager = new DatabaseManager();

		ArrayList<Student> studentList = (ArrayList<Student>) databaseManager.DeserializeStudentList();

		int index = getIndexbyMatricNum(matricNum, studentList);

		studentList.get(index).removeCourse(CourseCode);

		databaseManager.SerializeStudentList(studentList);
	}

	private void removeCourse(String CourseCode) {

		for (int i = 0; i < registeredCourse.size(); i++) {
			if (registeredCourse.get(i).getCourseCode().equals(CourseCode)) {
				registeredCourse.remove(i);
			}
		}
	}

	public static Student getStudentbyMatricNum(String matricNum, ArrayList<Student> StudentList) {
		for (int i = 0; i < StudentList.size(); i++) {
			if (StudentList.get(i).getMatricNum() == matricNum) {
				return StudentList.get(i);
			}
		}
		return null;
	}

	private static int getIndexbyMatricNum(String matricNum, ArrayList<Student> StudentList) {
		for (int i = 0; i < StudentList.size(); i++) {
			if (StudentList.get(i).getMatricNum() == matricNum) {
				return i;
			}
		}
		return -1;
	}

	public static void EditStudentAccessPeriod(String matricNum, Calendar newAccessStartDateTime,
			Calendar newAccessEndDateTime) {
		DatabaseManager databaseManager = new DatabaseManager();

		ArrayList<Student> StudentList = databaseManager.DeserializeStudentList();
		Student StudentObj = getStudentbyMatricNum(matricNum, StudentList);
		int index = getIndexbyMatricNum(matricNum, StudentList);

		long newAccessStartDateTimeInms = newAccessStartDateTime.getTimeInMillis();
		long newAccessEndDateTimeInms = newAccessEndDateTime.getTimeInMillis();
		StudentObj.setAccessStartTime(newAccessStartDateTimeInms);
		StudentObj.setAccessStartTime(newAccessEndDateTimeInms);

		StudentList.set(index, StudentObj);

		databaseManager.SerializeStudentList(StudentList);
	}

	public boolean verifyUniqueMatricNum(String matricNum) {
		DatabaseManager databaseManager = new DatabaseManager();
		List<Student> StudentList = databaseManager.DeserializeStudentList();

		for (int i = 0; i < StudentList.size(); i++) {
			if (StudentList.get(i).getMatricNum() == matricNum) {
				return false;
			}
		}
		return true;
	}

	public void addStudent(String firstName, String lastName, String gender, String nationality, String matricNum,
			String username, String pwd, Calendar AccessStartTime, Calendar AccessEndTime) {
		DatabaseManager databaseManager = new DatabaseManager();

		ArrayList<Student> StudentList = databaseManager.DeserializeStudentList();

		long AccessStartTimeInms = AccessStartTime.getTimeInMillis();
		long AccessEndTimeInms = AccessEndTime.getTimeInMillis();
		Student newStudent = new Student(firstName, lastName, gender, nationality, matricNum, username, numAU, password,
				AccessStartTimeInms, AccessEndTimeInms);
		StudentList.add(newStudent);

		databaseManager.SerializeStudentList(StudentList);
	}

	public static void printCourseMain(String matricNum) {
		DatabaseManager databaseManager = new DatabaseManager();
		ArrayList<Student> studentList = (ArrayList<Student>) databaseManager.DeserializeStudentList();

		int index = getIndexbyMatricNum(matricNum, studentList);

		ArrayList<StudentCourse> registercourses =studentList.get(index).getRegisteredCourse();

		System.out.println("registered Courses: ");
		for (int i = 0; i < registercourses.size(); i++) {
			System.out.printf("%d. %s %s", i, registercourses.get(i).getCourseCode(),
					registercourses.get(i).getCourseName());
		}
	}

	public boolean checkClash(Cindex index){
		//return true if clash
		// false if no clash

		for(int i =0; i < index.getSchedule().size();i++){
			Lesson singleLesson = index.getSchedule().get(i);

			// check index start time less then end time in students schedule
			for (int u=0;u<this.getRegisteredCourse().size();u++){
				//for each cindex in student schedule
				for (int =0 ; w< getRegisteredCourse().get(u).getIndex().getSchedule().size() ){
					Lesson singleLessonStudent = getRegisteredCourse().get(u).getIndex().getSchedule().get(w);
					if(singleLesson.getStartTime().before(singleLessonStudent.getEndTime()) || singleLesson.getEndTime().after(singleLessonStudent.getStartTime())){
						// clash when the start time of the index lesson is before the student index lesson end time
						// vice versa
						return true;
					}
				}
				
			}
		}
		return false;
	}

	public void setMatricNum(String matricNum) {
		this.matricNum = matricNum;
	}
}