package com.example.gooutbackend.controller;

import com.example.gooutbackend.dto.user.UserDtoIn;
import com.example.gooutbackend.dto.user.UserInformationDto;
import com.example.gooutbackend.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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

    @PutMapping("/update/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody UserDtoIn userDtoIn) {
        userService.updateUser(id, userDtoIn);
    }

}
