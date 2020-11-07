import java.io.Serializable;
import java.sql.Time;

public abstract class Lesson {
    
    // String Cindex;
    // String Course;


    // public Lesson(String course, String cindex) {
    //     Cindex = cindex;
    //     Course = course;
    // }

    // public String getCindex() {
    //     return Cindex;
    // }

    // public void setCindex(String cindex) {
    //     Cindex = cindex;
    // }

    // public String getCourse() {
    //     return Course;
    // }

    // public void setCourse(String course) {
    //     Course = course;
    // }

    public abstract Time getStartTime();
    
    public abstract Time getEndTime();
    
}