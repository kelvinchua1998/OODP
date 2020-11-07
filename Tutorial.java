import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Tutorial extends Lesson implements Serializable{
    private Time startTime;
    private Time endTime;
    private String venue;
    private String dayOfWeek;

    public Tutorial(String course, String cindex, Time startTime, Time endTime, String venue, String dayOfWeek) {
        super(course, cindex);
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
        this.dayOfWeek = dayOfWeek;
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

   public static void main(String[] args) {
       

    Tutorial tutorialObj1 = new Tutorial("a", "1", Time.valueOf("11:00:00"),Time.valueOf("11:00:00"), "Lt1", "Monday");
    Tutorial tutorialObj2 = new Tutorial("b", "1", Time.valueOf("11:00:00"),Time.valueOf("11:00:00"), "Lt1", "Monday");
    Tutorial tutorialObj3 = new Tutorial("c", "1", Time.valueOf("11:00:00"),Time.valueOf("11:00:00"), "Lt1", "Monday");
    Tutorial tutorialObj4 = new Tutorial("d", "1", Time.valueOf("11:00:00"),Time.valueOf("11:00:00"), "Lt1", "Monday");
    Tutorial tutorialObj5 = new Tutorial("e", "1", Time.valueOf("11:00:00"),Time.valueOf("11:00:00"), "Lt1", "Monday");

    ArrayList<Tutorial> tutorialList = new ArrayList<Tutorial>();
    tutorialList.add(tutorialObj1);
    tutorialList.add(tutorialObj2);
    tutorialList.add(tutorialObj3);
    tutorialList.add(tutorialObj4);
    tutorialList.add(tutorialObj5);

    
   SerializeTutorialList(tutorialList);
   tutorialList = null;
   tutorialList = DeserializeTutorialList();

   ListIterator<Tutorial> ListItr = tutorialList.listIterator();
   while (ListItr.hasNext()) {
      System.out.println("index:" + ListItr.nextIndex() + " value:" + ListItr.next().getStartTime().toString());
   }

   }


    

}
