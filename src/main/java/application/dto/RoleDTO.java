package application.dto;

import application.entities.AccountEntity;
import application.entities.RoleEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class RoleDTO {

    private int id;
    private String title;
    private Set<AccountDTO> accounts;

    public RoleDTO(RoleEntity roleEntity) {
        this.id = roleEntity.getId();
        this.title = roleEntity.getTitle();
    }
}
