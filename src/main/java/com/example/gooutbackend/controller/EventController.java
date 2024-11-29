package com.example.gooutbackend.controller;

import com.example.gooutbackend.dto.event.EventDto;
import com.example.gooutbackend.dto.event.UserEventDtoIn;
import com.example.gooutbackend.dto.playground.PlaygroundMiniDto;
import com.example.gooutbackend.entity.User;
import com.example.gooutbackend.service.EventService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
@Tag(name = "Конечные точки для работы с мероприятиями")
public class EventController {
    private final EventService eventService;
    @GetMapping
    public List<EventDto> getAllEvents() {
        return eventService.getActualEvents();
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public void createEvent(@RequestBody UserEventDtoIn userEventDtoIn) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((User) authentication.getPrincipal()).getId();
        eventService.createEvent(userEventDtoIn, userId);
    }

    @DeleteMapping("/user/close")
    @PreAuthorize("hasRole('USER')")
    public void closeUserEvent(Long playgroundEventId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((User) authentication.getPrincipal()).getId();
        eventService.closeEvent(userId, playgroundEventId);
    }

}
