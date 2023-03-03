package fr.esgi.extiaordinaryapi.service;

import fr.esgi.extiaordinaryapi.dto.UserResponse;
import fr.esgi.extiaordinaryapi.entity.User;
import fr.esgi.extiaordinaryapi.exception.UserException;
import fr.esgi.extiaordinaryapi.repository.UserRepository;
import fr.esgi.extiaordinaryapi.utils.UserInitializer;
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

    public UserResponse getMe() {
        User user = getCurrentUser();
        if (user==null){
            throw new UserException("No user authenticated");
        }
        return userRepository.findById(user.getUserId()).map(UserInitializer::mappingUserResponse).orElseThrow(() -> new RuntimeException("user not found"));
    }

    public User save(User user){
        return userRepository.save(user);
    }
}
