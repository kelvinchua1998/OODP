import java.io.Serializable;
import java.util.Date;

public class Lecture extends Lesson implements Serializable{
    
    private Date startTime;
    private Date endTime;
    private String venue;
    private Main.DAY_OF_WEEK dayOfweek;
    private Main.ODD_EVEN oddOrEven;
    private Main.LESSON_TYPE lesson_type;


    public Lecture(Date startTime, Date endTime, String venue, Main.DAY_OF_WEEK dayOfweek) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
        this.dayOfweek = dayOfweek;
        this.oddOrEven = Main.ODD_EVEN.OOD_AND_EVEN;
        this.lesson_type = Main.LESSON_TYPE.LECTURE;
    }
    
    public Date getStartTime() {
        return startTime;
    }



    public Date getEndTime() {
        return endTime;
    }

    @Override
    public Main.LESSON_TYPE getLessonType() {
        return lesson_type;
    }

    @Override
    public Main.DAY_OF_WEEK getDayoftheWeek() {
        return dayOfweek;
    }

    @Override
    public Main.ODD_EVEN getOddorEven() {
        return oddOrEven;
    }

    public String getVenue() {
        return venue;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setDayOfweek(Main.DAY_OF_WEEK dayOfweek) {
        this.dayOfweek = dayOfweek;
    }

   
}
