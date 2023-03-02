package fr.esgi.extiaordinaryapi.controller;

import fr.esgi.extiaordinaryapi.dto.CreateChallengeRequest;
import fr.esgi.extiaordinaryapi.dto.UpdateChallengeRequest;
import fr.esgi.extiaordinaryapi.entity.Challenge;
import fr.esgi.extiaordinaryapi.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/challenge")
public class ChallengeController {

    private final ChallengeService challengeService;

    @PostMapping(path = "/add")
    public ResponseEntity<Object> createChallenge(@RequestBody @Valid CreateChallengeRequest createChallengeRequest) {
        try {
            return ResponseEntity.ok(challengeService.createChallenge(
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
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping(path = "/{idChallenge}")
    public ResponseEntity<Object> findChallengeById(@PathVariable @Valid UUID idChallenge) {
        try {
            return ResponseEntity.ok(challengeService.findById(idChallenge));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAllChallenge() {
        try {
            return ResponseEntity.ok(challengeService.findAll());
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateChallenge(@RequestBody @Valid UpdateChallengeRequest updateChallengeRequest) {
        try {
            return ResponseEntity.ok(challengeService.updateChallenge(
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
                            .build()));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping(path = "/delete/{challengeId}")
    public ResponseEntity<String> deleteChallenge(@PathVariable @Valid UUID challengeId) {
        try {
            challengeService.deleteChallenge(challengeId);
            return ResponseEntity.ok("The user with the id : %s has been deleted !"
                    .formatted(challengeId));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
