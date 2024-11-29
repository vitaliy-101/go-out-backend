package com.example.gooutbackend.dto.playground;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class PlaygroundStateTimeDto {
    private Long playgroundEventId;
    private Integer userMaxCount;
    private Integer userCount;
    private String level;
    private LocalDateTime startTime;
}
