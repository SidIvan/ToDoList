package application.entities;

import jdk.jfr.Event;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;



import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

import static java.lang.Integer.parseInt;


@Table(name="Events")
@Entity
public class EventEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int nextId = -1;

    @Column
    private int prevId = -1;

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
            if (json.containsKey("prevId")) {
                setPrevId(parseInt(json.get("prevId").toString()));
            }
            if (json.containsKey("nextId")) {
                setNextId(parseInt(json.get("nextId").toString()));
            }
            if (json.containsKey("id")) {
                setId(parseInt(json.get("id").toString()));
            }
        } catch(ParseException ex) {
            ex.printStackTrace();
        }
    }

    public EventEntity clone() {
        EventEntity event = new EventEntity();
        event.setNextId(nextId);
        event.setPrevId(prevId);
        event.setId(id);
        event.setText(text);
        event.setTitle(title);
        event.setPlace(place);
        event.setStartDate((Date) startDate.clone());
        event.setEndDate((Date) endDate.clone());
        event.setStartTime((Time) startTime.clone());
        event.setEndTime((Time) endTime.clone());
        return event;
    }
    public String toString() {
        String res = "Event with id " + id + "\nTitle: " + title + "\nPlace: " + place + "\nText: " + text +
                "\nStart date: " + startDate + "\nEnd date: " + endDate + "\nStart time: " + startTime + "\nEnd time: " +
                endTime;
        if (prevId != -1) {
            res += "\nPrev id:" + prevId;
        }
        if (nextId != -1) {
            res += "\nNext id:" + nextId;
        }
        return res;

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

    public int getNextId() {
        return nextId;
    }

    public void setNextId(int nextId) {
        this.nextId = nextId;
    }

    public int getPrevId() {
        return prevId;
    }

    public void setPrevId(int prevId) {
        this.prevId = prevId;
    }
}
