import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    private String CourseCode; // changed int to string cuz eg. CE2001
    private String CourseName;
    private String CourseDescription;
    private String School;
    private int AU;
    private ArrayList<Cindex> listCindex;

    public Course(String cc, String cn, String d,String school, int AU, ArrayList<Student> registeredStudents, ArrayList<Cindex> ListCindex) {
        this.CourseCode = cc;
        this.CourseName = cn;
        this.CourseDescription = d;
        this.AU = AU;
        this.School = school;
    }

    public int getIndexOfCindex(String cindexName){
        for(int i=0; i<this.listCindex.size(); i++){
            if(this.listCindex.get(i).getIndex().equals(cindexName)){
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
        for(int i =0 ;i <listCindex.size(); i++){
            for (int j=0; j < listCindex.get(i).getRegisteredStudents().size(); j++){
                if(listCindex.get(i).getRegisteredStudents().get(j).getUsername().equals(username)){
                    //removing student from reg
                    listCindex.get(i).getRegisteredStudents().remove(j);
                    
                    return true;
                }
            }
            
        }
        return false;
    }
    //push waitlist for every indexif any
    public void pushWaitlist(){
        DatabaseManager databaseManager = new DatabaseManager();
        //check if num of reg stud is less than cpacity
        for (int i =0 ; i < listCindex.size();i++){
            Cindex singleIndex = listCindex.get(i);
            if(singleIndex.getRegisteredStudents().size()<singleIndex.getCapacity() && singleIndex.getWaitList().size() != 0){
               
                singleIndex.addRegisteredStudent(singleIndex.getWaitList().get(0));
                singleIndex.getWaitList().remove(0);

                // create a new studentCourse
                StudentCourse newlyregisteredCourse = new StudentCourse(CourseCode,
                        CourseName, CourseDescription, singleIndex);
                Student stud  = singleIndex.getRegisteredStudents().get(singleIndex.getCapacity()-1);
                stud.addCourse(newlyregisteredCourse);
                
                databaseManager.updateDatabase(stud);
                
                SendMail sendMail = new SendMail();
                
                String EmailContent = "Dear Sir/Mdm,\n This a confirmation email that your course "+CourseCode+" "+CourseName +"for index "+singleIndex.getIndex()+" have been successfully added\n Thank You\n NTU STARS";
                sendMail.sendgmail("melvinchuaqwerty@gmail.com", "melvinchuaqwerty@gmail.com", "s9825202i",singleIndex.getRegisteredStudents().get(singleIndex.getCapacity()-1).getEmail(), "Course Added", EmailContent);
                }
        }
    }

    public static void main(String[] args) {
        ArrayList<Course> courseList = new ArrayList<Course>();
        DatabaseManager databaseManager = new DatabaseManager();

        databaseManager.SerializeCourseList(courseList);

    }


	
}
