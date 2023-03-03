package fr.esgi.extiaordinaryapi.controller;

import fr.esgi.extiaordinaryapi.dto.UserResponse;
import fr.esgi.extiaordinaryapi.entity.User;
import fr.esgi.extiaordinaryapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getAllUsersByPointsDesc() {
        return userService.getAllUsersByPointsDesc();
    }

    @GetMapping("/me")
    public UserResponse getMe() {
        return userService.getMe();
    }
}
