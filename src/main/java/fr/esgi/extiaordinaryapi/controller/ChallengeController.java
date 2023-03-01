package fr.esgi.extiaordinaryapi.controller;

import fr.esgi.extiaordinaryapi.dto.CreateChallengeRquest;
import fr.esgi.extiaordinaryapi.dto.UpdateChallengeRequest;
import fr.esgi.extiaordinaryapi.entity.Challenge;
import fr.esgi.extiaordinaryapi.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/challenge")
public class ChallengeController {

    final ChallengeService challengeService;

    @PostMapping
    public Challenge createChallenge(@RequestBody @Valid CreateChallengeRquest createChallengeRquest) {
        return challengeService.createChallenge(
                Challenge.builder()
                        .dateStart(createChallengeRquest.dateStart())
                        .dateEnd(createChallengeRquest.dateEnd())
                        .description(createChallengeRquest.description())
                        .typeSport(createChallengeRquest.typeSport())
                        .collaboratorChallenger(createChallengeRquest.collaboratorChallenger())
                        .collaboratorChallenged(createChallengeRquest.collaboratorChallenged())
                        .workout(createChallengeRquest.workout())
                        .isAchieved(createChallengeRquest.isAchieved())
                        .build());
    }

    @GetMapping(path = "/{idChallenge}")
    public Challenge findChallengeById(@PathVariable @Valid UUID idChallenge) {
        return challengeService.findById(idChallenge);
    }

    @GetMapping
    public List<Challenge> findAllChallenge() {
        return challengeService.findAll();
    }

    @PutMapping
    public Challenge updateChallenge(@RequestBody @Valid UpdateChallengeRequest updateChallengeRequest) {
        return challengeService.updateChallenge(
                Challenge.builder()
                        .challengeId(updateChallengeRequest.challengeId())
                        .dateStart(updateChallengeRequest.dateStart())
                        .dateEnd(updateChallengeRequest.dateEnd())
                        .description(updateChallengeRequest.description())
                        .typeSport(updateChallengeRequest.typeSport())
                        .collaboratorChallenger(updateChallengeRequest.collaboratorChallenger())
                        .collaboratorChallenged(updateChallengeRequest.collaboratorChallenged())
                        .workout(updateChallengeRequest.workout())
                        .isAchieved(updateChallengeRequest.isAchieved())
                        .build());
    }

    @DeleteMapping(path = "/{challengeId}")
    public void deleteChallenge(@PathVariable @Valid UUID challengeId) {
        challengeService.deleteChallenge(challengeId);
    }
}
