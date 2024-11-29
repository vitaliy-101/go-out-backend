package com.example.gooutbackend.service;

import com.example.gooutbackend.dto.playground.PlaygroundInformationDto;
import com.example.gooutbackend.dto.playground.PlaygroundStateTimeDto;
import com.example.gooutbackend.entity.Playground;
import com.example.gooutbackend.entity.PlaygroundEvent;
import com.example.gooutbackend.entity.UserEvent;
import com.example.gooutbackend.mapper.PlaygroundMapper;
import com.example.gooutbackend.repository.PlaygroundEventRepository;
import com.example.gooutbackend.repository.PlaygroundRepository;
import com.example.gooutbackend.repository.UserEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaygroundService {
    private final PlaygroundRepository playgroundRepository;
    private final PlaygroundEventRepository playgroundEventRepository;
    private final UserEventRepository userEventRepository;
    private final PlaygroundMapper playgroundMapper;

    public List<Playground> getAllPlaygrounds() {
        return playgroundRepository.findAll();
    }

    public PlaygroundInformationDto getPlaygroundById(Long playgroundId, Long userId) {
        Playground playground = playgroundRepository.getReferenceById(playgroundId);
        List<PlaygroundEvent> playgroundEvents = playgroundEventRepository.getPlaygroundEventByPlaygroundId(playgroundId);

        // Сортировка по дате startTime
        playgroundEvents = playgroundEvents.stream()
                .sorted(Comparator.comparing(PlaygroundEvent::getStartTime))
                .toList();

        PlaygroundInformationDto playgroundInformationDto = playgroundMapper.fromPlaygroundToInformationDto(playground);

        for (PlaygroundEvent playgroundEvent : playgroundEvents) {
            // Сбор информации для каждого события
            PlaygroundStateTimeDto playgroundStateTimeDto = new PlaygroundStateTimeDto(playgroundEvent.getId(),
                    userEventRepository.getUserEventsByPlaygroundEventId(playgroundEvent.getId()).size(),
                    "Высокий",
                    playgroundEvent.getStartTime()
            );
            playgroundInformationDto.addPlaygroundStateTimeDto(playgroundStateTimeDto);
        }

        return playgroundInformationDto;
    }
}
