package fr.esgi.extiaordinaryapi.controller;

import fr.esgi.extiaordinaryapi.dto.CreateChallengeRequest;
import fr.esgi.extiaordinaryapi.entity.Challenge;
import fr.esgi.extiaordinaryapi.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/challenge")
public class ChallengeController {

    private final ChallengeService challengeService;

    @PostMapping(path = "/add")
    public ResponseEntity<Object> createChallenge(CreateChallengeRequest createChallengeRequest) {
            return ResponseEntity.ok(
                    challengeService.createChallenge(
                            Challenge.builder()
                                    .dateStart(createChallengeRequest.dateStart())
                                    .dateEnd(createChallengeRequest.dateEnd())
                                    .description(createChallengeRequest.description())
                                    .typeSport(createChallengeRequest.typeSport())
                                    .collaboratorChallenger(createChallengeRequest.collaboratorChallenger())
                                    .collaboratorChallenged(createChallengeRequest.collaboratorChallenged())
                                    .workout(createChallengeRequest.workout())
                                    .isAchieved(createChallengeRequest.isAchieved())
                                    .build()));
    }
}
