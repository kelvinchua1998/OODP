import java.io.Serializable;
import java.util.Date;

/**
 * sub class of Lesson and implements Serializable so that is can be serialized into the database
 * @author kelvin melvin
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
 * @param startTime start time of lab
 * @param endTime end time of lab
 * @param venue venue of lab
 * @param dayOfWeek day of week lab is on
 * @param oddOrEven lab is on odd week or even week or both
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
 * @return startTime of lab
 */
    public java.util.Date getStartTime() {
        return startTime;
    }
/**
 * gets the end time of labs
 * @return endTime of lab
 */
    public java.util.Date getEndTime() {
        return endTime;
    }
/**
 * gets the lesson type which is labs for this class
 * @return lesson_type as lab
 */
    @Override
    public Main.LESSON_TYPE getLessonType() {
        return lesson_type;
    }
/**
 * gets the day of the week the lab is on
 * @return day of the week lab is on
 */
    @Override
    public Main.DAY_OF_WEEK getDayoftheWeek() {
        return dayOfWeek;

    }
/**
 * gets whether the labs occur on odd or even weeks or both.
 * @return whether lab is on odd week or even week or both
 */
    @Override
    public Main.ODD_EVEN getOddorEven() {
        return oddOrEven;
    }
/**
 * gets the venue of the lab
 * @return venue of the lab
 */
    public String getVenue() {
        return venue;
    }
/**
 * sets the start time of the lab
 * @param startTime start time of lab
 */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
/**
 * sets the end time of the lab
 * @param endTime end time of lab
 */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
/**
 * sets the venue of the lab
 * @param venue venue of lab
 */
    public void setVenue(String venue) {
        this.venue = venue;
    }
/**
 * sets which day of the week the lab is on
 * @param dayOfWeek day of the week lab is on
 */
    public void setDayOfWeek(Main.DAY_OF_WEEK dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
/**
 * sets whether labs occur on odd or even weeks or both
 * @param oddOrEven which week lab is on
 */
    public void setOddOrEven(Main.ODD_EVEN oddOrEven) {
        this.oddOrEven = oddOrEven;
    }

   
}
