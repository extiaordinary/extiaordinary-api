package fr.esgi.extiaordinaryapi.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public record CreateChallengeDto(
        @NotNull LocalDateTime dateStart,
        @NotNull LocalDateTime dateEnd,
        @NotNull String description,
        @NotNull String typeSport,
        @NotNull UUID collaboratorChallenger,
        @NotNull UUID collaboratorChallenged,
        @NotNull UUID workout,
        @NotNull boolean isAchieved
) {
}
