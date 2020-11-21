import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    private String CourseCode; // changed int to string cuz eg. CE2001
    private String CourseName;
    private String CourseDescription;
    private String School;
    private int AU;
    private ArrayList<Cindex> listCindex;

    public Course(String cc, String cn, String d, String school, int AU,
            ArrayList<Cindex> ListCindex) {
        this.CourseCode = cc;
        this.CourseName = cn;
        this.CourseDescription = d;
        this.AU = AU;
        this.School = school;
        this.listCindex = ListCindex;
    }

    public int getIndexOfCindex(String cindexName) {
        for (int i = 0; i < this.listCindex.size(); i++) {
            if (this.listCindex.get(i).getIndexName().equals(cindexName)) {
                return i;
            }
        }
        return -1;
    }

    public String getCourseCode() {
        return CourseCode;
    }

    public void setCourseCode(String courseCode) {
        CourseCode = courseCode;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getCourseDescription() {
        return CourseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        CourseDescription = courseDescription;
    }

    public void setAU(int au) {
        this.AU = au;
    }

    public int getAU() {
        return this.AU;
    }

    public ArrayList<Cindex> getListCindex() {
        return listCindex;
    }

    public void setListCindex(ArrayList<Cindex> Cindex) {
        this.listCindex = Cindex;
    }

    public String getSchool() {
        return School;
    }

    public void setSchool(String school) {
        School = school;
    }

    public boolean removeStudentFromReg(String username) {
        // search student throughout the Cindex and remove
        for (int i = 0; i < this.listCindex.size(); i++) {
            for (int j = 0; j < this.listCindex.get(i).getRegisteredStudents().size(); j++) {
                if (this.listCindex.get(i).getRegisteredStudents().get(j).getUsername().equals(username)) {
                    // removing student from reg
                    this.listCindex.get(i).getRegisteredStudents().remove(j);

                    return true;
                }
            }

        }
        return false;
    }

    // push waitlist for every indexif any
    public void pushWaitlist() {
        DatabaseManager databaseManager = new DatabaseManager();
        // check if num of reg stud is less than cpacity
        for (int i = 0; i < listCindex.size(); i++) {
            Cindex singleIndex = listCindex.get(i);
            if (singleIndex.getRegisteredStudents().size() < singleIndex.getCapacity()
                    && singleIndex.getWaitList().size() != 0) {
                Student studFromWaitlist = singleIndex.getWaitList().get(0);
                singleIndex.addRegisteredStudent(studFromWaitlist);
                singleIndex.getWaitList().remove(0);

                // create a new studentCourse

                Student stud = singleIndex.getRegisteredStudents().get(singleIndex.getCapacity() - 1);
                stud.addCourse(newlyregisteredCourse);
                stud.removeWaitlistCourse();

                databaseManager.updateDatabase(stud);

                SendMail sendMail = new SendMail();

                String EmailContent = "Dear Sir/Mdm,\n This a confirmation email that your course " + CourseCode + " "
                        + CourseName + "for index " + singleIndex.getIndexName()
                        + " have been successfully added\n Thank You\n NTU STARS";
                sendMail.sendgmail("melvinchuaqwerty@gmail.com", "melvinchuaqwerty@gmail.com", "s9825202i",
                        singleIndex.getRegisteredStudents().get(singleIndex.getCapacity() - 1).getEmail(),
                        "Course Added", EmailContent);
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Course> courseList = new ArrayList<Course>();
        DatabaseManager databaseManager = new DatabaseManager();
        
        ArrayList<Cindex> ListCindex = new ArrayList<Cindex>();

        DateFormat timeformat = new SimpleDateFormat("HHmm");
        Date startTimeParsedLect=null;
        try {
            startTimeParsedLect = timeformat.parse("1000");
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Date endTimeParsedLect=null;
        try {
            endTimeParsedLect = timeformat.parse("1100");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Lesson lessonObj2 = new Lecture(startTimeParsedLect, endTimeParsedLect, "lt2", "tuesday");
        ArrayList<Lesson> schedule2 = new ArrayList<Lesson>();
        Cindex cindexObj2 = new Cindex("2", 1);
        
        schedule2.add(lessonObj2);
        cindexObj2.setSchedule(schedule2);
        ListCindex.add(cindexObj2);

        Course courseObj = new Course("c1", "course1", "course description","scse", 3, ListCindex);

        courseList.add(courseObj);
        databaseManager.SerializeCourseList(courseList);

    }

	public void removeStudentfromWailist(String username) {
        DatabaseManager databaseManager = new DatabaseManager();
        for (int i = 0; i<listCindex.size();i++){
            Cindex singleCindex = listCindex.get(i);
            for (int j =0; j < singleCindex.getWaitList().size();j++){
                if (singleCindex.getWaitList().get(j).getUsername().equals(username)){
                    singleCindex.getWaitList().remove(j);
                }
            }   
        }
        
	}

	public Cindex searchCindex(String courseIndex) {
        for (int i = 0 ; i < listCindex.size();i++){
            if(listCindex.get(i).getIndexName().equals(courseIndex)){
                return listCindex.get(i);
            }
            
        }
		return null;
	}


	
}
