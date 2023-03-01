package fr.esgi.extiaordinaryapi.controller;

import fr.esgi.extiaordinaryapi.dto.CreateChallengeDto;
import fr.esgi.extiaordinaryapi.entity.Challenge;
import fr.esgi.extiaordinaryapi.service.ChallengeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class ChallengeController {

    final ChallengeService challengeService;

    @PostMapping
    public Challenge createChallenge(@RequestBody @Valid CreateChallengeDto createChallengeDto) {
            return challengeService.createChallenge(
                            Challenge.builder()
                                    .dateStart(createChallengeDto.dateStart())
                                    .dateEnd(createChallengeDto.dateEnd())
                                    .description(createChallengeDto.description())
                                    .typeSport(createChallengeDto.typeSport())
                                    .collaboratorChallenger(createChallengeDto.collaboratorChallenger())
                                    .collaboratorChallenged(createChallengeDto.collaboratorChallenged())
                                    .workout(createChallengeDto.workout())
                                    .isAchieved(createChallengeDto.isAchieved())
                                    .build());
    }

    @GetMapping
    public String test() {
        return "test";
    }
}
