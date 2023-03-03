package fr.esgi.extiaordinaryapi.controller;

import fr.esgi.extiaordinaryapi.dto.CreateChallengeRequest;
import fr.esgi.extiaordinaryapi.dto.UpdateChallengeRequest;
import fr.esgi.extiaordinaryapi.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/challenge")
@CrossOrigin(origins = "http://localhost:4200")
public class ChallengeController {

    private final ChallengeService challengeService;

    @GetMapping(path = "/ownChallenges/{collaboratorChallengerId}")
    public ResponseEntity<Object> ownChallenges(@PathVariable @Valid UUID collaboratorChallengerId) {
        try {
            return ResponseEntity.ok(challengeService.findOwnChallenge(collaboratorChallengerId));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping(path = "/toDoChallenges")
    public ResponseEntity<Object> toDoChallenges() {
        try {
            return ResponseEntity.ok(challengeService.findToDoChallenge());
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PostMapping(path = "/accept/{challengeId}")
    public ResponseEntity<Object> acceptChallenge(@PathVariable @Valid UUID challengeId) {
        try {
            return ResponseEntity.ok(challengeService.acceptChallenge(challengeId));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PostMapping(path = "/achieve/{challengeId}")
    public ResponseEntity<Object> achieveChallenge(@PathVariable @Valid UUID challengeId) {
        try {
            return ResponseEntity.ok(challengeService.achieveChallenge(challengeId));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Object> createChallenge(@RequestBody @Valid CreateChallengeRequest createChallengeRequest) {
        try {
            return ResponseEntity.ok(challengeService.createChallenge(createChallengeRequest));
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
            return ResponseEntity.ok(challengeService.updateChallenge(updateChallengeRequest));
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
