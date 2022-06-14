package application.entities;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;


@Table(name="Events")
@Entity
public class EventEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;

    @Column
    private String place;

    @Column
    private String text;

    @Column
    private java.sql.Date startDate;

    @Column
    private java.sql.Date endDate;

    @Column
    private java.sql.Time startTime;

    @Column
    private java.sql.Time endTime;

    @Column
    boolean isLastEvent;

    public EventEntity() {
    }

    public EventEntity(String jsonString) {
        try {
            JSONObject json = (JSONObject) JSONValue.parseWithException(jsonString);
            setTitle((String) json.get("title"));
            setText((String) json.get("text"));
            setPlace((String) json.get("place"));
            setStartDate(Date.valueOf((String) json.get("startDate")));
            setEndDate(Date.valueOf((String) json.get("endDate")));
            setStartTime(Time.valueOf((String) json.get("startTime")));
            setEndTime(Time.valueOf((String) json.get("endTime")));
            setIsLastEvent((Boolean) json.get("isLastEvent"));
        } catch(ParseException ex) {
            ex.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getIsLastEvent() {
        return isLastEvent;
    }

    public void setIsLastEvent(boolean lastEvent) {
        isLastEvent = lastEvent;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
