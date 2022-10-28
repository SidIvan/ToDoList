package application.entities;

import application.exceptions.RoleCreationException;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class RoleEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String title;

    public RoleEntity() {}

    public RoleEntity(String jsonString) throws RoleCreationException {
        try {
            JSONObject json = (JSONObject) JSONValue.parseWithException(jsonString);
            if(!json.containsKey("title")) {
                throw new RoleCreationException(0);
            }
            this.title = (String) json.get("title");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
