import java.io.Serializable;
import java.util.ArrayList;

public class Cindex implements Serializable{
    private String index;
    private int Capacity;
    private ArrayList<Student> waitList;
    private ArrayList<Student> registeredStudents;
    private ArrayList<Lesson> schedule;

    public Cindex(String index, int Capacity, ArrayList<Student> waitList, ArrayList<Student> registeredStudents,
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
        this.waitList = new ArrayList<Student>();
        this.registeredStudents = new ArrayList<Student>();
        this.schedule = new ArrayList<Lesson>();
    }

    public String getIndex() {
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

    public ArrayList<Student> getWaitList() {
        return waitList;
    }

    public void setWaitList(ArrayList<Student> waitList) {
        this.waitList = waitList;
    }

    public ArrayList<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public void setRegisteredStudents(ArrayList<Student> registeredStudents) {
        this.registeredStudents = registeredStudents;
    }

    public void addRegisteredStudent(Student student){
        this.registeredStudents.add(student);
    }
    public void addWaitlistStudent(Student student){
        this.registeredStudents.add(student);
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

    public boolean pushWaitlist(){
        //check if num of reg stud is less than cpacity
        if(registeredStudents.size()<Capacity){
            if (waitList.size() != 0){
                addRegisteredStudent(waitList.get(0));
                waitList.remove(0);
                SendMail sendMail = new SendMail();
                
            }
            
        }
    }
}
