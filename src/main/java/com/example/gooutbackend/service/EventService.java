package com.example.gooutbackend.service;

import com.example.gooutbackend.dto.event.EventDto;
import com.example.gooutbackend.dto.event.PlaygroundEventInfoDto;
import com.example.gooutbackend.dto.event.UserEventDtoIn;
import com.example.gooutbackend.dto.playground.PlaygroundEventDto;
import com.example.gooutbackend.entity.Playground;
import com.example.gooutbackend.entity.PlaygroundEvent;
import com.example.gooutbackend.entity.UserEvent;
import com.example.gooutbackend.repository.PlaygroundEventRepository;
import com.example.gooutbackend.repository.PlaygroundRepository;
import com.example.gooutbackend.repository.UserEventRepository;
import com.example.gooutbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {
    private final PlaygroundEventRepository playgroundEventRepository;
    private final UserEventRepository userEventRepository;
    private final PlaygroundRepository playgroundRepository;
    private final UserRepository userRepository;

    public List<EventDto> getActualEvents() {
        List<PlaygroundEvent> playgroundEvents = playgroundEventRepository.getActualPlaygroundEvents();
        List<EventDto> eventDtoList = new ArrayList<>();
        for (PlaygroundEvent playgroundEvent : playgroundEvents) {
            List<UserEvent> userEvents = userEventRepository.getUserEventsByPlaygroundEventId(playgroundEvent.getId());
            Playground playground = playgroundEvent.getPlayground();
            EventDto eventDto = new EventDto(userEvents.size(), playgroundEvent.getUserMaxCount(),"Высокий",
                    new PlaygroundEventInfoDto(playground.getId(),playgroundEvent.getId(), playground.getType(), playground.getTown(), playground.getStreet(), playgroundEvent.getStartTime()));
            eventDtoList.add(eventDto);
        }
        return eventDtoList;
    }


    public void createEvent(UserEventDtoIn userEventDtoIn, Long userId) {
        Playground playground = playgroundRepository.getReferenceById(userEventDtoIn.getPlaygroundId());
        PlaygroundEvent existedPlaygroundEvent = playgroundEventRepository.getPlaygroundEventByPlaygroundIdAndStartTime(userEventDtoIn.getPlaygroundId(), userEventDtoIn.getStartTime());
        if (existedPlaygroundEvent != null) {
            UserEvent userEvent = new UserEvent();
            userEvent.setWillCome(true);
            userEvent.setCreator(false);
            userEvent.setUser(userRepository.getReferenceById(userId));
            userEvent.setPlaygroundEvent(existedPlaygroundEvent);
            userEventRepository.save(userEvent);
        }
        else {
            PlaygroundEvent playgroundEvent = new PlaygroundEvent();
            playgroundEvent.setPlayground(playground);
            playgroundEvent.setStartTime(userEventDtoIn.getStartTime());
            playgroundEvent.setUserMaxCount(userEventDtoIn.getUserMaxCount());
            playgroundEvent.setGeneralCollection(userEventDtoIn.getIsGeneralCollection());
            playgroundEventRepository.save(playgroundEvent);

            UserEvent userEvent = new UserEvent();
            userEvent.setWillCome(true);
            userEvent.setCreator(userEventDtoIn.getIsGeneralCollection());
            userEvent.setUser(userRepository.getReferenceById(userId));
            userEvent.setPlaygroundEvent(playgroundEvent);
            userEventRepository.save(userEvent);
        }
    }

    public void closeEvent(Long userId, Long playgroundEventId) {
        userEventRepository.deleteByUserIdAndPlaygroundEventId(userId, playgroundEventId);
    }

}
