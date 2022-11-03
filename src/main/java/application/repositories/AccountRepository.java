package application.repositories;

import application.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

    Optional<AccountEntity> findByLogin(String login);

    @Modifying
    @Transactional
    @Query(value = "UPDATE accounts SET access_token_id = :accessTokenId WHERE id = :id",
           nativeQuery = true)
    int updateAccess(@Param("id") int id, @Param("accessTokenId") int accessTokenId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE accounts SET refresh_token_id = :refreshTokenId WHERE id = :id",
           nativeQuery = true)
    int updateRefresh(@Param("id") int id, @Param("refreshTokenId") int refreshTokenId);


    @Modifying
    @Transactional
    @Query(value = "UPDATE accounts SET access_token_id = NULL WHERE id = :id",
           nativeQuery = true)
    int clearAccess(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE accounts SET refresh_token_id = NULL WHERE id = :id",
           nativeQuery = true)
    int clearRefresh(@Param("id") int id);

}
