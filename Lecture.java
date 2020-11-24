import java.io.Serializable;
import java.util.Date;

/**
 * sub class of Lesson and implements Serializable so that it can be serialized into database
 */
public class Lecture extends Lesson implements Serializable{
    
    /**
     * start time of lecture
     */
    private Date startTime;
    /**
     * end time of lecture
     */
    private Date endTime;
    /**
     * venue of the lecture
     */
    private String venue;
    /**
     * stores which day of the week lecture is on
     */
    private Main.DAY_OF_WEEK dayOfweek;
    /**
     * states whether lecture occurs on odd or even weeks
     */
    private Main.ODD_EVEN oddOrEven;
    /**
     * states the type of lesson which is lecture for this class
     */
    private Main.LESSON_TYPE lesson_type;

/**
 * default constructor to create Lecture class
 * @param startTime Date
 * @param endTime Date
 * @param venue String
 * @param dayOfweek Main.DAY_OF_WEEK
 */
    public Lecture(Date startTime, Date endTime, String venue, Main.DAY_OF_WEEK dayOfweek) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
        this.dayOfweek = dayOfweek;
        this.oddOrEven = Main.ODD_EVEN.OOD_AND_EVEN;
        this.lesson_type = Main.LESSON_TYPE.LECTURE;
    }
    
/**
 * gets the start time of lectures
 * @return startTime of lecture
 */
    public Date getStartTime() {
        return startTime;
    }

/**
 * gets the end time of lectures
 * @return endTime of lecture 
 */
    public Date getEndTime() {
        return endTime;
    }

/**
 * gets the lesson type
 * @return lesson_type as lecture
 */
    @Override
    public Main.LESSON_TYPE getLessonType() {
        return lesson_type;
    }

/**
 * gets the day of the week that lecture is on
 * @return day of the week lecture is on
 */
    @Override
    public Main.DAY_OF_WEEK getDayoftheWeek() {
        return dayOfweek;
    }

/**
 * gets whether lesson occurs on odd or even week
 * @return lecture is both on odd and even weeks
 */
    @Override
    public Main.ODD_EVEN getOddorEven() {
        return oddOrEven;
    }

/**
 * gets the venue of the lecture
 * @return String venue of the lecture
 */
    public String getVenue() {
        return venue;
    }

/**
 * sets the start time of the lecture
 * @param startTime Date
 */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

/**
 * sets the end time of the lecture
 * @param endTime Date
 */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

/**
 * sets the venue of the lecture
 * @param venue String
 */
    public void setVenue(String venue) {
        this.venue = venue;
    }

/**
 * sets which day of the week the lecture is on
 * @param dayOfweek Main.DAY_OF_WEEK
 */
    public void setDayOfweek(Main.DAY_OF_WEEK dayOfweek) {
        this.dayOfweek = dayOfweek;
    }

   
}
