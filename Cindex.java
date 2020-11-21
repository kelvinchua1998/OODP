import java.io.Serializable;
import java.util.ArrayList;

public class Cindex implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String index;
    private int Capacity;
    private ArrayList<String> waitList;
    private ArrayList<String> registeredStudents;
    private ArrayList<Lesson> schedule;

    public Cindex(String index, int Capacity, ArrayList<String> waitList, ArrayList<String> registeredStudents,
            ArrayList<Lesson> schedule) {
        this.index = index;
        this.Capacity = Capacity;
        this.waitList = waitList;
        this.registeredStudents = registeredStudents;
        this.schedule = schedule;
    }

    public Cindex(String index, int Capacity) {
        this.index = index;
        this.Capacity = Capacity;
        this.waitList = new ArrayList<String>();
        this.registeredStudents = new ArrayList<String>();
        this.schedule = new ArrayList<Lesson>();
    }

    public String getIndexName() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int Capacity) {
        this.Capacity = Capacity;
    }

    public ArrayList<String> getWaitList() {
        return waitList;
    }

    public void setWaitList(ArrayList<String> waitList) {
        this.waitList = waitList;
    }

    public ArrayList<String> getRegisteredStudents() {
        return registeredStudents;
    }

    public void setRegisteredStudents(ArrayList<String> registeredStudents) {
        this.registeredStudents = registeredStudents;
    }

    public void addRegisteredStudent(String studentUsername){
        this.registeredStudents.add(studentUsername);
    }
    public void addWaitlistStudent(String studentUsername){
        this.waitList.add(studentUsername);
    }

    public ArrayList<Lesson> getSchedule() {
        return this.schedule;
    }

    public void setSchedule(ArrayList<Lesson> schedule) {
        this.schedule = schedule;
    }

    public int getCurrentVacancy() {
        return this.Capacity - registeredStudents.size();
    }

    public int getIndexofStudent(String username){
        for(int i=0; i<this.registeredStudents.size(); i++){
            if(this.registeredStudents.get(i).equals(username)){
                return i;
            }
        }
        return -1;
    }

}
