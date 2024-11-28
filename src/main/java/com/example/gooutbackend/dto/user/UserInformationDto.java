package com.example.gooutbackend.dto.user;

import com.example.gooutbackend.dto.playground.PlaygroundEventDto;
import com.example.gooutbackend.dto.sport.SportWithLevelDto;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserInformationDto {
    private Long id;
    private String name;
    private String nickname;
    private String priorityArea;
    private List<SportWithLevelDto> sportWithLevelList;
    private List<String> achievements;
    private List<PlaygroundEventDto> playgroundEventDtoList;

}
