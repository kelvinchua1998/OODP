import java.io.Serializable;
import java.util.Date;

/**
 * sub class of Lesson and implements Serializable so that it can be serialized into database
 */
public class Lecture extends Lesson implements Serializable{
    
    private Date startTime;
    private Date endTime;
    private String venue;
    private Main.DAY_OF_WEEK dayOfweek;
    private Main.ODD_EVEN oddOrEven;
    private Main.LESSON_TYPE lesson_type;

/**
 * default constructor to create Lecture class
 * @param startTimeParsed
 * @param endTimeParsed
 * @param venue
 * @param dayOfWeek
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
 * @return Date startTime
 */
    public Date getStartTime() {
        return startTime;
    }

/**
 * gets the end time of lectures
 * @return Date endTime
 */
    public Date getEndTime() {
        return endTime;
    }

/**
 * gets the lesson type which is lecture for this class
 * @return Main.LESSON_TYPE lesson_type
 */
    @Override
    public Main.LESSON_TYPE getLessonType() {
        return lesson_type;
    }

/**
 * gets the day of the week that lecture is on
 * @return Main.DAY_OF_WEEK dayOfweek
 */
    @Override
    public Main.DAY_OF_WEEK getDayoftheWeek() {
        return dayOfweek;
    }

/**
 * gets whether lesson occurs on odd or even week. For this class it is both
 * @return Main.ODD_EVEN oddOrEven
 */
    @Override
    public Main.ODD_EVEN getOddorEven() {
        return oddOrEven;
    }

/**
 * gets the venue of the lecture
 * @return String venue
 */
    public String getVenue() {
        return venue;
    }

/**
 * sets the start time of the lecture
 * @param startTime
 */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

/**
 * sets the end time of the lecture
 * @param endTime
 */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

/**
 * sets the venue of the lecture
 * @param venue
 */
    public void setVenue(String venue) {
        this.venue = venue;
    }

/**
 * sets which day of the week the lecture is on
 * @param dayOfweek
 */
    public void setDayOfweek(Main.DAY_OF_WEEK dayOfweek) {
        this.dayOfweek = dayOfweek;
    }

   
}
