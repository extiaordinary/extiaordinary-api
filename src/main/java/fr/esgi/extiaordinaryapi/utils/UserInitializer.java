package fr.esgi.extiaordinaryapi.utils;

import fr.esgi.extiaordinaryapi.dto.UserResponse;
import fr.esgi.extiaordinaryapi.entity.User;

public interface UserInitializer {

    static UserResponse mappingUserResponse(User user) {
        if (user != null){
            return new UserResponse(
                    user.getUserId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getDateOfBirth(),
                    user.getPoints(),
                    user.getRole().toString()
            );
        }
        return null;
    }
}
