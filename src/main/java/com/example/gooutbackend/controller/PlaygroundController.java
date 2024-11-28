package com.example.gooutbackend.controller;

import com.example.gooutbackend.dto.playground.PlaygroundInformationDto;
import com.example.gooutbackend.dto.playground.PlaygroundMiniDto;
import com.example.gooutbackend.entity.User;
import com.example.gooutbackend.mapper.PlaygroundMapper;
import com.example.gooutbackend.service.PlaygroundService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/playground")
@Tag(name = "Конечные точки для работы со спортивными площадками")
public class PlaygroundController {
    private final PlaygroundService playgroundService;
    private final PlaygroundMapper playgroundMapper;

    @GetMapping("/all")
    public List<PlaygroundMiniDto> getAllPlaygrounds() {
        return playgroundMapper.fromPlaygroundListToMiniDto(playgroundService.getAllPlaygrounds());
    }

    @GetMapping("/{playgroundId}")
    @PreAuthorize("hasRole('USER')")
    public PlaygroundInformationDto getPlaygroundById(@PathVariable Long playgroundId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((User) authentication.getPrincipal()).getId();
        return playgroundService.getPlaygroundById(playgroundId, userId);
    }
}
