package fr.esgi.extiaordinaryapi.controller;

import fr.esgi.extiaordinaryapi.dto.JwtTokenResponse;
import fr.esgi.extiaordinaryapi.dto.LoginRequest;
import fr.esgi.extiaordinaryapi.dto.RegisterRequest;
import fr.esgi.extiaordinaryapi.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<JwtTokenResponse> register(
            @RequestBody @Valid RegisterRequest request
    ) {
        try {
            return ResponseEntity.ok(authenticationService.registerUser(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity authenticate(
            @RequestBody @Valid LoginRequest request
    ) {
        try {
            return ResponseEntity.ok(authenticationService.authenticateUser(request));
        } catch (Exception e) {
            return new ResponseEntity("Authentication failed", HttpStatus.FORBIDDEN); // TODO change
        }
    }
}
