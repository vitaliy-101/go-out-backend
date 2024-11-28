package com.example.gooutbackend.mapper;

import com.example.gooutbackend.dto.playground.PlaygroundEventDto;
import com.example.gooutbackend.dto.sport.SportWithLevelDto;
import com.example.gooutbackend.dto.user.UserInformationDto;
import com.example.gooutbackend.entity.Achievement;
import com.example.gooutbackend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "nickname", source = "user.nickname")
    @Mapping(target = "priorityArea", source = "user.priorityArea")
    @Mapping(target = "achievements", source = "achievements")
    @Mapping(target = "sportWithLevelList", source = "sportWithLevelList")
    UserInformationDto toUserInformationDto(
            User user,
            List<SportWithLevelDto> sportWithLevelList,
            List<String> achievements,
            List<PlaygroundEventDto> playgroundEventDtoList
    );
}