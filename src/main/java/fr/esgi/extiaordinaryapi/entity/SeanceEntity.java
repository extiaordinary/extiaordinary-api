package fr.esgi.extiaordinaryapi.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "T_Seance")
public class SeanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, updatable = false)
    @NotNull
    private UUID seanceId;

    @Column(nullable = false)
    private LocalDateTime dateStart;

    @Column(nullable = false)
    private LocalDateTime dateEnd;

    @Column(nullable = false, unique = true)
    private String name;


    @Column(nullable = false)
    private String description;


    @Column(nullable = false)
    private UUID coachId;


    @Column(nullable = false)
    private int rewardPoint;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;
}