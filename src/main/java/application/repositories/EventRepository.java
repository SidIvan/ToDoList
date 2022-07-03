package application.repositories;

import application.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Integer> {

    List<EventEntity> findByTitle(String title);

    @Query(value = "SELECT * FROM events WHERE start_date<=:date AND end_date >=:date order by start_time",
           nativeQuery = true)
    List<EventEntity> findByDate(@Param("date") Date date);


    @Query(value = "DELETE FROM events WHERE title = :title",
           nativeQuery = true)
    void deleteByTitle(@Param("title") String title);

    @Modifying
    @Transactional
    @Query(value = "UPDATE events SET next_id = :nextId WHERE id = :id",
           nativeQuery = true)
    int updateNextId(@Param("id") int id, @Param("nextId") int nextId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE events SET prev_id = :nextId WHERE id = :id",
            nativeQuery = true)
    int updatePrevId(@Param("id") int id, @Param("nextId") int nextId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE events SET title = :title WHERE id = :id",
            nativeQuery = true)
    int updateTitle(@Param("id") int id, @Param("title") String title);

    @Modifying
    @Transactional
    @Query(value = "UPDATE events SET place = :place WHERE id = :id",
            nativeQuery = true)
    int updatePlace(@Param("id") int id, @Param("place") String place);

    @Modifying
    @Transactional
    @Query(value = "UPDATE events SET text = :text WHERE id = :id",
            nativeQuery = true)
    int updateText(@Param("id") int id, @Param("text") String text);

    @Modifying
    @Transactional
    @Query(value = "UPDATE events SET start_date = :startDate WHERE id = :id",
            nativeQuery = true)
    int updateStartDate(@Param("id") int id, @Param("startDate") Date startDate);

    @Modifying
    @Transactional
    @Query(value = "UPDATE events SET end_date = :endDate WHERE id = :id",
            nativeQuery = true)
    int updateEndDate(@Param("id") int id, @Param("endDate") Date endDate);

    @Modifying
    @Transactional
    @Query(value = "UPDATE events SET start_time = :startTime WHERE id = :id",
            nativeQuery = true)
    int updateStartTime(@Param("id") int id, @Param("startTime") Time startTime);

    @Modifying
    @Transactional
    @Query(value = "UPDATE events SET end_time = :endTime WHERE id = :id",
            nativeQuery = true)
    int updateEndTime(@Param("id") int id, @Param("endTime") Time endTime);
}


