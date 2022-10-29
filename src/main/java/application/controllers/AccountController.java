package application.controllers;

import application.dto.AccountDTO;
import application.entities.AccountEntity;
import application.repositories.AccountRepository;
import application.services.AccountService;
import application.utils.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/getAllAccountInfo")
    List<AccountDTO> getAllAccountInfo() {
        return accountService.getAllAccountInfo();
    }
}
