import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Labs extends Lesson implements Serializable{
    
    private Time startTime;
    private Time endTime;
    private String venue;
    private String dayOfWeek;
    private String oddOrEven;

    public Labs(String course, String cindex, Time startTime, Time endTime, String venue, String dayOfWeek,
    String oddOrEven) {
    super(course, cindex);
    this.startTime = startTime;
    this.endTime = endTime;
    this.venue = venue;
    this.dayOfWeek = dayOfWeek;
    this.oddOrEven = oddOrEven;
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

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getOddOrEven() {
        return oddOrEven;
    }

    public void setOddOrEven(String oddOrEven) {
        this.oddOrEven = oddOrEven;
    }

    private static void SerializeLabsList(List<Labs> labsList) {
        FileOutputStream fileOut ;
        ObjectOutputStream out;
      try {
         fileOut = new FileOutputStream("labs.ser");
        out = new ObjectOutputStream(fileOut);
         out.writeObject(labsList);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved\n");
      } catch (IOException i) {
         i.printStackTrace();
      }
   }

   private static List<Labs> DeserializeLabsList() { 
       try{
         List<Labs> labsList2;
         FileInputStream fileIn = new FileInputStream("labs.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         labsList2 = (List<Labs>) in.readObject();
         in.close();
         fileIn.close();
         return labsList2;
      } catch (IOException i) {
         i.printStackTrace();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
      return null;
   }

   public static void main(String[] args) {
       

    Labs LabsObj1 = new Labs("a", "1", Time.valueOf("11:00:00"), Time.valueOf("11:00:00"), "lt1", "monday","odd");
    Labs LabsObj2 = new Labs("a", "1", Time.valueOf("11:00:00"), Time.valueOf("11:00:00"), "lt1", "monday","odd");
    Labs LabsObj3 = new Labs("a", "1", Time.valueOf("11:00:00"), Time.valueOf("11:00:00"), "lt1", "monday","odd");
    Labs LabsObj4 = new Labs("a", "1", Time.valueOf("11:00:00"), Time.valueOf("11:00:00"), "lt1", "monday","odd");
    Labs LabsObj5 = new Labs("a", "1", Time.valueOf("11:00:00"), Time.valueOf("11:00:00"), "lt1", "monday","odd");

    List<Labs> LabsList = new ArrayList<Labs>();
    LabsList.add(LabsObj1);
    LabsList.add(LabsObj2);
    LabsList.add(LabsObj3);
    LabsList.add(LabsObj4);
    LabsList.add(LabsObj5);

    
   SerializeLabsList(LabsList);
   LabsList = null;
   LabsList = DeserializeLabsList();

   ListIterator<Labs> ListItr = LabsList.listIterator();
   while (ListItr.hasNext()) {
      System.out.println("index:" + ListItr.nextIndex() + " value:" + ListItr.next().getStartTime().toString());
   }

   }

   
}
