package fr.esgi.extiaordinaryapi.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "T_CHALLENGE")
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "challengeId",
            unique = true,
            updatable = false
    )
    @NotNull
    private UUID challengeId;
    @Column(name = "dateStart")
    @NotNull
    private LocalDateTime dateStart;
    @Column(name = "dateEnd")
    @NotNull
    private LocalDateTime dateEnd;
    @Column(name = "description")
    @NotNull
    private String description;
    @Column(name = "typeSport")
    @NotNull
    private String typeSport;
    @Column(name = "collaboratorChallenger")
    @NotNull
    private UUID collaboratorChallenger;
    @Column(name = "collaboratorChallenged")
    @NotNull
    private UUID collaboratorChallenged;
    @Column(name = "workout")
    @NotNull
    private UUID workout;
    @Column(name = "achieved")
    @NotNull
    private Boolean isAchieved;
}
