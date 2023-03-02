package fr.esgi.extiaordinaryapi.utils;

import fr.esgi.extiaordinaryapi.entity.Challenge;

public interface ChallengeInitializer {

    static void updateStateChallenge(Challenge challenge, Challenge existingChallenge) {
        existingChallenge.setDateStart(challenge.getDateStart());
        existingChallenge.setDateEnd(challenge.getDateEnd());
        existingChallenge.setDescription(challenge.getDescription());
        existingChallenge.setTypeSport(challenge.getTypeSport());
        existingChallenge.setCollaboratorChallenger(challenge.getCollaboratorChallenger());
        existingChallenge.setCollaboratorChallenged(challenge.getCollaboratorChallenged());
        existingChallenge.setWorkout(challenge.getWorkout());
        existingChallenge.setIsAchieved(challenge.getIsAchieved());
    }
}
