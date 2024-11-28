package com.example.gooutbackend.service.auth;

import com.example.gooutbackend.dto.AuthenticationResponse;
import com.example.gooutbackend.entity.User;
import com.example.gooutbackend.entity.auth.Role;
import com.example.gooutbackend.entity.auth.Token;
import com.example.gooutbackend.exceptions.ExistByNicknameException;
import com.example.gooutbackend.exceptions.LoginException;
import com.example.gooutbackend.exceptions.NotFoundByNicknameException;
import com.example.gooutbackend.repository.TokenRepository;
import com.example.gooutbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    private void revokeAllTokenByUseId(User user) {
        List<Token> validTokenListByUserId = tokenRepository.findAllAccessTokensByUserId(user.getId());
        if (!validTokenListByUserId.isEmpty()) {
            validTokenListByUserId.forEach(token -> {
                token.setLoggedOut(true);
            });
        }
        tokenRepository.saveAll(validTokenListByUserId);
    }

    private void saveUserToken(String jwt, User user) {
        Token token = new Token();
        token.setAccessToken(jwt);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
    }

    public AuthenticationResponse register(User user) {
        if (existByNickName(user.getNickname())) {
            throw new ExistByNicknameException(User.class, user.getNickname());
        }
        user.setRole(Role.USER);
        user = userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        saveUserToken(jwt, user);
        return new AuthenticationResponse(jwt);
    }

    public AuthenticationResponse authenticate(User request) throws LoginException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getNickname(),
                            request.getPassword()
                    )
            );
        }
        catch (AuthenticationException e) {
                throw new LoginException(User.class);
        }
        User user = userRepository.findByNickname(request.getNickname())
                .orElseThrow(() -> new NotFoundByNicknameException(User.class, request.getNickname()));
        String jwt = jwtService.generateToken(user);
        revokeAllTokenByUseId(user);
        saveUserToken(jwt, user);
        return new AuthenticationResponse(jwt);
    }

    public Boolean existByNickName(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

}
