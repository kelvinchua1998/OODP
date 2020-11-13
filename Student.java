import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student extends User implements Serializable{
	private static final long serialVersionUID = 1L;
	private int userID;
	private String matricNum;
	private String gender;
	private String firstName;
	private String lastName;
	private String nationality;
	private int numAU;
	private long AccessStartDateTime;
	private long AccessEndDateTime;
	private ArrayList<StudentCourse> registeredCourse;
	private ArrayList<StudentCourse> waitlist;

	public Student(String firstName, String lastName, String gender, String nationality,int userID, String matricNum, String username, String password, int numAU, long accessStartDateTime, long accessEndDateTime) {
		super(username,password,"student",userID);

		User userObj = new User(username, password, "student",userID);
		DatabaseManager databaseManager = new DatabaseManager();
		databaseManager.adduser(userObj);

		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.nationality = nationality;
		this.matricNum = matricNum;
		this.numAU = numAU;
		this.AccessStartDateTime = accessEndDateTime;
		this.AccessEndDateTime = accessEndDateTime;
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

	public int getNumAuAvail() {
		return numAU;
	}

	public void minusAU(Course course) { // minus after adding course
		numAU -= course.getAU();
	}

	public void plusAU(Course course) { // plus after dropping course
		numAU += course.getAU();           //use Course or String?
	}

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

	public void addWaitlist(StudentCourse registeredCourse) {
		this.waitlist.add(registeredCourse);
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

	public void removeCourse(String CourseCode) {
		for (int i = 0; i < registeredCourse.size(); i++) {
			if (registeredCourse.get(i).getCourseCode().equals(CourseCode)) {
				registeredCourse.remove(i);
			}
		}
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

	

	public boolean checkClash(Cindex index){
		//return true if clash
		// false if no clash

		for(int i =0; i < index.getSchedule().size();i++){
			Lesson singleLesson = index.getSchedule().get(i);

			// check index start time less then end time in students schedule
			for (int u=0;u<this.getRegisteredCourse().size();u++){
				//for each cindex in student schedule
				for (int w=0 ; w< getRegisteredCourse().get(u).getIndex().getSchedule().size(); w++ ){
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

	public static void main(String[] args) {
		Student studentObj = new Student("melvin", "Chua", "male",  "singapore", 2,"U1234567G","student","student", 0,  0, 0);
		DatabaseManager databaseManager = new DatabaseManager();
		ArrayList<Student> studentList = new ArrayList<Student>();
		
        studentList.add(studentObj);

        databaseManager.SerializeStudentList(studentList);
	}
}