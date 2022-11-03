package application.entities;


import application.exceptions.AccountCreationException;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "Accounts")
public class AccountEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String login;

    @Column
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="access_token_id",
                referencedColumnName="id")
    private AccessTokenEntity accessToken;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="refresh_token_id",
            referencedColumnName="id")
    private RefreshTokenEntity refreshToken;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name = "applied_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private Set<RoleEntity> roles = new HashSet<>();

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
            login = json.get("login").toString();
            password = json.get("password").toString();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }
}
