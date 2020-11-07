
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.sql.Time;
//import java.time.MonthDay;
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

    // public Cindex addNewIndex(String index, int capacity) throws ParseException {
    //     Cindex CindexObj = new Cindex(index, capacity);

    //     System.out.println("Add new lesson: ");
    //     System.out.println("0.Stop adding lesson ");
    //     System.out.println("1.Add new Lecture ");
    //     System.out.println("2.Add new Tutorial ");
    //     System.out.println("3.Add new Lab ");

    //     Scanner sc = new Scanner(System.in);

    //     DateFormat timeformat = new SimpleDateFormat("HHmm");
    //     int choice = -1;

    //     while (choice != 0) {
    //         choice = sc.nextInt();
    //         switch (choice) {
    //             case 0:
    //                 break;
    //             case 1:
    //                 System.out.println("Start Time: ");
    //                 String startTimeLect = sc.next();
    //                 Date startTimeParsedLect = timeformat.parse(startTimeLect);

    //                 System.out.println("End Time: ");
    //                 String endTimeLect = sc.next();
    //                 Date endTimeParsedLect = timeformat.parse(endTimeLect);

    //                 System.out.println("Venue: ");
    //                 String venueLect = sc.next();

    //                 System.out.println("Day Of Week: ");
    //                 String dayOfweekLect = sc.next();

    //                 Lecture lecture = new Lecture(startTimeParsedLect, endTimeParsedLect, venueLect, dayOfweekLect);

    //                 CindexObj.schedule.add(lecture);
    //                 break;
    //             case 2:
    //                 System.out.println("Start Time: ");
    //                 String startTimeTut = sc.next();
    //                 Date startTimeParsedTut = timeformat.parse(startTimeTut);

    //                 System.out.println("End Time: ");
    //                 String endTimeTut = sc.next();
    //                 Date endTimeParsedTut = timeformat.parse(endTimeTut);

    //                 System.out.println("Venue: ");
    //                 String venueTut = sc.next();

    //                 System.out.println("Day Of Week: ");
    //                 String dayOfweekTut = sc.next();

    //                 Tutorial tutorial = new Tutorial(startTimeParsedTut, endTimeParsedTut, venueTut, dayOfweekTut);

    //                 CindexObj.schedule.add(tutorial);
    //                 break;
    //             case 3:
    //                 System.out.println("Start Time: ");
    //                 String startTimeLab = sc.next();
    //                 Date startTimeParsedLab = timeformat.parse(startTimeLab);

    //                 System.out.println("End Time: ");
    //                 String endTimeLab = sc.next();
    //                 Date endTimeParsedLab = timeformat.parse(endTimeLab);

    //                 System.out.println("Venue: ");
    //                 String venueLab = sc.next();

    //                 System.out.println("Day Of Week: ");
    //                 String dayOfweekLab = sc.next();

    //                 System.out.println("Odd or Even: ");
    //                 String oddOrEvenLab = sc.next();

    //                 Labs lab = new Labs(startTimeParsedLab, endTimeParsedLab, venueLab, dayOfweekLab, oddOrEvenLab);

    //                 CindexObj.schedule.add(lab);
    //                 break;
    //         }
    //     }

    //     return CindexObj;
    // }

    public static void main(String[] args) {
        // mock data from ce1105
        ArrayList<Student> waitingList = new ArrayList<>();
        ArrayList<Student> registeredStudents = new ArrayList<>();
        ArrayList<Lesson> schedule = new ArrayList<>();

        Cindex cindex1 = new Cindex("1", 10, waitingList, registeredStudents, schedule);

        List<Cindex> cindexList = new ArrayList<Cindex>();
        cindexList.add(cindex1);

        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.SerializeCindexList(cindexList);
        cindexList = null;
        cindexList = databaseManager.DeserializeCindexList();

        ListIterator<Cindex> ListItr = cindexList.listIterator();
        while (ListItr.hasNext()) {
            System.out.println("index:" + ListItr.nextIndex() + " value:" + ListItr.next().getTotalVacancy());
        }

    }

}
