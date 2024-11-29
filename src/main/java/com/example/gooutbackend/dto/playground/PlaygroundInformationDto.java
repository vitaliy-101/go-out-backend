package com.example.gooutbackend.dto.playground;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class PlaygroundInformationDto {
    private Long id;
    private String town;
    private String street;
    private String type;
    private Double longitude;
    private Double latitude;
    private Integer playgroundState;
    private Boolean currentBookState;
    private String downloadURL;
    private List<PlaygroundStateTimeDto> playgroundStateTimeDtoList = new ArrayList<>();


    public void addPlaygroundStateTimeDto(PlaygroundStateTimeDto playgroundStateTimeDto) {
        playgroundStateTimeDtoList.add(playgroundStateTimeDto);
    }
}
