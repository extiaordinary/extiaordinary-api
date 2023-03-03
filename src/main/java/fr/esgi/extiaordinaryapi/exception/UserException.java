package fr.esgi.extiaordinaryapi.exception;

import java.util.UUID;

public final class UserException extends RuntimeException {

    public UserException(String message) {
        super(message);
    }

    public static UserException notFoundUserId(UUID userId) {
        return new UserException(String
                .format("the user with the id : %s was not found.",
                        userId.toString()));
    }
}
