package fr.esgi.extiaordinaryapi.repository;

import fr.esgi.extiaordinaryapi.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, UUID> {

    Optional<Challenge> findChallengeByChallengeId(UUID challengeId);
}
