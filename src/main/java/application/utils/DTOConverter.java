package application.utils;

import application.dto.AccountDTO;
import application.dto.RoleDTO;
import application.entities.AccountEntity;
import application.entities.RoleEntity;

import java.util.HashSet;
import java.util.Set;

public class DTOConverter {
    public static AccountDTO toAccountDTO(AccountEntity accountEntity) {
        return new AccountDTO(accountEntity);
    }

    public static Set<AccountDTO> toAccountDTOs(Set<AccountEntity> accountEntities) {
        Set<AccountDTO> accountDTOs = new HashSet<>();
        for (AccountEntity accountEntity : accountEntities) {
            accountDTOs.add(new AccountDTO(accountEntity));
        }
        return accountDTOs;
    }

    public static RoleDTO toRoleDTO(RoleEntity roleEntity) {
        return new RoleDTO(roleEntity);
    }

    public static Set<RoleDTO> toRoleDTOs(Set<RoleEntity> roleEntities) {
        Set<RoleDTO> roleDTOs = new HashSet<>();
        for (RoleEntity roleEntity : roleEntities) {
            roleDTOs.add(new RoleDTO(roleEntity));
        }
        return roleDTOs;
    }
}
