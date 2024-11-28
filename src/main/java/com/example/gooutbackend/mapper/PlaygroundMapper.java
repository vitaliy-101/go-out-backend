package com.example.gooutbackend.mapper;

import com.example.gooutbackend.dto.AuthRequest;
import com.example.gooutbackend.dto.playground.PlaygroundInformationDto;
import com.example.gooutbackend.dto.playground.PlaygroundMiniDto;
import com.example.gooutbackend.entity.Playground;
import com.example.gooutbackend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlaygroundMapper {

    List<PlaygroundInformationDto> fromPlaygroundListToInformationDto(List<Playground> playgrounds);

    @Mapping(target = "downloadURL", qualifiedByName = "createDownloadURL", source = ".")
    PlaygroundInformationDto fromPlaygroundToInformationDto(Playground playground);

    List<PlaygroundMiniDto> fromPlaygroundListToMiniDto(List<Playground> playgrounds);

    PlaygroundMiniDto fromPlaygroundToMiniDto(Playground playground);

    @Named("createDownloadURL")
    default String createDownloadURL(Playground playground) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/playground/download/")
                .path(String.valueOf(playground.getId()))
                .toUriString();
    }
}
