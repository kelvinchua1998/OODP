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

    public boolean checkIfStudentFromRegAndWaitlist(String username) {
        // search student throughout the Cindex and remove
        for (int i = 0; i < this.listCindex.size(); i++) {
            for (int j = 0; j < this.listCindex.get(i).getRegisteredStudents().size(); j++) {
                if (this.listCindex.get(i).getRegisteredStudents().get(j).equals(username)) {
                    return true;
                }
            }
            for (int j = 0; j < this.listCindex.get(i).getWaitList().size(); j++) {
                if (this.listCindex.get(i).getWaitList().get(j).equals(username)) {
                    return true;
                }
            }

        }
        return false;
    }

    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager();

//COURSE 1
        ArrayList<Course> courseList = new ArrayList<Course>();
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

        Lesson lessonObj1 = new Lecture(startTimeParsedLect, endTimeParsedLect, "lt2", Main.DAY_OF_WEEK.TUESDAY);

        Date starttime=null;
        try {
            starttime = timeformat.parse("1200");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Date endtime=null;
        try {
            endtime = timeformat.parse("1300");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Lesson lessonObj2 = new Tutorial(starttime,endtime,"tr1",
                    Main.DAY_OF_WEEK.TUESDAY);


        Date starttime2=null;
        try {
            starttime2 = timeformat.parse("1300");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Date endtime2=null;
        try {
            endtime2 = timeformat.parse("1400");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Lesson lessonObj3 = new Labs(starttime2, endtime2, "lab1", Main.DAY_OF_WEEK.TUESDAY, Main.ODD_EVEN.EVEN);

        ArrayList<Lesson> schedule2 = new ArrayList<Lesson>();
        Cindex cindexObj2 = new Cindex("1", 2);
        
        schedule2.add(lessonObj1);
        schedule2.add(lessonObj2);
        schedule2.add(lessonObj3);
        cindexObj2.setSchedule(schedule2);
        ListCindex.add(cindexObj2);

        Course courseObj = new Course("C1", "course1", "course description","scse", 3, ListCindex);

        courseList.add(courseObj);

        //COURSE 2
        
        databaseManager.SerializeCourseList(courseList);

    }

	public void removeStudentfromWailist(String username) {
        DatabaseManager databaseManager = new DatabaseManager();
        for (int i = 0; i<listCindex.size();i++){
            Cindex singleCindex = listCindex.get(i);
            for (int j =0; j < singleCindex.getWaitList().size();j++){
                if (singleCindex.getWaitList().get(j).equals(username)){
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

        //checks reg for the stud
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

        //checks waitlist as well
        for (int i = 0; i < listCindex.size(); i++) {
            for (int j = 0; j < listCindex.get(i).getWaitList().size(); j++) {
                if (listCindex.get(i).getWaitList().get(j).equals(username)) {
                    //remove student
                    listCindex.get(i).getWaitList().remove(j);
                    
                }
            }

        }

	}


	
}
