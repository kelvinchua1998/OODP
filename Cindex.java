import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Cindex  implements Serializable{
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

    public int getTotalVacancy() {
        return Capacity;
    }

    public void setTotalVacancy(int Capacity) {
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

    public ArrayList<Lesson> getSchedule() {
        return this.schedule;
    }

    public void setSchedule(ArrayList<Lesson> schedule) {
        this.schedule = schedule;
    }

    public int getCurrentVacancy() {
        return this.Capacity - registeredStudents.size();

    }

    public static int getVacancyCindex(String coursecode, String index) {
        Cindex singleIndex = searchCindex(coursecode, index);

        if (singleIndex != null) {
            return singleIndex.getCurrentVacancy();
        } else {
            return -1;
        }
    }

    public static ArrayList<Student> getStudentList(String coursecode, String index) {
        Cindex singleIndex = searchCindex(coursecode, index);

        return singleIndex.getRegisteredStudents();
    }

    private static Cindex searchCindex(String coursecode, String Cindex) {
        ArrayList<Course> courseList = new ArrayList<Course>();

        DatabaseManager databaseManager = new DatabaseManager();
        courseList = databaseManager.DeserializeCourseList();

        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseCode().equals(coursecode)) {
                Course courseObj = courseList.get(i);
                ArrayList<Cindex> CindexObj = courseObj.getListCindex();
                for (int j = 0; j < CindexObj.size(); j++) {
                    if (CindexObj.get(j).equals(Cindex)) {
                        return CindexObj.get(j);
                    }
                }

            }
        }

        return null;

    }


}
