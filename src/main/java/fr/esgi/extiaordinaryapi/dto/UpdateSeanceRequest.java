package fr.esgi.extiaordinaryapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record UpdateSeanceRequest(

        @NotNull
        @JsonProperty("seanceId")
        String id,

        @JsonProperty("name")
        String name,

        @JsonProperty("description")
        String description,

        @JsonProperty("rewardPoint")
        int rewardPoint,

        @JsonProperty("dateStart")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime dateStart,

        @JsonProperty("dateEnd")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime dateEnd,

        @JsonProperty("coach")
        String coachId
) { }

