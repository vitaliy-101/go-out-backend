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
public class PlaygroundEventInfoDto {
    private Long playgroundId;
    private Long playgroundEventId;
    private String type;
    private String town;
    private String street;
    private LocalDateTime startTime;

}
