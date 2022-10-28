package application.entities;


import application.exceptions.AccountCreationException;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "Accounts")
public class AccountEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String login;

    @Column
    String password;

    public AccountEntity() {}

    public AccountEntity(String jsonString) throws AccountCreationException {
        try {
            JSONObject json = (JSONObject) JSONValue.parseWithException(jsonString);
            if(!json.containsKey("login")) {
                throw new AccountCreationException(0);
            }
            if(!json.containsKey("password")) {
                throw new AccountCreationException(1);
            }
            login = (String) json.get("login");
            password = (String) json.get("password");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }
}
