package fr.esgi.extiaordinaryapi.repository;

import fr.esgi.extiaordinaryapi.entity.Seance;
import fr.esgi.extiaordinaryapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    List<User> findAllBySeancesPlayed(Seance seance);

    @Query("SELECT u FROM User u ORDER BY u.points DESC")
    List<User> findAllUsersOrderByPointsDesc();


}
