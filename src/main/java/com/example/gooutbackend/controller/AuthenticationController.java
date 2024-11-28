package com.example.gooutbackend.controller;


import com.example.gooutbackend.dto.AuthRequest;
import com.example.gooutbackend.dto.AuthenticationResponse;
import com.example.gooutbackend.entity.User;
import com.example.gooutbackend.exceptions.LoginException;
import com.example.gooutbackend.mapper.AuthMapper;
import com.example.gooutbackend.service.auth.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Регистрация и логин")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final AuthMapper authMapper;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authenticationService.register(authMapper.fromAuthRequestToUser(request)));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthRequest request) throws LoginException {
        var user = new User();
        user.setNickname(request.getNickname());
        user.setPassword(request.getPassword());
        return ResponseEntity.ok(authenticationService.authenticate(user));
    }


}
