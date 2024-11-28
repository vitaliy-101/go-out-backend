package com.example.gooutbackend.repository;

import com.example.gooutbackend.dto.playground.PlaygroundEventDto;
import com.example.gooutbackend.entity.PlaygroundEvent;
import com.example.gooutbackend.entity.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface PlaygroundEventRepository extends JpaRepository<PlaygroundEvent, Long> {

    @Query("SELECT new com.example.gooutbackend.dto.playground.PlaygroundEventDto(p.id, p.type, p.town, p.street, pe.startTime) " +
            "FROM PlaygroundEvent pe " +
            "JOIN pe.playground p " +  // соединяем с Playground для получения типа
            "JOIN pe.userEvents ue " + // соединяем с UserEvent
            "WHERE ue.user.id = :userId AND ue.willCome = true") // фильтруем по пользователю и его желанию участвовать
    List<PlaygroundEventDto> findActualPlaygroundEventsByUserId(@Param("userId") Long userId);

    @Query("SELECT pe " +
            "FROM PlaygroundEvent pe " +
            "WHERE pe.startTime > CURRENT_TIMESTAMP " +
            "ORDER BY pe.startTime DESC")
    List<PlaygroundEvent> getActualPlaygroundEvents();

    List<PlaygroundEvent> getPlaygroundEventByPlaygroundId(Long id);

    List<PlaygroundEvent> getPlaygroundEventsByUserEvents(Set<UserEvent> userEvents);

    PlaygroundEvent getPlaygroundEventByPlaygroundIdAndStartTime(Long id, LocalDateTime startTime);


}