package application.repositories;

import application.entities.BirthdayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BirthdayRepository extends JpaRepository<BirthdayEntity, Integer> {

    List<BirthdayEntity> findById(int id);

    @Query(value = "SELECT * FROM birthdays WHERE new_born_name = :name",
           nativeQuery = true)
    List<BirthdayEntity> findByName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "UPDATE birthdays SET new_born_name = :name WHERE id = :id",
            nativeQuery = true)
    int putName(@Param("id") int id, @Param("name") String name);
}
