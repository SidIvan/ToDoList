package application.services;

import application.entities.AccountEntity;
import application.exceptions.AccountCreationException;
import application.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    @Autowired
    AccountRepository accountRepository;

    public int createUser(String jsonString) {
        try {
            AccountEntity newAccount = new AccountEntity(jsonString);
            return accountRepository.saveAndFlush(newAccount).getId();
        } catch(AccountCreationException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
}
