package application.repositories;

import application.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    @Modifying
    @Transactional
    @Query(value="INSERT INTO applied_roles (account_id, role_id) VALUES (:accountId, :roleId)",
           nativeQuery = true)
    int giveRole(int accountId, int roleId);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM applied_roles WHERE account_id = :accountId AND role_id = :roleId",
           nativeQuery = true)
    int removeRole(int accountId, int roleId);
}
