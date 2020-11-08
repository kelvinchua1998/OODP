import java.io.Serializable;
import java.util.Date;


public class Tutorial extends Lesson implements Serializable{
    private Date startTime;
    private Date endTime;
    private String venue;
    private String dayOfWeek;

    public Tutorial(java.util.Date startTimeParsed, java.util.Date endTimeParsed, String venue, String dayOfWeek) {
        this.startTime = startTimeParsed;
        this.endTime = endTimeParsed;
        this.venue = venue;
        this.dayOfWeek = dayOfWeek;
    }


	public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
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

    

}
