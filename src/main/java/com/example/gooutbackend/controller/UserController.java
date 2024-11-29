package com.example.gooutbackend.controller;

import com.example.gooutbackend.dto.user.UserDtoIn;
import com.example.gooutbackend.dto.user.UserInformationDto;
import com.example.gooutbackend.entity.User;
import com.example.gooutbackend.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "Конечные точки для работы с пользователем")
public class UserController {
    private final UserService userService;

    @GetMapping("/information/{id}")
    public UserInformationDto getUserInformationDto(@PathVariable Long id) {
        return userService.getUserInformation(id);
    }

    @PutMapping("/update/sport")
    @PreAuthorize("hasRole('USER')")
    public void updateSport(String newSportName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((User) authentication.getPrincipal()).getId();
        userService.updateUserSportName(userId, newSportName);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('USER')")
    public void updateUser(@RequestBody UserDtoIn userDtoIn) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((User) authentication.getPrincipal()).getId();
        userService.updateUser(userId, userDtoIn);
    }

    @DeleteMapping("/delete/sport")
    @PreAuthorize("hasRole('USER')")
    public void deleteUserSport(String sportName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((User) authentication.getPrincipal()).getId();
        userService.deleteUserSport(userId, sportName);
    }

    @PutMapping("/update/sport/level")
    @PreAuthorize("hasRole('USER')")
    public void updateUserSportLevel(String sportName, Integer sportLevel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((User) authentication.getPrincipal()).getId();
        userService.updateUserSportLevel(userId, sportName, sportLevel);
    }

}
