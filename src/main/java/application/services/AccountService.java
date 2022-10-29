package application.services;

import application.dto.AccountDTO;
import application.entities.AccountEntity;
import application.repositories.AccountRepository;
import application.utils.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public List<AccountDTO> getAllAccountInfo() {
        List<AccountEntity> accountEntities = accountRepository.findAll();
        List<AccountDTO> accounts = new ArrayList<>();
        for (AccountEntity accountEntity : accountEntities) {
            AccountDTO accountDTO = DTOConverter.toAccountDTO(accountEntity);
            accountDTO.setRoles(DTOConverter.toRoleDTOs(accountEntity.getRoles()));
            accounts.add(accountDTO);
        }
        return accounts;
    }
}
