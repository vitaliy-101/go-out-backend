package com.example.gooutbackend.mapper;

import com.example.gooutbackend.dto.AuthRequest;
import com.example.gooutbackend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthMapper {

    @Mapping(target = "password", qualifiedByName = "getEncodedPassword", source = "password")
    User fromAuthRequestToUser(AuthRequest authRequest);

    @Named("getEncodedPassword")
    default String getEncodedPassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
