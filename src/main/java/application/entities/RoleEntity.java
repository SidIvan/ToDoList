package application.entities;

import application.exceptions.RoleCreationException;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class RoleEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;

    @ManyToMany(mappedBy = "roles")
    private Set<AccountEntity> accounts = new HashSet<>();

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
