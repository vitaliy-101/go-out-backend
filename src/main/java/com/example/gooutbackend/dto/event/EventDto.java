package com.example.gooutbackend.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class EventDto {
    private Integer userCount;
    private String level; //считаем левел (высокий, низкий и тд)
    private PlaygroundEventInfoDto playgroundEventInfoDto;
}
