

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
//import java.sql.Time;
//import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Cindex extends Course implements Serializable{
    private String index;
    private int totalVacancy;
    private ArrayList<Student> waitList;
    private ArrayList<Student> registeredStudents;
    private ArrayList<Lesson> schedule;


    public Cindex(String cc, String cn, String d, String index, int totalVacancy, ArrayList<Student> waitList,
			ArrayList<Student> registeredStudents, ArrayList<Lesson> schedule) {
		super(cc, cn, d);
		this.index = index;
		this.totalVacancy = totalVacancy;
		this.waitList = waitList;
		this.registeredStudents = registeredStudents;
		this.schedule = schedule;
	}

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public int getTotalVacancy() {
        return totalVacancy;
    }

    public void setTotalVacancy(int totalVacancy) {
        this.totalVacancy = totalVacancy;
    }

    public String[] getWaitList() {
        return waitList;
    }

    public void setWaitList(String[] waitList) {
        this.waitList = waitList;
    }

    public String[] getRegisteredStudents() {
        return registeredStudents;
    }

    public void setRegisteredStudents(String[] registeredStudents) {
        this.registeredStudents = registeredStudents;
    }

    public Lesson[] getSchedule() {
        return Schedule;
    }

    public void setSchedule(Lesson[] schedule) {
        Schedule = schedule;
    }

    private static void SerializeCindexList(List<Cindex> cindexList){
        FileOutputStream fileOut ;
        ObjectOutputStream out;
        try {
            fileOut = new FileOutputStream("cindex.ser");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(cindexList);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved\n");
          } catch (IOException i) {
            i.printStackTrace();
          }
    
    }
    
    private static List<Cindex> DeserializeCindexList() {
        try{
         List<Cindex> cindexList2;
         FileInputStream fileIn = new FileInputStream("cindex.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         cindexList2 = (List<Cindex>) in.readObject();
         in.close();
         fileIn.close();
          return cindexList2;
       } catch (IOException i) {
         i.printStackTrace();
       } catch (ClassNotFoundException e) {
         e.printStackTrace();
       }
       return null;
    }
    
    public static void main(String[] args) {
        //mock data from ce1105
        ArrayList<Student> waitingList = new ArrayList<>();
        ArrayList<Student> registeredStudents = new ArrayList<>();
        ArrayList<Lesson> schedule = new ArrayList<>();
        
        Cindex cindex1 = new Cindex("1", 10,waitingList , registeredStudents, schedule);
    
        List<Cindex> cindexList = new ArrayList<Cindex>();
        cindexList.add(cindex1);
    
        SerializeCindexList(cindexList);
        cindexList = null;
        cindexList = DeserializeCindexList();
    
        ListIterator<Cindex> ListItr = cindexList.listIterator();
        while (ListItr.hasNext()) {
           System.out.println("index:" + ListItr.nextIndex() + " value:" + ListItr.next().getTotalVacancy());
        }
    
    }
    private static Cindex searchCindex(String coursecode, String Cindex) {
        List<Cindex> cindexList = new ArrayList<Cindex>();
        cindexList = null;
        cindexList = DeserializeCindexList();

        for (int i = 0; i < cindexList.size(); i++) {
            if (cindexList.get(i).getCourseCode().equals(coursecode) && cindexList.get(i).getIndex().equals(Cindex))
                return cindexList.get(i);
        }

        return null;

    }

    public int getCurrentVacancy() {
        return this.totalVacancy - registeredStudents.size();

    }

    public static int getVacancyCindex(String coursecode, String index) {
        Cindex singleIndex = searchCindex(coursecode, index);

        if (singleIndex != null){
            return singleIndex.getCurrentVacancy();
        }else{
            return -1;
        }
        
        
    }

   
}

