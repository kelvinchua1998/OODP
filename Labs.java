import java.io.Serializable;
import java.util.Date;

/**
 * sub class of Lesson and implements Serializable so that is can be serialized into the database
 */
public class Labs extends Lesson implements Serializable {

    /**
     * start time of the lab
     */
    private java.util.Date startTime;
    /**
     * end time of the lab
     */
    private java.util.Date endTime;
    /**
     * venue of the lab
     */
    private String venue;
    /**
     * stores which day of the week the lab is on
     */
    private Main.DAY_OF_WEEK dayOfWeek;
    /**
     * states whether the lab occurs on odd or even weeks
     */
    private Main.ODD_EVEN oddOrEven;
    /**
     * states the lesson type which is lab for this class
     */
    private Main.LESSON_TYPE lesson_type;

/**
 * default constructor for creating Labs class
 * @param startTime
 * @param endTime
 * @param venue
 * @param dayOfWeek
 * @param oddOrEven
 */
    public Labs(java.util.Date startTime, java.util.Date endTime, String venue, Main.DAY_OF_WEEK dayOfWeek, Main.ODD_EVEN oddOrEven) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
        this.dayOfWeek = dayOfWeek;
        this.oddOrEven = oddOrEven;
        this.lesson_type = Main.LESSON_TYPE.LAB;
    }
/**
 * gets the start time of labs
 * @return java.util.Date startTime
 */
    public java.util.Date getStartTime() {
        return startTime;
    }
/**
 * gets the end time of labs
 * @return java.util.Date endTime
 */
    public java.util.Date getEndTime() {
        return endTime;
    }
/**
 * gets the lesson type which is labs for this class
 * @return Main.LESSON_TYPE lesson_type
 */
    @Override
    public Main.LESSON_TYPE getLessonType() {
        return lesson_type;
    }
/**
 * gets the day of the week the lab is on
 * @return Main.DAY_OF_WEEK dayOfWeek
 */
    @Override
    public Main.DAY_OF_WEEK getDayoftheWeek() {
        return dayOfWeek;

    }
/**
 * gets whether the labs occur on odd or even weeks or both.
 * @return Main.ODD_EVEN oddOrEven
 */
    @Override
    public Main.ODD_EVEN getOddorEven() {
        return oddOrEven;
    }
/**
 * gets the venue of the lab
 * @return String venue
 */
    public String getVenue() {
        return venue;
    }
/**
 * sets the start time of the lab
 * @param startTime
 */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
/**
 * sets the end time of the lab
 * @param endTime
 */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
/**
 * sets the venue of the lab
 * @param venue
 */
    public void setVenue(String venue) {
        this.venue = venue;
    }
/**
 * sets which day of the week the lab is on
 * @param dayOfWeek
 */
    public void setDayOfWeek(Main.DAY_OF_WEEK dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
/**
 * sets whether labs occur on odd or even weeks or both
 * @param oddOrEven
 */
    public void setOddOrEven(Main.ODD_EVEN oddOrEven) {
        this.oddOrEven = oddOrEven;
    }

   
}
