import java.io.Serializable;
import java.util.Date;
/**
 * this is the parent class for Lecture, Tutorial and Labs. It implements Serializable so that it can be serialized into the database
 * @author kelvin melvin
 */
public abstract class Lesson implements Serializable {
/**
 * abtract method to get start time of lessons
 * 
 */
    public abstract Date getStartTime();
/**
 * abstract method to get end time of lessons
 * 
 */    
    public abstract Date getEndTime();
/**
 * abstract method to get the type of lesson(lecture, tutorial or labs)
 * 
 */
    public abstract Main.LESSON_TYPE getLessonType();
/**
 * abstract class to get which day of the week the lesson is on
 * 
 */
    public abstract Main.DAY_OF_WEEK getDayoftheWeek();
/**
 * abtract class that get if lesson occurs on odd weeks or even weeks or both
 * 
 */
    public abstract Main.ODD_EVEN getOddorEven();
/**
 * abstract class to get the venue of lessons
 * 
 */
    public abstract String getVenue();
}