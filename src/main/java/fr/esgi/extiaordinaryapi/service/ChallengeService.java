package fr.esgi.extiaordinaryapi.service;

import fr.esgi.extiaordinaryapi.entity.Challenge;
import fr.esgi.extiaordinaryapi.exception.ChallengeException;
import fr.esgi.extiaordinaryapi.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static fr.esgi.extiaordinaryapi.utils.ChallengeInitializer.updateStateChallenge;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChallengeService {

    private final ChallengeRepository challengeRepository;

    public Challenge createChallenge(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    public Challenge findById(UUID challengeId) {
        return challengeRepository
                .findChallengeByChallengeId(challengeId)
                .orElseThrow(() -> ChallengeException.notFoundAccountId(challengeId));
    }

    public List<Challenge> findAll() {
        return challengeRepository.findAll();
    }

    public Challenge updateChallenge(Challenge challenge) {
        UUID challengeId = challenge.getChallengeId();
        var existingChallenge = challengeRepository
                .findChallengeByChallengeId(challengeId)
                .orElseThrow(() -> ChallengeException.notFoundAccountId(challengeId));

        updateStateChallenge(challenge, existingChallenge);
        return challengeRepository.save(existingChallenge);
    }


    public void deleteChallenge(UUID challengeId) {
        Challenge existingChallenge = challengeRepository
                .findChallengeByChallengeId(challengeId)
                .orElseThrow(() -> ChallengeException.notFoundAccountId(challengeId));
        challengeRepository.delete(existingChallenge);
    }
}
