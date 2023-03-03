package fr.esgi.extiaordinaryapi.exception;

import java.util.UUID;

public final class ChallengeException extends RuntimeException {

    public ChallengeException(String message) {
        super(message);
    }

    public static ChallengeException notFoundAccountId(UUID challengeId) {
        return new ChallengeException(String
                .format("the challenge with the id : %s was not found.",
                        challengeId.toString()));
    }
}
