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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "collaboratorChallenger")
    private User collaboratorChallenger;

    @ManyToOne
    @JoinColumn(name = "collaboratorChallenged")
    private User collaboratorChallenged;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "seanceId")
    private Seance workout;

    @Column(name = "achieved")
    @NotNull
    private Boolean isAchieved;

    @Column(name = "tag")
    @NotNull
    private TAG tag;
}
