package fr.esgi.extiaordinaryapi.service;

import fr.esgi.extiaordinaryapi.entity.User;
import fr.esgi.extiaordinaryapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(details.getUsername()).orElseThrow(() -> new RuntimeException("user not found"));
    }

    public List<User> getAllUsersByPointsDesc() {
        return userRepository.findAllUsersOrderByPointsDesc();
    }

}
