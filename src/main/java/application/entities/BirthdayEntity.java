package application.entities;


import application.repositories.BirthdayRepository;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Time;

import static java.lang.Integer.parseInt;

@Entity
@Table(name="birthdays")
public class BirthdayEntity {
    @Id
    @Column
    private int id;

    @Column
    private String newBornName;

    public BirthdayEntity() {}

    public BirthdayEntity(String jsonString) {
        try {
            JSONObject json = (JSONObject) JSONValue.parseWithException(jsonString);
            setNewBornName((String) json.get("newBornName"));
            if (json.containsKey("id")) {
                setId(parseInt(json.get("id").toString()));
            }
        } catch(ParseException ex) {
            ex.printStackTrace();
        }
    }

    BirthdayEntity(int id, String newBornName) {
        this.newBornName = newBornName;
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewBornName() {
        return newBornName;
    }

    public void setNewBornName(String newBornName) {
        this.newBornName = newBornName;
    }

    public String toString() {
        return "Birthday:\nId: " + id + "\nNewborn name: " + newBornName;
    }
}
