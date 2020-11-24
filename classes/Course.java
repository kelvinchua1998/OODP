package classes;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class is to identify different courses. It implements Serializable so that it can be serialized into the database
 * @author kelvin melvin
 */
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * course code of the course
     */
    private String CourseCode; //string cuz eg. CE2001
    /**
     * name of the course
     */
    private String CourseName;
    /**
     * description of the course
     */
    private String CourseDescription;
    /**
     * which school the course belongs to
     */
    private String School;
    /**
     * number of academic units of the course
     */
    private int AU;
    /**
     * list of the different indexes in the course
     */
    private ArrayList<Cindex> listCindex;

/**
 * default constructor to create the Course class
 * @param cc course code of the course
 * @param cn course name of the course
 * @param d course description of the course
 * @param AU number of AUs of the course
 * @param school school which the course belongs to
 * @param ListCindex list of indexes of the course
 */
    public Course(String cc, String cn, String d, String school, int AU,
            ArrayList<Cindex> ListCindex) {
        this.CourseCode = cc;
        this.CourseName = cn;
        this.CourseDescription = d;
        this.AU = AU;
        this.School = school;
        this.listCindex = ListCindex;
    }

/**
 * gets the index of the specified Cindex in the list of Cindex of the course. If Cindex is not found, -1 is returned
 * @param cindexName String
 * @return index of specified Cindex, if it exists in the list of Cindex. -1 otherwise.
 */
    public int getIndexOfCindex(String cindexName) {
        for (int i = 0; i < this.listCindex.size(); i++) {
            if (this.listCindex.get(i).getIndexName().equals(cindexName)) {
                return i;
            }
        }
        return -1;
    }
/**
 * gets the course code of the course
 * @return code of the course
 */
    public String getCourseCode() {
        return CourseCode;
    }
/**
 * sets the course code of the course
 * @param courseCode the course code for the course
 */
    public void setCourseCode(String courseCode) {
        CourseCode = courseCode;
    }
/**
 * gets the name of the course
 * @return course name of the course
 */
    public String getCourseName() {
        return CourseName;
    }
/**
 * sets the name of the course
 * @param courseName the name to be set for the course
 */
    public void setCourseName(String courseName) {
        CourseName = courseName;
    }
/**
 * gets the description of the course
 * @return Course Description of the course
 */
    public String getCourseDescription() {
        return CourseDescription;
    }
/**
 * sets the description of the course
 * @param courseDescription the description to be set
 */
    public void setCourseDescription(String courseDescription) {
        CourseDescription = courseDescription;
    }
/**
 * sets the number of AU for the course
 * @param au number of AUs
 */
    public void setAU(int au) {
        this.AU = au;
    }
/**
 * gets the number of AU for the course
 * @return value of AU
 */
    public int getAU() {
        return this.AU;
    }
/**
 * gets the list of Cindex for the course
 * @return ArrayList of Cindex
 */
    public ArrayList<Cindex> getListCindex() {
        return listCindex;
    }
/**
 * sets the list of Cindex for the course
 * @param Cindex ArrayList
 */
    public void setListCindex(ArrayList<Cindex> Cindex) {
        this.listCindex = Cindex;
    }
/** 
 * gets the school the course belongs to 
 * @return School that course belongs to
 */
    public String getSchool() {
        return School;
    }
/**
 * sets the school the course belongs to
 * @param school school to be set
 */
    public void setSchool(String school) {
        School = school;
    }
/**
 * checks if the specified student is in the list of registered students or in the waitlist
 * @param username String
 * @return true if student is in list of registered students or the waitlist. false otherwise.
 */
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
        //course1 (CZ2001, 10124)
        DatabaseManager databaseManager = new DatabaseManager();
        ArrayList<Course> courseList = new ArrayList<Course>();

        ArrayList<Cindex> ListCindex = new ArrayList<Cindex>();

        DateFormat timeformat = new SimpleDateFormat("HHmm");
        Date startTimeParsedLect=null;
        try {
            startTimeParsedLect = timeformat.parse("0830");
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Date endTimeParsedLect=null;
        try {
            endTimeParsedLect = timeformat.parse("0930");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Lesson lessonObj1 = new Lecture(startTimeParsedLect, endTimeParsedLect, "lt10", Main.DAY_OF_WEEK.FRIDAY);

        Date starttime=null;
        try {
            starttime = timeformat.parse("1630");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Date endtime=null;
        try {
            endtime = timeformat.parse("1730");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Lesson lessonObj2 = new Tutorial(starttime,endtime,"tr+9",
                    Main.DAY_OF_WEEK.THURSDAY);


        Date starttime2=null;
        try {
            starttime2 = timeformat.parse("1030");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Date endtime2=null;
        try {
            endtime2 = timeformat.parse("1230");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Lesson lessonObj3 = new Labs(starttime2, endtime2, "hwlab1", Main.DAY_OF_WEEK.WEDNESDAY, Main.ODD_EVEN.EVEN);

        ArrayList<Lesson> schedule2 = new ArrayList<Lesson>();
        Cindex cindexObj2 = new Cindex("10124", 2);
        
        schedule2.add(lessonObj1);
        schedule2.add(lessonObj2);
        schedule2.add(lessonObj3);
        cindexObj2.setSchedule(schedule2);
        ListCindex.add(cindexObj2);

        Course courseObj = new Course("CZ2001", "Algorithms", "Introduction to algorithms, basics for analysis of algorithms, sorting, searching, graphs, basic computability and complexity theory","scse", 3, ListCindex);

        courseList.add(courseObj);

        //course2 (CZ2002, 10126)
        ArrayList<Cindex> ListCindex1 = new ArrayList<Cindex>();
        Date startTimeParsedLect1=null;
        try {
            startTimeParsedLect1 = timeformat.parse("0830");
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Date endTimeParsedLect1=null;
        try {
            endTimeParsedLect1 = timeformat.parse("0930");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Lesson lessonObj4 = new Lecture(startTimeParsedLect1, endTimeParsedLect1, "lt11", Main.DAY_OF_WEEK.THURSDAY);

        Date starttime3=null;
        try {
            starttime3 = timeformat.parse("0930");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Date endtime3=null;
        try {
            endtime3 = timeformat.parse("1030");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Lesson lessonObj5 = new Tutorial(starttime3,endtime3,"tr+17",
                    Main.DAY_OF_WEEK.THURSDAY);


        Date starttime4=null;
        try {
            starttime4 = timeformat.parse("1430");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Date endtime4=null;
        try {
            endtime4 = timeformat.parse("1630 ");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Lesson lessonObj6 = new Labs(starttime4, endtime4, "spl", Main.DAY_OF_WEEK.MONDAY, Main.ODD_EVEN.ODD);

        ArrayList<Lesson> schedule3 = new ArrayList<Lesson>();
        Cindex cindexObj3 = new Cindex("10126", 2);
        
        schedule3.add(lessonObj4);
        schedule3.add(lessonObj5);
        schedule3.add(lessonObj6);
        cindexObj3.setSchedule(schedule3);
        ListCindex1.add(cindexObj3);

        Course courseObj2 = new Course("CZ2002", "Object Oriented Design & Programming", "You will discover how to describe these concepts using appropriate UML diagrams. Finally, you will also learn good design principles for reuse, and to realise these principles using object-oriented programming languages such as Java and/or C++","scse", 3, ListCindex1);

        courseList.add(courseObj2);

        //course3 (CZ2003, 10128)
        ArrayList<Cindex> ListCindex2 = new ArrayList<Cindex>();
        Date startTimeParsedLect2=null;
        try {
            startTimeParsedLect2 = timeformat.parse("1130");
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Date endTimeParsedLect2=null;
        try {
            endTimeParsedLect2 = timeformat.parse("1230");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Lesson lessonObj7 = new Lecture(startTimeParsedLect2, endTimeParsedLect2, "online", Main.DAY_OF_WEEK.THURSDAY);

        Date starttime5=null;
        try {
            starttime5 = timeformat.parse("1530");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Date endtime5=null;
        try {
            endtime5 = timeformat.parse("1630");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Lesson lessonObj8 = new Tutorial(starttime5,endtime5,"tr+15",
                    Main.DAY_OF_WEEK.THURSDAY);


        Date starttime6=null;
        try {
            starttime6 = timeformat.parse("1230");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Date endtime6=null;
        try {
            endtime6 = timeformat.parse("1430 ");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Lesson lessonObj9 = new Labs(starttime6, endtime6, "swlab3", Main.DAY_OF_WEEK.WEDNESDAY, Main.ODD_EVEN.EVEN);

        ArrayList<Lesson> schedule4 = new ArrayList<Lesson>();
        Cindex cindexObj4 = new Cindex("10128", 2);
        
        schedule4.add(lessonObj7);
        schedule4.add(lessonObj8);
        schedule4.add(lessonObj9);
        cindexObj4.setSchedule(schedule4);
        ListCindex2.add(cindexObj4);

        Course courseObj3 = new Course("CZ2003", "Computer Graphics and Visualisation", "Foundation mathematics for computer graphics; Visual rendering pipeline; Virtual reality Modelling Language (VRML) and Extensible 3D (X3D); Geometric shapes; Transformations and motions; Visual Appearance; Rendering","scse", 3, ListCindex2);

        courseList.add(courseObj3);

        //course4 (CZ2004, 10129)
        ArrayList<Cindex> ListCindex3 = new ArrayList<Cindex>();
        Date startTimeParsedLect3=null;
        try {
            startTimeParsedLect3 = timeformat.parse("0930");
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Date endTimeParsedLect3=null;
        try {
            endTimeParsedLect3 = timeformat.parse("1030");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Lesson lessonObj10 = new Lecture(startTimeParsedLect3, endTimeParsedLect3, "online", Main.DAY_OF_WEEK.FRIDAY);

        Date starttime7=null;
        try {
            starttime7 = timeformat.parse("1530");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Date endtime7=null;
        try {
            endtime7 = timeformat.parse("1630");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Lesson lessonObj11 = new Tutorial(starttime7,endtime7,"tr+9",
                    Main.DAY_OF_WEEK.THURSDAY);


        Date starttime8=null;
        try {
            starttime8 = timeformat.parse("1030");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Date endtime8=null;
        try {
            endtime8 = timeformat.parse("1230 ");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Lesson lessonObj12 = new Labs(starttime8, endtime8, "hwlab1", Main.DAY_OF_WEEK.THURSDAY, Main.ODD_EVEN.ODD);

        ArrayList<Lesson> schedule5 = new ArrayList<Lesson>();
        Cindex cindexObj5 = new Cindex("10140", 2);
        
        schedule5.add(lessonObj10);
        schedule5.add(lessonObj11);
        schedule5.add(lessonObj12);
        cindexObj5.setSchedule(schedule5);
        ListCindex3.add(cindexObj5);

        Course courseObj4 = new Course("CZ2004", "Human Computer Interaction", "Usability and application spaces; Guidelines and principles for text-based interactions; Hardware devices for interaction; Development processes and assessments; Software tools in user interface development; Interaction styles; Practical UI development concepts; Design Issues","scse", 3, ListCindex3);

        courseList.add(courseObj4);

        //course4 (CZ2004, 13811) - clash with CZ2003, 10128
        Date startTimeParsedLect4=null;
        try {
            startTimeParsedLect4 = timeformat.parse("0930");
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Date endTimeParsedLect4=null;
        try {
            endTimeParsedLect4 = timeformat.parse("1030");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Lesson lessonObj13 = new Lecture(startTimeParsedLect4, endTimeParsedLect4, "online", Main.DAY_OF_WEEK.FRIDAY);

        Date starttime9=null;
        try {
            starttime9 = timeformat.parse("1330");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Date endtime9=null;
        try {
            endtime9 = timeformat.parse("1430");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Lesson lessonObj14 = new Tutorial(starttime9,endtime9,"lhn-tr+13",
                    Main.DAY_OF_WEEK.WEDNESDAY);


        Date starttime10=null;
        try {
            starttime10 = timeformat.parse("0830");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Date endtime10=null;
        try {
            endtime10 = timeformat.parse("1030 ");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Lesson lessonObj15 = new Labs(starttime10, endtime10, "hwlab1", Main.DAY_OF_WEEK.WEDNESDAY, Main.ODD_EVEN.EVEN);

        ArrayList<Lesson> schedule6 = new ArrayList<Lesson>();
        Cindex cindexObj6 = new Cindex("13811", 2);
        
        schedule6.add(lessonObj13);
        schedule6.add(lessonObj14);
        schedule6.add(lessonObj15);
        cindexObj6.setSchedule(schedule6);
        ListCindex3.add(cindexObj6);

        //Course courseObj6 = new Course("CZ2004", "Computer Graphics and Visualisation", "Foundation mathematics for computer graphics; Visual rendering pipeline; Virtual reality Modelling Language (VRML) and Extensible 3D (X3D); Geometric shapes; Transformations and motions; Visual Appearance; Rendering","scse", 3, ListCindex3);

        //courseList.add(courseObj6);
        
        databaseManager.SerializeCourseList(courseList);

    }

/**
 * removes specified student from the waitlist
 * @param username the username of the student
 */
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
/**
 * method that searches for the specified Cindex in the list of Cindex. If specified Cindex does not exist, method returns null
 * @param courseIndex String
 * @return the specified Cindex if it exists. null otherwise.
 */
	public Cindex searchCindex(String courseIndex) {
        for (int i = 0 ; i < listCindex.size();i++){
            if(listCindex.get(i).getIndexName().equals(courseIndex)){
                return listCindex.get(i);
            }
            
        }
		return null;
	}
/**
 * method that removes specified student from list of registered students or waitlist student when they drop the course and move a student from the waitlist into the list of registered students
 * @param username username of the student
 */
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
