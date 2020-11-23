import java.io.Serializable;
import java.util.Date;

public class Labs extends Lesson implements Serializable {

    private java.util.Date startTime;
    private java.util.Date endTime;
    private String venue;
    private Main.DAY_OF_WEEK dayOfWeek;
    private Main.ODD_EVEN oddOrEven;
    private Main.LESSON_TYPE lesson_type;

    public Labs(java.util.Date startTime, java.util.Date endTime, String venue, Main.DAY_OF_WEEK dayOfWeek, Main.ODD_EVEN oddOrEven) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
        this.dayOfWeek = dayOfWeek;
        this.oddOrEven = oddOrEven;
        this.lesson_type = Main.LESSON_TYPE.LAB;
    }

    public java.util.Date getStartTime() {
        return startTime;
    }

    public java.util.Date getEndTime() {
        return endTime;
    }

    @Override
    public Main.LESSON_TYPE getLessonType() {
        return lesson_type;
    }

    @Override
    public Main.DAY_OF_WEEK getDayoftheWeek() {
        return dayOfWeek;

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

    public void setDayOfWeek(Main.DAY_OF_WEEK dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setOddOrEven(Main.ODD_EVEN oddOrEven) {
        this.oddOrEven = oddOrEven;
    }

   
}
