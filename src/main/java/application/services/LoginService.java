package application.services;

import application.entities.AccountEntity;
import application.exceptions.AccountCreationException;
import application.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    AccountRepository accountRepository;

    public String login(String jsonString) {
        try {
            AccountEntity accountInfo = new AccountEntity(jsonString);
            Optional<AccountEntity> account = accountRepository.findByLogin(accountInfo.getLogin());
            if (account.isEmpty() || !account.get().getPassword().equals(accountInfo.getPassword())) {
                return "permission denied";
            }
            return "permission access";

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "bad request";
    }
}
