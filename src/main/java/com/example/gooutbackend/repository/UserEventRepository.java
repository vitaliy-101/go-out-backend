package com.example.gooutbackend.repository;

import com.example.gooutbackend.entity.UserEvent;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEventRepository extends JpaRepository<UserEvent, Long> {
    @Query(value = "SELECT ue.* FROM user_event ue " +
            "JOIN playground_event pe ON ue.playground_event_id = pe.id " +
            "WHERE pe.id = :playgroundEventId", nativeQuery = true)
    List<UserEvent> getUserEventsByPlaygroundEventId(@Param("playgroundEventId") Long playgroundEventId);

    List<UserEvent> getUserEventsByUserId(Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserEvent ue WHERE ue.user.id = :userId AND ue.playgroundEvent.id = :playgroundEventId")
    void deleteByUserIdAndPlaygroundEventId(Long userId, Long playgroundEventId);


}
