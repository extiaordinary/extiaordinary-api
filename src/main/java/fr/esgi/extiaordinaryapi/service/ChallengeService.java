package fr.esgi.extiaordinaryapi.service;

import fr.esgi.extiaordinaryapi.entity.Challenge;
import fr.esgi.extiaordinaryapi.exception.ChallengeException;
import fr.esgi.extiaordinaryapi.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;

    public Challenge createChallenge(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    public Challenge findById(UUID challengeId) {
        return challengeRepository
                .findChallengeByChallengeId(challengeId)
                .orElseThrow(() -> new ChallengeException("The challenge with the id : "
                        + challengeId +" was not found"));
    }

    public List<Challenge> findAll() {
        return challengeRepository.findAll();
    }

    public Challenge updateChallenge(Challenge challenge) {
        UUID challengeId = challenge.getChallengeId();
        var existingChallenge = challengeRepository
                .findChallengeByChallengeId(challengeId)
                .orElseThrow(() -> new ChallengeException("The challenge with the id : "
                        + challengeId +" was not found"));
        existingChallenge.setDateStart(challenge.getDateStart());
        existingChallenge.setDateEnd(challenge.getDateEnd());
        existingChallenge.setDescription(challenge.getDescription());
        existingChallenge.setTypeSport(challenge.getTypeSport());
        existingChallenge.setCollaboratorChallenger(challenge.getCollaboratorChallenger());
        existingChallenge.setCollaboratorChallenged(challenge.getCollaboratorChallenged());
        existingChallenge.setWorkout(challenge.getWorkout());
        existingChallenge.setAchieved(challenge.isAchieved());
        return challengeRepository.save(existingChallenge);
    }

    public void deleteChallenge(Challenge challenge) {
        challengeRepository.delete(challenge);
    }
}
