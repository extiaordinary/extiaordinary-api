package fr.esgi.extiaordinaryapi.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "T_CLASSEMENT")
public class Classement {

    @Id
    @Column(
            name = "idClassement",
            nullable = false,
            updatable = false,
            unique = true
    )
    private UUID idClassement;
    @OneToMany
    @Column(
            name = "topRatedUsers",
            unique = true
    )
    private Set<User> topRatedUsers;
}
