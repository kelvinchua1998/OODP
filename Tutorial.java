import java.io.Serializable;
import java.util.Date;

/**
 * subclass of Lesson and implements Serializable so that it can be serialized into the database
 */
public class Tutorial extends Lesson implements Serializable{
    private Date startTime;
    private Date endTime;
    private String venue;
    private Main.DAY_OF_WEEK dayOfWeek;
    private Main.ODD_EVEN oddOrEven;
    private Main.LESSON_TYPE lesson_type;
/**
 * default constructor for creating Tutorial class
 * @param startTimeParsed
 * @param endTimeParsed
 * @param venue
 * @param dayOfWeek
 */
    public Tutorial(java.util.Date startTimeParsed, java.util.Date endTimeParsed, String venue, Main.DAY_OF_WEEK dayOfWeek) {
        this.startTime = startTimeParsed;
        this.endTime = endTimeParsed;
        this.venue = venue;
        this.dayOfWeek = dayOfWeek;
        this.oddOrEven = Main.ODD_EVEN.OOD_AND_EVEN;
        this.lesson_type = Main.LESSON_TYPE.TUTORIAL;
    }

/**
 * gets the start time of the tutorial
 * @return Date startTime
 */
	public Date getStartTime() {
        return startTime;
    }
/**
 * sets the start time of the tutorial
 */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
/**
 * gets the end time of the tutorial
 * @return Date endTime
 */
    public Date getEndTime() {
        return endTime;
    }
/**
 * gets the lesson type which is tutorial for this class
 * @return Main.LESSON_TYPE lesson_type
 */
    @Override
    public Main.LESSON_TYPE getLessonType() {
        return lesson_type;
    }
/**
 * gets the day of the week that the tutorial is on
 * @return Main.DAY_OF_WEEK dayOfWeek
 */
    @Override
    public Main.DAY_OF_WEEK getDayoftheWeek() {
        return dayOfWeek;
    }
/**
 * gets whether lesson occurs on odd or even week. For tutorial, it is both.
 * @return Main.ODD_EVEN oddOrEven
 */
    @Override
    public Main.ODD_EVEN getOddorEven() {
        return oddOrEven;
    }
/**
 * sets the end time of the tutorial
 * @param endTime
 */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
/**
 * gets the venue of the tutorial
 * @return String venue
 */
    public String getVenue() {
        return venue;
    }
/**
 * sets the venue of the tutorial
 * @param venue
 */
    public void setVenue(String venue) {
        this.venue = venue;
    }
/**
 * sets which day of the week the tutorial is on
 * @param dayOfWeek
 */
    public void setDayOfWeek(Main.DAY_OF_WEEK dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    

}
