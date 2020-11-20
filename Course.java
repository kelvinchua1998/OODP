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

    public Course(String cc, String cn, String d, String school, int AU, ArrayList<Student> registeredStudents,
            ArrayList<Cindex> ListCindex) {
        this.CourseCode = cc;
        this.CourseName = cn;
        this.CourseDescription = d;
        this.AU = AU;
        this.School = school;
    }

    public int getIndexOfCindex(String cindexName) {
        for (int i = 0; i < this.listCindex.size(); i++) {
            if (this.listCindex.get(i).getIndex().equals(cindexName)) {
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
        listCindex = Cindex;
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

                singleIndex.addRegisteredStudent(singleIndex.getWaitList().get(0));
                singleIndex.getWaitList().remove(0);

                // create a new studentCourse
                StudentCourse newlyregisteredCourse = new StudentCourse(CourseCode, CourseName, CourseDescription,
                        singleIndex);
                Student stud = singleIndex.getRegisteredStudents().get(singleIndex.getCapacity() - 1);
                stud.addCourse(newlyregisteredCourse);

                databaseManager.updateDatabase(stud);

                SendMail sendMail = new SendMail();

                String EmailContent = "Dear Sir/Mdm,\n This a confirmation email that your course " + CourseCode + " "
                        + CourseName + "for index " + singleIndex.getIndex()
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
        ArrayList<Student> registeredStudents = new ArrayList<Student>();
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
        Lesson lessonObj1 = new Lecture(startTimeParsedLect, endTimeParsedLect, "lt1", "monday");
        ArrayList<Lesson> schedule = new ArrayList<Lesson>();
        Cindex cindexObj = new Cindex("1", 10);
        schedule.add(lessonObj1);
        cindexObj.setSchedule(schedule);
        ListCindex.add(cindexObj);

        Lesson lessonObj2 = new Lecture(startTimeParsedLect, endTimeParsedLect, "lt2", "tuesday");
        ArrayList<Lesson> schedule2 = new ArrayList<Lesson>();
        Cindex cindexObj2 = new Cindex("2", 1);
        schedule2.add(lessonObj2);
        cindexObj2.setSchedule(schedule2);
        ListCindex.add(cindexObj2);

        Course courseObj = new Course("c1", "course1", "course description","scse", 3, registeredStudents, ListCindex);

        Course courseObj2 = new Course("c2", "course2", "course description","scse", 3, registeredStudents, ListCindex);

        courseList.add(courseObj);
        courseList.add(courseObj2);
        databaseManager.SerializeCourseList(courseList);

    }


	
}
