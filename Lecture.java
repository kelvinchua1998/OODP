import java.io.Serializable;
import java.util.Date;

public class Lecture extends Lesson implements Serializable{
    
    private Date startTime;
    private Date endTime;
    private String venue;
    private String dayOfweek;

    public Lecture(Date startTime, Date endTime, String venue, String dayOfweek) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
        this.dayOfweek = dayOfweek;
    }
    
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
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
    
    

//    public static void main(String[] args) {
//        //serialise mock data


//        Lecture lectureObj1 = new Lecture("a","1", Time.valueOf("11:00:00"), Time.valueOf("11:00:00"), "Lt1","tuesday");
//        Lecture lectureObj2 = new Lecture("b","1", Time.valueOf("11:00:00"), Time.valueOf("11:00:00"), "Lt1","tuesday");
//        Lecture lectureObj3 = new Lecture("c","1", Time.valueOf("11:00:00"), Time.valueOf("11:00:00"), "Lt1","tuesday");
//        Lecture lectureObj4 = new Lecture("d","1", Time.valueOf("11:00:00"), Time.valueOf("11:00:00"), "Lt1","tuesday");
//        Lecture lectureObj5 = new Lecture("e","1", Time.valueOf("11:00:00"), Time.valueOf("11:00:00"), "Lt1","tuesday");
 
//        ArrayList<Lecture> lecturetList = new ArrayList<Lecture>();
//        lecturetList.add(lectureObj1);
//        lecturetList.add(lectureObj2);
//        lecturetList.add(lectureObj3);
//        lecturetList.add(lectureObj4);
//        lecturetList.add(lectureObj5);

       
//       SerializeLectureList(lecturetList);
//       lecturetList = null;
//       lecturetList = DeserializeLectureList();

//       ListIterator<Lecture> ListItr = lecturetList.listIterator();
//       while (ListItr.hasNext()) {
//          System.out.println("index:" + ListItr.nextIndex() + " value:" + ListItr.next().getStartTime().toString());
//       }

//    }

   
}
