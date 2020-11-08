import java.io.Serializable;
import java.util.Date;

public abstract class Lesson implements Serializable {

    public abstract Date getStartTime();
    
    public abstract Date getEndTime();
    
}