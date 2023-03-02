package fr.esgi.extiaordinaryapi.service;

import fr.esgi.extiaordinaryapi.dto.JwtToken;
import fr.esgi.extiaordinaryapi.dto.LoginDto;
import fr.esgi.extiaordinaryapi.dto.RegisterDto;
import fr.esgi.extiaordinaryapi.entity.Role;
import fr.esgi.extiaordinaryapi.entity.User;
import fr.esgi.extiaordinaryapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtToken register(RegisterDto request) {
        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return JwtToken.builder()
                .token(jwtToken)
                .build();
    }

    public JwtToken authenticate(LoginDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(RuntimeException::new);
        var jwtToken = jwtService.generateToken(user);
        return JwtToken.builder()
                .token(jwtToken)
                .build();
    }
}
