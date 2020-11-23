import java.io.Serializable;
import java.util.Date;

public abstract class Lesson implements Serializable {

    public abstract Date getStartTime();
    
    public abstract Date getEndTime();

    public abstract Main.LESSON_TYPE getLessonType();

    public abstract Main.DAY_OF_WEEK getDayoftheWeek();

    public abstract Main.ODD_EVEN getOddorEven();

    public abstract String getVenue();
}