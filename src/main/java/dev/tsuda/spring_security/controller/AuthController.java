package dev.tsuda.spring_security.controller;

import dev.tsuda.spring_security.dto.request.LoginRequest;
import dev.tsuda.spring_security.dto.request.RegisterUserRequest;
import dev.tsuda.spring_security.dto.response.LoginResponse;
import dev.tsuda.spring_security.dto.response.RegisterUserResponse;
import dev.tsuda.spring_security.entity.User;
import dev.tsuda.spring_security.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserRepository userRepository, PasswordEncoder encoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {

        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(
                request.email(), request.password()
        );

        Authentication authentication = authenticationManager.authenticate(userAndPass);

        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> register(@Valid @RequestBody RegisterUserRequest request) {

        User user = new User();
        user.setEmail(request.email());
        user.setName(request.name());
        user.setPassword(encoder.encode(request.password()));

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterUserResponse(user.getName(), user.getEmail()));
    }
}
