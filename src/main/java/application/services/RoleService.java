package application.services;

import application.entities.RoleEntity;
import application.exceptions.RoleCreationException;
import application.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public int createRole(String jsonString) {
        try {
            RoleEntity role = new RoleEntity(jsonString);
            return roleRepository.saveAndFlush(role).getId();
        } catch (RoleCreationException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
}
