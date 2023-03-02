package fr.esgi.extiaordinaryapi.utils;

import fr.esgi.extiaordinaryapi.dto.UserResponse;
import fr.esgi.extiaordinaryapi.entity.User;

public interface UserInitializer {

    static UserResponse  mappingUserResponse(User user){
        return new UserResponse(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }
}
