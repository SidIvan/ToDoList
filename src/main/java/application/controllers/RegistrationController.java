package application.controllers;

import application.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    RegistrationService registrationService;

    @PostMapping("/createUser")
    int creatingUser(@RequestBody String jsonString) {
        return registrationService.createUser(jsonString);
    }
}
