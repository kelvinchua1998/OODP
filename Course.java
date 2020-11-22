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

    public boolean checkIfStudentFromReg(String username) {
        // search student throughout the Cindex and remove
        for (int i = 0; i < this.listCindex.size(); i++) {
            for (int j = 0; j < this.listCindex.get(i).getRegisteredStudents().size(); j++) {
                if (this.listCindex.get(i).getRegisteredStudents().get(j).equals(username)) {
                    return true;
                }
            }

        }
        return false;
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

	public void removeStudentFromIndex(String username) {
        DatabaseManager databaseManager= new DatabaseManager();

        for (int i = 0; i < listCindex.size(); i++) {
            for (int j = 0; j < listCindex.get(i).getRegisteredStudents().size(); j++) {
                if (listCindex.get(i).getRegisteredStudents().get(j).equals(username)) {
                    //remove student
                    listCindex.get(i).getRegisteredStudents().remove(j);
                    
                    if (listCindex.get(i).getWaitList().size() != 0){
                        //move student from waitlist 
                        listCindex.get(i).addRegisteredStudent(listCindex.get(i).getWaitList().get(0));
                        Student studAllocated = (Student) databaseManager
                                .getObjectbyUsername(listCindex.get(i).getWaitList().get(0));

                        studAllocated.courseAllocatedFromWaitlist(CourseCode);
                        listCindex.get(i).getWaitList().remove(0);
                        // update database for student allocated
                        databaseManager.updateDatabase(studAllocated);
                    }
                    
                   
                }
            }

        }
	}


	
}
