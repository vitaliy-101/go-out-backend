package com.example.gooutbackend.dto.playground;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class PlaygroundMiniDto {
    private Long id;
    private Double longitude;
    private Double latitude;
}
