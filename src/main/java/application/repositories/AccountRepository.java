package application.repositories;

import application.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

    @Query(value = "SELECT * FROM accounts WHERE login = :login",
           nativeQuery = true)
    Optional<AccountEntity> findByLogin(String login);

}
