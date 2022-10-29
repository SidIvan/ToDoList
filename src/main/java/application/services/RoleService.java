package application.services;

import application.dto.AccountDTO;
import application.dto.RoleDTO;
import application.entities.AccountEntity;
import application.entities.RoleEntity;
import application.exceptions.AccountDoesNotExistsException;
import application.exceptions.RoleCreationException;
import application.exceptions.RoleDoesNotExistsException;
import application.repositories.AccountRepository;
import application.repositories.RoleRepository;
import application.utils.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@Service
public class RoleService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RoleRepository roleRepository;

    public List<RoleDTO> getAllRoleInfo() {
        List<RoleEntity> roleEntities = roleRepository.findAll();
        List<RoleDTO> roles = new ArrayList<>();
        for (RoleEntity roleEntity : roleEntities) {
            RoleDTO roleDTO = DTOConverter.toRoleDTO(roleEntity);
            roleDTO.setAccounts(DTOConverter.toAccountDTOs(roleEntity.getAccounts()));
            roles.add(roleDTO);
        }
        return roles;
    }

    public int createRole(String jsonString) {
        try {
            RoleEntity role = new RoleEntity(jsonString);
            return roleRepository.saveAndFlush(role).getId();
        } catch (RoleCreationException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public int giveRole(int accountId, int roleId) throws AccountDoesNotExistsException,
                                                           RoleDoesNotExistsException {
        Optional<AccountEntity> account = accountRepository.findById(accountId);
        if(account.isEmpty()) {
            throw new AccountDoesNotExistsException(accountId);
        }
        Optional<RoleEntity> role = roleRepository.findById(roleId);
        if(role.isEmpty()) {
            throw new RoleDoesNotExistsException(roleId);
        }
        return roleRepository.giveRole(accountId, roleId);
    }

    public int removeRole(int accountId, int roleId) throws AccountDoesNotExistsException,
                                                            RoleDoesNotExistsException {
        Optional<AccountEntity> account = accountRepository.findById(accountId);
        if(account.isEmpty()) {
            throw new AccountDoesNotExistsException(accountId);
        }
        Optional<RoleEntity> role = roleRepository.findById(roleId);
        if(role.isEmpty()) {
            throw new RoleDoesNotExistsException(roleId);
        }
        return roleRepository.removeRole(accountId, roleId);
    }
}
