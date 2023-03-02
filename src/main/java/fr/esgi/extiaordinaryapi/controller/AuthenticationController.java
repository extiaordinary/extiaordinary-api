package fr.esgi.extiaordinaryapi.controller;

import fr.esgi.extiaordinaryapi.dto.JwtToken;
import fr.esgi.extiaordinaryapi.dto.LoginDto;
import fr.esgi.extiaordinaryapi.dto.RegisterDto;
import fr.esgi.extiaordinaryapi.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<JwtToken> register(
            @RequestBody RegisterDto request
    ) {
        try {
            return ResponseEntity.ok(authenticationService.register(request));
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity authenticate(
            @RequestBody LoginDto request
    ) {
        try {
            return ResponseEntity.ok(authenticationService.authenticate(request));
        } catch (Exception e){
            return new ResponseEntity("Authentication failed", HttpStatus.FORBIDDEN); // TODO change
        }
    }


}
