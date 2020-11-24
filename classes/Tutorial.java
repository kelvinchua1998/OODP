package classes;
import java.io.Serializable;
import java.util.Date;

/**
 * subclass of Lesson and implements Serializable so that it can be serialized into the database
 * @author kelvin melvin
 */
public class Tutorial extends Lesson implements Serializable{
    /**
     * start time of the tutorial
     */
    private Date startTime;
    /**
     * end time of the tutorial
     */
    private Date endTime;
    /**
     * venue of the tutorial
     */
    private String venue;
    /**
     * stores which day of the week the tutorial is on
     */
    private Main.DAY_OF_WEEK dayOfWeek;
    /**
     * states whether tutorial occurs on odd or even weeks
     */
    private Main.ODD_EVEN oddOrEven;
    /**
     * states the lesson type which is tutorial for this class
     */
    private Main.LESSON_TYPE lesson_type;
/**
 * default constructor for creating Tutorial class
 * @param startTimeParsed start time of tutorial 
 * @param endTimeParsed end time of tutorial
 * @param venue venue of tutorial
 * @param dayOfWeek day of week tutorial is on
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
 * @return startTime of tutorial
 */
	public Date getStartTime() {
        return startTime;
    }
/**
 * sets the start time of the tutorial
 * @param startTime start time of tutorial
 */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
/**
 * gets the end time of the tutorial
 * @return endTime of tutorial
 */
    public Date getEndTime() {
        return endTime;
    }
/**
 * gets the lesson type which is tutorial for this class
 * @return lesson_type as tutorial
 */
    @Override
    public Main.LESSON_TYPE getLessonType() {
        return lesson_type;
    }
/**
 * gets the day of the week that the tutorial is on
 * @return day of the week tutorial is on
 */
    @Override
    public Main.DAY_OF_WEEK getDayoftheWeek() {
        return dayOfWeek;
    }
/**
 * gets whether lesson occurs on odd or even week
 * @return tutorial is on both odd and even weeks
 */
    @Override
    public Main.ODD_EVEN getOddorEven() {
        return oddOrEven;
    }
/**
 * sets the end time of the tutorial
 * @param endTime end time of tutorial
 */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
/**
 * gets the venue of the tutorial
 * @return String venue of tutorial
 */
    public String getVenue() {
        return venue;
    }
/**
 * sets the venue of the tutorial
 * @param venue venue of tutorial
 */
    public void setVenue(String venue) {
        this.venue = venue;
    }
/**
 * sets which day of the week the tutorial is on
 * @param dayOfWeek day of the week tutorial is on
 */
    public void setDayOfWeek(Main.DAY_OF_WEEK dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    

}
