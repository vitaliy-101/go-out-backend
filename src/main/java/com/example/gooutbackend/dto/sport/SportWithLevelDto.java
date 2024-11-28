package com.example.gooutbackend.dto.sport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class SportWithLevelDto {
    private String sportName;
    private Integer level;
}
