package fr.esgi.extiaordinaryapi.repository;

import fr.esgi.extiaordinaryapi.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, UUID> {
    @Query("select c from Challenge c where c.collaboratorChallenged.userId = ?1")
    List<Challenge> findByCollaboratorChallenged_UserId(UUID userId);

    @Query("select c from Challenge c where c.collaboratorChallenger.userId = ?1")
    List<Challenge> findByCollaboratorChallenger_UserId(UUID userId);

    Optional<Challenge> findChallengeByChallengeId(UUID challengeId);
}
