package fr.esgi.extiaordinaryapi.exception;

import java.util.UUID;

public final class ChallengeException extends RuntimeException {

    private ChallengeException(String message) {
        super(message);
    }

    public static ChallengeException notFoundAccountId(UUID challengeId) {
        return new ChallengeException(String.format("%s not found.", challengeId.toString()));
    }
}
