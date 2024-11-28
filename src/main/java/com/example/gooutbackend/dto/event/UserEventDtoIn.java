package com.example.gooutbackend.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserEventDtoIn {
    private Long playgroundId;
    private LocalDateTime startTime;
    private Boolean isGeneralCollection;
}
