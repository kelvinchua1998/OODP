import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public abstract class Lesson implements Serializable {

    public abstract Date getStartTime();
    
    public abstract Date getEndTime();
    
}