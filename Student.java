import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
/**
 * student class that is a subclass of User and implements Serializable so that it can be serialized into the database
 * @author kelvi
 *
 */
public class Student extends User implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * matric number of the student
	 */
	
	private String matricNum;
	/**
	 * enum Main.gender for gender of the student
	 */
	private Main.GENDER gender;
	/**
	 * first name of the student
	 */
	private String firstName;
	/**
	 * last name of the student
	 *
	 */
	private String lastName;
	/**
	 * nationality of the student
	 */
	private String nationality;
	/**
	 * email of the student
	 */
	private String email;
	/**
	 * total number of AUs registered from their registered courses
	 */
	private int numAUsRegistered;
	/**
	 * their Access start Date and time 
	 */
	private long AccessStartDateTime;
	/**
	 * their Access end Date and time
	 */
	private long AccessEndDateTime;
	/**
	 * list of student's registered courses courseCode
	 */
	private ArrayList<StudentCourse> registeredCourse;
	/**
	 * list of student's Courses that are on waitlist courseCode
	 */
	private ArrayList<StudentCourse> waitlist;

	/**
	 * default constructor for the Student Class
	 * @param firstName
	 * @param lastName
	 * @param gender
	 * @param nationality
	 * @param matricNum
	 * @param username
	 * @param password
	 * @param accessStartTime
	 * @param accessEndTime
	 * @param email
	 */
	public Student(String firstName, String lastName, Main.GENDER gender, String nationality, String matricNum, String username, String password, long accessStartTime, long accessEndTime, String email) {
		super(username,password,"student");
	
		User userObj = new User(username, password, "student");
		DatabaseManager databaseManager = new DatabaseManager();
		databaseManager.adduser(userObj);

		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.nationality = nationality;
		this.matricNum = matricNum;
		this.numAUsRegistered = 0;
		this.AccessStartDateTime = accessStartTime;
		this.AccessEndDateTime = accessEndTime;
		this.email = email;
		registeredCourse = new ArrayList<StudentCourse>();
		waitlist = new ArrayList<StudentCourse>();
	}
/**
 * returns password of the student
 */
	public String getPassword() {
		return this.password;
	}
/**
 * returns first name of the student
 * @return
 */
	public String getFirstName() {
		return firstName;
	}
/**
 * returns last name of the student
 * @return
 */
	public String getLastName() {
		return lastName;
	}
/**
 * returns enum Main.GENDER of the student
 * @return
 */
	public Main.GENDER getGender() {
		return gender;
	}
	/**
	 * set the gender of the student
	 * @param gender
	 */
	public void setGender(Main.GENDER gender) {
		this.gender = gender;
	}
/**
 * returns the nationality of the student
 * @return
 */
	public String getNationality() {
		return nationality;
	}
/**
 * returns the matric number of the student
 * @return
 */
	public String getMatricNum() {
		return matricNum;
	}
/**
 * returns the total number of AUs that the student registered
 * @return
 */
	public int getNumAuRegistered() {
		return numAUsRegistered;
	}
/**
 * minus the total number of AUs registered by the course passed into the method
 * @param course
 */
	public void minusAU(Course course) { // minus after adding course
		numAUsRegistered -= course.getAU();
	}
/**
 * add to the total number of AUs registered by the course passed into the method
 * @param course
 */
	public void plusAU(Course course) { // plus after dropping course
		numAUsRegistered += course.getAU();           //use Course or String?
	}
/**
 * Method to verify password. returns true if the password is correct else false.
 * @param enPwd
 * @return
 */
	public Boolean verifyPassword(String enPwd) {
		if (enPwd == password)
			return true;
		else
			return false;
	}
/**
 * return the access Start time of the student
 * @return
 */
	public long getAccessStartTime() {
		return AccessStartDateTime;
	}
/**
 * set the access start time for the student 
 * @param accessStartDateTime
 */
	public void setAccessStartTime(long accessStartDateTime) {
		AccessStartDateTime = accessStartDateTime;
	}
/**
 * returns the access end time of the student
 * @return
 */
	public long getAccessEndTime() {
		return AccessEndDateTime;
	}
/**
 * set the access end time for the student
 * @param accessEndDateTime
 */
	public void setAccessEndTime(long accessEndDateTime) {
		AccessEndDateTime = accessEndDateTime;
	}
/**
 * returns the username of the student
 */
	public String getUsername() {
		return username;
	}
/**
 * sets the username of the student
 */
	public void setUsername(String username) {
		this.username = username;
	}
/**
 * add a course to the students list of registered courses
 * @param registeredCourse
 */
	public void addCourse(StudentCourse registeredCourse) {
		this.registeredCourse.add(registeredCourse);
	}
/**
 * add a course to the student's list of waitlist courses
 * @param registeredCourse
 */
	public void addWaitlist(StudentCourse registeredCourse) {
		//remove any exiting waitlist for indexes of the same course
		for (int i=0; i <waitlist.size();i++){
			if( waitlist.get(i).getCourseCode().equals(registeredCourse.getCourseCode())){
				waitlist.remove(i);
			}
		}
		this.waitlist.add(registeredCourse);
	}
	
/**
 * returns the arrayList of the students registered courses
 * @return
 */
	public ArrayList<StudentCourse> getRegisteredCourse() {
		return registeredCourse;
	}
/**
 * sets an ArrayList of registered courses for the student
 * @param registeredCourse
 */
	public void setRegisteredCourse(ArrayList<StudentCourse> registeredCourse) {
		this.registeredCourse = registeredCourse;
	}
/**
 * returns the arraylist of students waitlist courses
 * @return
 */
	public ArrayList<StudentCourse> getWaitlist() {
		return waitlist;
	}
/**
 * sets an ArrayList of waitlist courses for the student
 * @param waitlist
 */
	public void setWaitlist(ArrayList<StudentCourse> waitlist) {
		this.waitlist = waitlist;
	}
/**
 * remove a specific course from the students list of registered courses
 * @param CourseCode
 */

	public void removeCourseFromRegORWait(String CourseCode) {
		//search course in reg courses
		for (int i = 0; i < registeredCourse.size(); i++) {
			if (registeredCourse.get(i).getCourseCode().equals(CourseCode)) {

				SendMail sendMail = new SendMail();
				sendMail.droppedCourse(registeredCourse.get(i), email);
				
				registeredCourse.remove(i);
				

			}
		}
		//search course in waitlist as well
		for (int i = 0; i < waitlist.size(); i++) {
			if (waitlist.get(i).getCourseCode().equals(CourseCode)) {

				SendMail sendMail = new SendMail();
				sendMail.droppedCourse(waitlist.get(i), email);
				
				waitlist.remove(i);
				

			}
		}
	}

	/**
	 * check whether any of student's registered and waitlist courses clashes with the index that is passed in. returns true if there is any clashes, otherwise false if there is no clashes
	 * @param index
	 * @return
	 */

	public boolean checkClash(Cindex index){
		//NOTE HAVE TO CHECK FOR WAITLIST AS WELL
		// show the clash timings
		//return true if clash
		// false if no clash
		int clashCounter =0;
		DatabaseManager databaseManager = new DatabaseManager();

		for(int i =0; i < index.getSchedule().size();i++){
			Lesson singleLesson = index.getSchedule().get(i);

			// check index start time less then end time in students schedule
			for (int u=0;u<this.getRegisteredCourse().size();u++){
				//for each cindex in student schedule
				Course studentRegCourse = databaseManager.searchCourse(getRegisteredCourse().get(u).getCourseCode());
				Cindex studentRegIndex = studentRegCourse.searchCindex(getRegisteredCourse().get(u).getCourseIndex());
				for (int w = 0; w< studentRegIndex.getSchedule().size(); w++ ){
					Lesson singleLessonStudent = studentRegIndex.getSchedule().get(w);
					if(singleLesson.getDayoftheWeek().equals(singleLessonStudent.getDayoftheWeek()) ){
						if(singleLesson.getOddorEven().equals(Main.ODD_EVEN.OOD_AND_EVEN) || singleLessonStudent.getOddorEven().equals(Main.ODD_EVEN.OOD_AND_EVEN) || singleLessonStudent.getOddorEven().equals(singleLesson.getDayoftheWeek())){
							if(singleLesson.getStartTime().before(singleLessonStudent.getStartTime())){

								if(singleLesson.getEndTime().before(singleLessonStudent.getStartTime())){
									//no clash
								}else{
									//clash
									clashCounter++;
									System.out.printf("Index Clash with registered course : %s %S index %s\n",studentRegCourse.getCourseCode(),studentRegCourse.getCourseName(),studentRegIndex.getIndexName());
									System.out.println("registered index timing : lesson type / start time - end time");
									for (int j =0; j < studentRegIndex.getSchedule().size();j++){

										System.out.printf("%s / %s - %s ",studentRegIndex.getSchedule().get(j).getStartTime(),studentRegIndex.getSchedule().get(j).getEndTime() );
									}

								}
							}else{
								if(singleLesson.getStartTime().before(singleLessonStudent.getEndTime())){
									//clash
									clashCounter++;
									System.out.printf("Index Clash with registered course : %s %S index %s\n",studentRegCourse.getCourseCode(),studentRegCourse.getCourseName(),studentRegIndex.getIndexName());
								}else{
									//no clash

								}
							}
						}
					}

				}
				
			}
		}

		for(int i =0; i < index.getSchedule().size();i++){
			Lesson singleLesson = index.getSchedule().get(i);

			// check index start time less then end time in students schedule
			for (int u=0;u<this.getWaitlist().size();u++){
				//for each cindex in student schedule
				Course studentWaitlistCourse = databaseManager.searchCourse(getWaitlist().get(u).getCourseCode());
				Cindex studentWaitlistIndex = studentWaitlistCourse.searchCindex(getWaitlist().get(u).getCourseIndex());
				for (int w = 0; w< studentWaitlistIndex.getSchedule().size(); w++ ){
					Lesson singleLessonStudent = studentWaitlistIndex.getSchedule().get(w);

					// only checks for clash when day is equal to eeach other and when both the lesson is same odd or even or when either of them is oddandeven
					if(singleLesson.getDayoftheWeek().equals(singleLessonStudent.getDayoftheWeek()) ){
						if(singleLesson.getOddorEven().equals(Main.ODD_EVEN.OOD_AND_EVEN) || singleLessonStudent.getOddorEven().equals(Main.ODD_EVEN.OOD_AND_EVEN) || singleLessonStudent.getOddorEven().equals(singleLesson.getDayoftheWeek())){
							if(singleLesson.getStartTime().before(singleLessonStudent.getStartTime())){

								if(singleLesson.getEndTime().before(singleLessonStudent.getStartTime())){
									//no clash
								}else{
									//clash
									clashCounter++;
									System.out.printf("Index Clash with waitlist course : %s %S index %s\n",studentWaitlistCourse.getCourseCode(),studentWaitlistCourse.getCourseName(),studentWaitlistIndex.getIndexName());
								}
							}else{
								if(singleLesson.getStartTime().before(singleLessonStudent.getEndTime())){
									//clash
									clashCounter++;
									System.out.printf("Index Clash with waitlist course : %s %S index %s\n",studentWaitlistCourse.getCourseCode(),studentWaitlistCourse.getCourseName(),studentWaitlistIndex.getIndexName());
								}else{
									//no clash

								}
							}
						}
					}

				}

			}
		}



		if (clashCounter > 0){
			return true;
		}else
		{
			return false;
		}
	}

	/**
	 * sets the matric number of the student
	 * @param matricNum
	 */
	public void setMatricNum(String matricNum) {
		this.matricNum = matricNum;
	}
/**
 * returns the email of the student
 * @return
 */
	public String getEmail() {
		return email;
	}
/**
 * sets the email of the student
 * @param email
 */
	public void setEmail(String email) {
		this.email = email;
	}
	public static void main(String[] args) {
		DatabaseManager databaseManager = new DatabaseManager();
		// Student studentObj = (Student) databaseManager.getObjectbyUsername("student");
		long accessStartDateTime = new GregorianCalendar(2020, 01, 01, 00, 00).getTimeInMillis();
		long accessEndDateTime = new GregorianCalendar(2021, 01, 01, 00, 00).getTimeInMillis();
		
		Student studentObj = new Student("melvin", "chua",Main.GENDER.MALE , "Singapore", "U1234567G", "student", "student", accessStartDateTime, accessEndDateTime, "chua0946@e.ntu.edu.sg");
		Student studentObj2 = new Student("kelvin", "chua",Main.GENDER.MALE , "Singapore", "U2345678G", "student2", "student2", accessStartDateTime, accessEndDateTime, "chua0946@e.ntu.edu.sg");
		// System.out.println(studentObj.getPassword()); 
		// System.out.println(studentObj.getUsername()); 
		// System.out.println(studentObj.getAccessEndTime()); 
		

		ArrayList<Student> studentList = new ArrayList<Student>();
		studentList.add(studentObj);
		studentList.add(studentObj2);

        databaseManager.SerializeStudentList(studentList);
	}
/**
 * returns the StudentCourse object from the student's waitlist
 * @param courseCode
 * @return
 */
	public StudentCourse checkWaitlist(String courseCode) {
		for (int i =0 ; i <waitlist.size(); i++){
			if (waitlist.get(i).getCourseCode().equals(courseCode)){
				return waitlist.get(i);
			}
		}
		return null;
	}
/**
 * method that adds the course from the students waitlist into their registered courses list
 * @param courseCode
 */
	public void courseAllocatedFromWaitlist(String courseCode) {
		DatabaseManager databaseManager = new DatabaseManager();
		for (int i = 0; i <waitlist.size();i++){
			if (waitlist.get(i).getCourseCode().equals(courseCode)){
				this.addCourse(waitlist.get(i));
				// email
				SendMail sendMail = new SendMail();
				sendMail.allocatedRegfromWaitlist(waitlist.get(i), email);
				//remove
				waitlist.remove(i);
			}
		}
		
	}
/**
 * method that checks clashes for a new index , not considering the old index from the same course for both the students registered and waitlist courses. used for changing of index function.
 * returns true if there is any clashes otherwise false.
 * @param newindex
 * @param courseCode
 * @return
 */
	public boolean checkClashforSameCourseNewIndex(Cindex newindex,String courseCode) {
		// won't check the student course with the same course code as this
		// show the clash timings
		//return true if clash
		// false if no clash
		int clashCounter =0;
		DatabaseManager databaseManager = new DatabaseManager();

		for(int i =0; i < newindex.getSchedule().size();i++){
			Lesson singleLesson = newindex.getSchedule().get(i);

			// check index start time less then end time in students schedule
			for (int u=0;u<this.getRegisteredCourse().size();u++){
				//for each cindex in student schedule
				if (!getRegisteredCourse().get(u).getCourseCode().equals(courseCode)){
					Course studentRegCourse = databaseManager.searchCourse(getRegisteredCourse().get(u).getCourseCode());
					Cindex studentRegIndex = studentRegCourse.searchCindex(getRegisteredCourse().get(u).getCourseIndex());

					for (int w = 0; w< studentRegIndex.getSchedule().size(); w++ ){
						Lesson singleLessonStudent = studentRegIndex.getSchedule().get(w);
						if(singleLesson.getDayoftheWeek().equals(singleLessonStudent.getDayoftheWeek()) ){
							if(singleLesson.getOddorEven().equals(Main.ODD_EVEN.OOD_AND_EVEN) || singleLessonStudent.getOddorEven().equals(Main.ODD_EVEN.OOD_AND_EVEN) || singleLessonStudent.getOddorEven().equals(singleLesson.getDayoftheWeek())){
								if(singleLesson.getStartTime().before(singleLessonStudent.getStartTime())){

									if(singleLesson.getEndTime().before(singleLessonStudent.getStartTime())){
										//no clash
									}else{
										//clash
										clashCounter++;
										System.out.printf("Index Clash with registered course : %s %S index %s\n",studentRegCourse.getCourseCode(),studentRegCourse.getCourseName(),studentRegIndex.getIndexName());
										System.out.println("registered index timing : lesson type / start time - end time");
										for (int j =0; j < studentRegIndex.getSchedule().size();j++){

											System.out.printf("%s / %s - %s ",studentRegIndex.getSchedule().get(j).getStartTime(),studentRegIndex.getSchedule().get(j).getEndTime() );
										}

									}
								}else{
									if(singleLesson.getStartTime().before(singleLessonStudent.getEndTime())){
										//clash
										clashCounter++;
										System.out.printf("Index Clash with registered course : %s %S index %s\n",studentRegCourse.getCourseCode(),studentRegCourse.getCourseName(),studentRegIndex.getIndexName());
									}else{
										//no clash

									}
								}
							}
						}

					}

				}
			}
				}


		for(int i =0; i < newindex.getSchedule().size();i++){
			Lesson singleLesson = newindex.getSchedule().get(i);

			// check index start time less then end time in students schedule
			for (int u=0;u<this.getWaitlist().size();u++){
				//for each cindex in student schedule
				if (!getWaitlist().get(u).getCourseCode().equals(courseCode)){
					Course studentWaitlistCourse = databaseManager.searchCourse(getWaitlist().get(u).getCourseCode());
					Cindex studentWaitlistIndex = studentWaitlistCourse.searchCindex(getWaitlist().get(u).getCourseIndex());
					for (int w = 0; w< studentWaitlistIndex.getSchedule().size(); w++ ){
						Lesson singleLessonStudent = studentWaitlistIndex.getSchedule().get(w);

						// only checks for clash when day is equal to eeach other and when both the lesson is same odd or even or when either of them is oddandeven
						if(singleLesson.getDayoftheWeek().equals(singleLessonStudent.getDayoftheWeek()) ){
							if(singleLesson.getOddorEven().equals(Main.ODD_EVEN.OOD_AND_EVEN) || singleLessonStudent.getOddorEven().equals(Main.ODD_EVEN.OOD_AND_EVEN) || singleLessonStudent.getOddorEven().equals(singleLesson.getDayoftheWeek())){
								if(singleLesson.getStartTime().before(singleLessonStudent.getStartTime())){

									if(singleLesson.getEndTime().before(singleLessonStudent.getStartTime())){
										//no clash
									}else{
										//clash
										clashCounter++;
										System.out.printf("Index Clash with waitlist course : %s %S index %s\n",studentWaitlistCourse.getCourseCode(),studentWaitlistCourse.getCourseName(),studentWaitlistIndex.getIndexName());
									}
								}else{
									if(singleLesson.getStartTime().before(singleLessonStudent.getEndTime())){
										//clash
										clashCounter++;
										System.out.printf("Index Clash with waitlist course : %s %S index %s\n",studentWaitlistCourse.getCourseCode(),studentWaitlistCourse.getCourseName(),studentWaitlistIndex.getIndexName());
									}else{
										//no clash

									}
								}
							}
						}

					}

				}
			}
				}




		if (clashCounter > 0){
			return true;
		}else
		{
			return false;
		}
	}


}