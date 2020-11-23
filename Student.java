import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Student extends User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String matricNum;
	private String gender;
	private String firstName;
	private String lastName;
	private String nationality;
	private String email;
	private int numAUsRegistered;
	private long AccessStartDateTime;
	private long AccessEndDateTime;
	private ArrayList<StudentCourse> registeredCourse;
	private ArrayList<StudentCourse> waitlist;

	public Student(String firstName, String lastName, String gender, String nationality, String matricNum, String username, String password, long accessStartTime, long accessEndTime, String email) {
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

	public int getNumAuRegistered() {
		return numAUsRegistered;
	}

	public void minusAU(Course course) { // minus after adding course
		numAUsRegistered -= course.getAU();
	}

	public void plusAU(Course course) { // plus after dropping course
		numAUsRegistered += course.getAU();           //use Course or String?
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
		//remove any exiting waitlist for indexes of the same course
		for (int i=0; i <waitlist.size();i++){
			if( waitlist.get(i).getCourseCode().equals(registeredCourse.getCourseCode())){
				waitlist.remove(i);
			}
		}
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

				SendMail sendMail = new SendMail();
				sendMail.droppedCourse(registeredCourse.get(i), email);
				
				registeredCourse.remove(i);
				

			}
		}
	}

	

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

	public void setMatricNum(String matricNum) {
		this.matricNum = matricNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public static void main(String[] args) {
		DatabaseManager databaseManager = new DatabaseManager();
		// Student studentObj = (Student) databaseManager.getObjectbyUsername("student");
		long accessStartDateTime = new GregorianCalendar(2020, 01, 01, 00, 00).getTimeInMillis();
		long accessEndDateTime = new GregorianCalendar(2021, 01, 01, 00, 00).getTimeInMillis();
		
		Student studentObj = new Student("melvin", "chua", "male", "Singapore", "U1234567G", "student", "student", accessStartDateTime, accessEndDateTime, "chua0946@e.ntu.edu.sg");
		Student studentObj2 = new Student("kelvin", "chua", "male", "Singapore", "U2345678G", "student2", "student2", accessStartDateTime, accessEndDateTime, "chua0946@e.ntu.edu.sg");
		// System.out.println(studentObj.getPassword()); 
		// System.out.println(studentObj.getUsername()); 
		// System.out.println(studentObj.getAccessEndTime()); 
		

		ArrayList<Student> studentList = new ArrayList<Student>();
		studentList.add(studentObj);
		studentList.add(studentObj2);

        databaseManager.SerializeStudentList(studentList);
	}

	public StudentCourse checkWaitlist(String courseCode) {
		for (int i =0 ; i <waitlist.size(); i++){
			if (waitlist.get(i).getCourseCode().equals(courseCode)){
				return waitlist.get(i);
			}
		}
		return null;
	}

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