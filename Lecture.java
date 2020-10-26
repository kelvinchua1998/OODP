import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Time;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Lecture extends Lesson implements Serializable{
    
    private Time startTime;
    private Time endTime;
    private String venue;
    private String dayOfweek;

    public Lecture(String course, String cindex, Time startTime, Time endTime, String venue, String dayOfweek) {
        super(course,cindex);
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
        this.dayOfweek = dayOfweek;
    }
    
    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDayOfweek() {
        return dayOfweek;
    }

    public void setDayOfweek(String dayOfweek) {
        this.dayOfweek = dayOfweek;
    }
    
    
    private static void SerializeLectureList(List<Lecture> lectureList) {
        FileOutputStream fileOut ;
        ObjectOutputStream out;
      try {
        fileOut = new FileOutputStream("lecture.ser");
        out = new ObjectOutputStream(fileOut);
        out.writeObject(lectureList);
        out.close();
        fileOut.close();
        System.out.printf("Serialized data is saved\n");
      } catch (IOException i) {
        i.printStackTrace();
      }
   }

   private static List<Lecture> DeserializeLectureList() {
       try{
        List<Lecture> lectureList2;
        FileInputStream fileIn = new FileInputStream("lecture.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        lectureList2 = (List<Lecture>) in.readObject();
        in.close();
        fileIn.close();
         return lectureList2;
      } catch (IOException i) {
        i.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
      return null;
   }

   public static void main(String[] args) {
       //serialise mock data


       Lecture lectureObj1 = new Lecture("a","1", Time.valueOf("11:00:00"), Time.valueOf("11:00:00"), "Lt1","tuesday");
       Lecture lectureObj2 = new Lecture("b","1", Time.valueOf("11:00:00"), Time.valueOf("11:00:00"), "Lt1","tuesday");
       Lecture lectureObj3 = new Lecture("c","1", Time.valueOf("11:00:00"), Time.valueOf("11:00:00"), "Lt1","tuesday");
       Lecture lectureObj4 = new Lecture("d","1", Time.valueOf("11:00:00"), Time.valueOf("11:00:00"), "Lt1","tuesday");
       Lecture lectureObj5 = new Lecture("e","1", Time.valueOf("11:00:00"), Time.valueOf("11:00:00"), "Lt1","tuesday");
 
       List<Lecture> lecturetList = new ArrayList<Lecture>();
       lecturetList.add(lectureObj1);
       lecturetList.add(lectureObj2);
       lecturetList.add(lectureObj3);
       lecturetList.add(lectureObj4);
       lecturetList.add(lectureObj5);

       
      SerializeLectureList(lecturetList);
      lecturetList = null;
      lecturetList = DeserializeLectureList();

      ListIterator<Lecture> ListItr = lecturetList.listIterator();
      while (ListItr.hasNext()) {
         System.out.println("index:" + ListItr.nextIndex() + " value:" + ListItr.next().getStartTime().toString());
      }

   }

   
}
