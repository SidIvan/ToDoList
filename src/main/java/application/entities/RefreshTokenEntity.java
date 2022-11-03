package application.entities;

import application.exceptions.TokenCreationException;
import application.utils.TokenGenerator;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="refresh_tokens")
public class RefreshTokenEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String value;

    @OneToOne(mappedBy = "refreshToken")
    private AccountEntity account;

    public RefreshTokenEntity() {
        value = TokenGenerator.generateToken();
    }

    public RefreshTokenEntity(String jsonString) throws TokenCreationException {
        try {
            JSONObject json = (JSONObject) JSONValue.parseWithException(jsonString);
            if (!json.containsKey("token_value")) {
                throw new TokenCreationException(0);
            }
            String token = json.get("token_value").toString();
            for(int i = 0; i < token.length(); ++i) {
                if((i + 1) % 4 == 0 && (token.charAt(i) > '9' || token.charAt(i) < '0')) {
                    throw new TokenCreationException(1);
                }
                if((i + 1) % 4 != 0 && (token.charAt(i) > 'z' || token.charAt(i) < 'a')) {
                    throw new TokenCreationException(1);
                }
            }
            value = token;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
