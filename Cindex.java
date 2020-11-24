import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class is to identify the various indexes for each course. It implements serializable so that it can be serialized into the database
 */
public class Cindex implements Serializable{
    private static final long serialVersionUID = 1L;
    
    /**
     * index of the course
     */
    private String index;
    /**
     * capacity of the index
     */
    private int Capacity;
    /**
     * list of students in the waitlist for this index
     */
    private ArrayList<String> waitList;
    /**
     * list of students registered for this index
     */
    private ArrayList<String> registeredStudents;
    /**
     * list of the different lessons in this index
     */
    private ArrayList<Lesson> schedule;

/**
 * default constructor to create Cindex class
 * @param index
 * @param Capacity
 * @param waitList
 * @param registeredStudents
 * @param schedule
 */
    public Cindex(String index, int Capacity, ArrayList<String> waitList, ArrayList<String> registeredStudents,
            ArrayList<Lesson> schedule) {
        this.index = index;
        this.Capacity = Capacity;
        this.waitList = waitList;
        this.registeredStudents = registeredStudents;
        this.schedule = schedule;
    }

/**
 * constructor that creates an empty list of waitList, registeredStudents and schedule
 */
    public Cindex(String index, int Capacity) {
        this.index = index;
        this.Capacity = Capacity;
        this.waitList = new ArrayList<String>();
        this.registeredStudents = new ArrayList<String>();
        this.schedule = new ArrayList<Lesson>();
    }
/**
 * gets the index code 
 * @return String index
 */
    public String getIndexName() {
        return index;
    }
/**
 * sets the index code
 * @param index String
 */
    public void setIndex(String index) {
        this.index = index;
    }
/**
 * gets the capacity of the Cindex
 * @return int Capacity
 */
    public int getCapacity() {
        return Capacity;
    }
/**
 * sets the capacity of the Cindex
 * @param Capacity int
 */
    public void setCapacity(int Capacity) {
        this.Capacity = Capacity;
    }
/**
 * gets the list of students on waitlist for the Cindex
 * @return ArrayList<String> waitList
 */
    public ArrayList<String> getWaitList() {
        return waitList;
    }
/**
 * sets the waitlist of the Cindex
 * @param waitList ArrayList<String>
 */
    public void setWaitList(ArrayList<String> waitList) {
        this.waitList = waitList;
    }
/**
 * gets the list of registered students of the Cindex
 * @return ArrayList<String> registeredStudents
 */
    public ArrayList<String> getRegisteredStudents() {
        return registeredStudents;
    }
/**
 * sets the list of registered students of the Cindex
 * @param registeredStudents ArrayList<String>
 */
    public void setRegisteredStudents(ArrayList<String> registeredStudents) {
        this.registeredStudents = registeredStudents;
    }
/**
 * adds registered students to the list for registered students of the Cindex
 * @param studentUsername String
 */
    public void addRegisteredStudent(String studentUsername){
        this.registeredStudents.add(studentUsername);
    }
/**
 * adds students to waitlist if Cindex capacity is full
 * @param studentUsername String
 */
    public void addWaitlistStudent(String studentUsername){
        this.waitList.add(studentUsername);
    }
/**
 * gets the schedule of the Cindex
 * @return ArrayList<Lesson> schedule
 */
    public ArrayList<Lesson> getSchedule() {
        return this.schedule;
    }
/**
 * sets the schedule of the Cindex
 * @param schedule ArrayList<Lesson>
 */
    public void setSchedule(ArrayList<Lesson> schedule) {
        this.schedule = schedule;
    }
/**
 * gets the current vacancy of the Cindex
 * @return int this.Capacity - registeredStudents.size()
 */
    public int getCurrentVacancy() {
        return this.Capacity - registeredStudents.size();
    }

/**
 * get the index of the specified student in the list of registered students for the Cindex. If the student is not found -1 is returned
 * @param username String
 * @return int 
 */
    public int getIndexofStudent(String username){
        for(int i=0; i<this.registeredStudents.size(); i++){
            if(this.registeredStudents.get(i).equals(username)){
                return i;
            }
        }
        return -1;
    }

}
