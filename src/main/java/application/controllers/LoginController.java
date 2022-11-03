package application.controllers;

import application.entities.AccountEntity;
import application.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("")
    public String login(@RequestBody String jsonString) {
        return loginService.login(jsonString);
    }

    @PostMapping("/access")
    public String access(@RequestBody String jsonString) {return loginService.access(jsonString);}

    @PostMapping("/refresh")
    public String refresh(@RequestBody String jsonString) {return loginService.refresh(jsonString);}
}
