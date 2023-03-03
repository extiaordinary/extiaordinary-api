package fr.esgi.extiaordinaryapi.service;

import fr.esgi.extiaordinaryapi.dto.ChallengeResponse;
import fr.esgi.extiaordinaryapi.dto.CreateChallengeRequest;
import fr.esgi.extiaordinaryapi.dto.UpdateChallengeRequest;
import fr.esgi.extiaordinaryapi.entity.Challenge;
import fr.esgi.extiaordinaryapi.entity.TAG;
import fr.esgi.extiaordinaryapi.entity.User;
import fr.esgi.extiaordinaryapi.exception.ChallengeException;
import fr.esgi.extiaordinaryapi.repository.ChallengeRepository;
import fr.esgi.extiaordinaryapi.repository.SeanceRepository;
import fr.esgi.extiaordinaryapi.utils.ChallengeInitializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static fr.esgi.extiaordinaryapi.utils.ChallengeInitializer.updateStateChallenge;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final UserService userService;
    private final SeanceService seanceService;
    private final SeanceRepository seanceRepository;


    public List<ChallengeResponse> findOwnChallenge(UUID collaboratorChallengedId) {
        List<Challenge> challenge = challengeRepository.findByCollaboratorChallenger_UserId(collaboratorChallengedId);
        return challenge.stream().map(ChallengeInitializer::mapToChallenge).toList();
    }

    public List<ChallengeResponse> findToDoChallenge() {
        User collaboratorChallenged = userService.getCurrentUser();
        List<Challenge> challenge = challengeRepository.findByCollaboratorChallenged_UserId(collaboratorChallenged.getUserId());
        return challenge.stream().map(ChallengeInitializer::mapToChallenge).toList();
    }

    public ChallengeResponse acceptChallenge(UUID challengeId) {
        User collaboratorChallenged = userService.getCurrentUser();
        if (collaboratorChallenged.getSeancesPlayed().size() > 0){
            collaboratorChallenged.setPoints(collaboratorChallenged.getPoints()+1);
            collaboratorChallenged = userService.save(collaboratorChallenged);
        }
        Challenge foundChallenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> ChallengeException.notFoundAccountId(challengeId));
        foundChallenge.setCollaboratorChallenged(collaboratorChallenged);
        return ChallengeInitializer.mapToChallenge(challengeRepository.save(foundChallenge));
    }

    public ChallengeResponse createChallenge(CreateChallengeRequest createChallengeRequest) {
        val userChallenger = userService.getCurrentUser();
        val workout = seanceRepository.findById(createChallengeRequest.workout());
        if (workout.isEmpty()) throw new ChallengeException("Seance not assigne to this challenge");

        Challenge challenge = Challenge.builder()
                .dateStart(createChallengeRequest.dateStart())
                .dateEnd(createChallengeRequest.dateEnd())
                .description(createChallengeRequest.description())
                .typeSport(createChallengeRequest.typeSport())
                .collaboratorChallenger(userChallenger)
                .collaboratorChallenged(null)
                .workout(workout.get())
                .isAchieved(createChallengeRequest.isAchieved())
                .tag(TAG.valueOf(createChallengeRequest.tag()))
                .build();
        return ChallengeInitializer.mapToChallenge(challengeRepository.save(challenge));
    }

    public ChallengeResponse findById(UUID challengeId) {
        return challengeRepository
                .findChallengeByChallengeId(challengeId)
                .map(ChallengeInitializer::mapToChallenge)
                .orElseThrow(() -> ChallengeException.notFoundAccountId(challengeId));
    }

    public List<ChallengeResponse> findAll() {
        return challengeRepository.findAll().stream().map(ChallengeInitializer::mapToChallenge).toList();
    }

    public ChallengeResponse updateChallenge(UpdateChallengeRequest updateChallengeRequest) {
        val workout = seanceRepository.findById(updateChallengeRequest.workout());
        if (workout.isEmpty()) throw new ChallengeException("Seance not assigne to this challenge");
        Challenge challenge =
                Challenge.builder()
                        .challengeId(updateChallengeRequest.challengeId())
                        .dateStart(updateChallengeRequest.dateStart())
                        .dateEnd(updateChallengeRequest.dateEnd())
                        .description(updateChallengeRequest.description())
                        .typeSport(updateChallengeRequest.typeSport())
                        .collaboratorChallenger(updateChallengeRequest.collaboratorChallenger())
                        .collaboratorChallenged(updateChallengeRequest.collaboratorChallenged())
                        .workout(workout.get())
                        .isAchieved(updateChallengeRequest.isAchieved())
                        .build();
        UUID challengeId = challenge.getChallengeId();
        var existingChallenge = challengeRepository
                .findChallengeByChallengeId(challengeId)
                .orElseThrow(() -> ChallengeException.notFoundAccountId(challengeId));

        updateStateChallenge(challenge, existingChallenge);
        return ChallengeInitializer.mapToChallenge(challengeRepository.save(existingChallenge));
    }

    public void deleteChallenge(UUID challengeId) {
        Challenge existingChallenge = challengeRepository
                .findChallengeByChallengeId(challengeId)
                .orElseThrow(() -> ChallengeException.notFoundAccountId(challengeId));
        challengeRepository.delete(existingChallenge);
    }
}
