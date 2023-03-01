package fr.esgi.extiaordinaryapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "T_SEANCE")
public class SeanceEntity{

    @jakarta.persistence.Id
    @Id @EqualsAndHashCode.Include
    private UUID id;

    @NotNull
    @JsonProperty("date_start")
    private LocalDateTime dateStart;

    @NotNull
    @JsonProperty("date_end")
    private LocalDateTime dateEnd;

    @NotNull
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("description")
    private String description;

    @NotNull
    @Builder.Default
    private UUID coachId = UUID.randomUUID();

    @NotNull
    @Builder.Default
    @JsonProperty("reward_point")
    private int rewardPoint = 10;

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
