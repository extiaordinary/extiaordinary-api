package fr.esgi.extiaordinaryapi.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.esgi.extiaordinaryapi.entity.User;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@JsonAutoDetect(fieldVisibility = ANY)
public record UpdateChallengeRequest(
        @NotNull
        @JsonProperty("challengeId")
        UUID challengeId,
        @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd'T'HH:mm")
        @NotNull
        @JsonProperty("dateStart")
        LocalDateTime dateStart,
        @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd'T'HH:mm")
        @NotNull
        @JsonProperty("dateEnd")
        LocalDateTime dateEnd,
        @NotNull
        @JsonProperty("description")
        String description,
        @NotNull
        @JsonProperty("typeSport")
        String typeSport,
        @NotNull
        @JsonProperty("collaboratorChallenger")
        User collaboratorChallenger,
        @NotNull
        @JsonProperty("collaboratorChallenged")
        User collaboratorChallenged,
        @NotNull
        @JsonProperty("workout")
        UUID workout,
        @NotNull
        @JsonProperty("isAchieved")
        boolean isAchieved
) {
}
