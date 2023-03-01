package fr.esgi.extiaordinaryapi.entity;

import lombok.*;

import javax.persistence.*;
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
    private UUID challengeId;
    @Column(name = "dateStart")
    private LocalDateTime dateStart;
    @Column(name = "dateEnd")
    private LocalDateTime dateEnd;
    @Column(name = "description")
    private String description;
    @Column(name = "typeSport")
    private String typeSport;
    @Column(name = "collaboratorChallenger")
    private UUID collaboratorChallenger;
    @Column(name = "collaboratorChallenged")
    private UUID collaboratorChallenged;
    @Column(name = "workout")
    private UUID workout;
    @Column(name = "achieved")
    private boolean isAchieved;
}
