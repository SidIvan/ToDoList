package application.dto;

import application.entities.AccountEntity;
import application.entities.RoleEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class AccountDTO {

    private int id;
    private String login;
    private String password;
    private Set<RoleDTO> roles;

    public AccountDTO(AccountEntity accountEntity) {
        this.id = accountEntity.getId();
        this.login = accountEntity.getLogin();
        this.password = accountEntity.getPassword();
    }
}
