import java.io.Serializable;
import java.util.Date;

public class Labs extends Lesson implements Serializable {

    private java.util.Date startTime;
    private java.util.Date endTime;
    private String venue;
    private String dayOfWeek;
    private String oddOrEven;

    public Labs(java.util.Date startTime, java.util.Date endTime, String venue, String dayOfWeek, String oddOrEven) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
        this.dayOfWeek = dayOfWeek;
        this.oddOrEven = oddOrEven;
    }

    public java.util.Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public java.util.Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getOddOrEven() {
        return oddOrEven;
    }

    public void setOddOrEven(String oddOrEven) {
        this.oddOrEven = oddOrEven;
    }

   
}
