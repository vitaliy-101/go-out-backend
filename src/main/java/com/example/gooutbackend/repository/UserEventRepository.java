package com.example.gooutbackend.repository;

import com.example.gooutbackend.entity.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserEventRepository extends JpaRepository<UserEvent, Long> {
    @Query(value = "SELECT ue.* FROM user_event ue " +
            "JOIN user_playground_event upe ON ue.id = upe.user_event_id " +
            "JOIN playground_event pe ON upe.playground_event_id = pe.id " +
            "WHERE pe.id = :playgroundEventId", nativeQuery = true)
    List<UserEvent> getUserEventsByPlaygroundEventId(@Param("playgroundEventId") Long playgroundEventId);

    Set<UserEvent> getUserEventsByUserId(Long id);

}
