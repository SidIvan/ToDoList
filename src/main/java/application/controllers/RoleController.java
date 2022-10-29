package application.controllers;

import application.dto.RoleDTO;
import application.entities.RoleEntity;
import application.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/getAllRoleInfo")
    public List<RoleDTO> getAllRoleInfo() {
        return roleService.getAllRoleInfo();
    }
    @PostMapping("/createRole")
    public int createRole(@RequestBody String jsonString) {
        return roleService.createRole(jsonString);
    }

    @PutMapping("/giveRole/{accountId}/{roleId}")
    public int giveRole(@PathVariable(name="accountId") int accountId,
                        @PathVariable(name="roleId") int roleId) {
        try {
            return roleService.giveRole(accountId, roleId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @PutMapping("/removeRole/{accountId}/{roleId}")
    public int removeRole(@PathVariable(name="accountId") int accountId,
                          @PathVariable(name="roleId") int roleId) {
        try {
            return roleService.removeRole(accountId, roleId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }
}
