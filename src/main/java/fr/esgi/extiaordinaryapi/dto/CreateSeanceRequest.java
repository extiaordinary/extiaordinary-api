package fr.esgi.extiaordinaryapi.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URI;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@JsonAutoDetect(fieldVisibility = ANY)
public record CreateSeanceRequest(
        @JsonProperty("name")
        String name,
        @JsonProperty("description")
        String description,
        @JsonProperty("rewardPoint")
        int rewardPoint,
        @JsonProperty("dateStart")
        @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime dateStart,
        @JsonProperty("dateEnd")
        @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime dateEnd,
        @JsonProperty("coach")
        String coachId,

        @JsonProperty("image")
        URI image
) {
}
