import java.io.Serializable;

public class Student implements Serializable{

	private static final long serialVersionUID = 1L;

	private String matricNum;
	private String gender;
	private String firstName;
	private String lastName;
	private String nationality;
	private String password;
	private int numAU;
	//Courses listC = new Courses[];     //array size set after students setCourses?
	//Courses waitlist = new Courses[];
	
	public Student(String firstName, String lastName, String gender, String nationality, String matricNum, int numAU, String pwd) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.nationality = nationality;
		this.matricNum = matricNum;
		this.numAU = numAU;
		password = pwd;
		//all these info get from separate file?
	}

	public String getPassword(){
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
	
	public int getNumAuAvail(){
		return numAU;
	}

	public void minusAU(){   //minus after adding course
		//numAU -= Course.getNumAU();
	}

	public void plusAU(){  //plus after dropping course
		//numAu += Course.getNumAU();
	}

	public Boolean verifyPassword(String enPwd) {
		if(enPwd == password)
			return true;
		else
			return false;
	}
	
	/*public String getCourses() {
		return courses[];                 //notsure
	}
	
	public String getWaitlist() {
		return waitlist[];
	}*/
	
}