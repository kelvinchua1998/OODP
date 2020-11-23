import java.io.Serializable;
import java.util.Date;


public class Tutorial extends Lesson implements Serializable{
    private Date startTime;
    private Date endTime;
    private String venue;
    private Main.DAY_OF_WEEK dayOfWeek;
    private Main.ODD_EVEN oddOrEven;
    private Main.LESSON_TYPE lesson_type;

    public Tutorial(java.util.Date startTimeParsed, java.util.Date endTimeParsed, String venue, Main.DAY_OF_WEEK dayOfWeek) {
        this.startTime = startTimeParsed;
        this.endTime = endTimeParsed;
        this.venue = venue;
        this.dayOfWeek = dayOfWeek;
        this.oddOrEven = Main.ODD_EVEN.OOD_AND_EVEN;
        this.lesson_type = Main.LESSON_TYPE.TUTORIAL;
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

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setDayOfWeek(Main.DAY_OF_WEEK dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    

}
