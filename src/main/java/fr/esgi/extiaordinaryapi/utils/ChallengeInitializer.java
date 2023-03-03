package fr.esgi.extiaordinaryapi.utils;

import fr.esgi.extiaordinaryapi.dto.ChallengeResponse;
import fr.esgi.extiaordinaryapi.entity.Challenge;

import java.util.List;

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


    static ChallengeResponse mapToChallenge(Challenge challenge){
        return new ChallengeResponse(
                challenge.getChallengeId(),
                challenge.getDateStart().toString(),
                challenge.getDateEnd().toString(),
                challenge.getDescription(),
                challenge.getTypeSport(),
                UserInitializer.mappingUserResponse(challenge.getCollaboratorChallenger()),
                UserInitializer.mappingUserResponse(challenge.getCollaboratorChallenged()),
                challenge.getWorkout(),
                challenge.getIsAchieved(),
                challenge.getTag()
        );
    }
}
