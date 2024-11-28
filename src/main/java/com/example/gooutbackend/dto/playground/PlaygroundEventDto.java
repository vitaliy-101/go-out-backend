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
public class PlaygroundEventDto {
    private Long id;
    private String type;
    private String town;
    private String street;
    private LocalDateTime startTime;
}
