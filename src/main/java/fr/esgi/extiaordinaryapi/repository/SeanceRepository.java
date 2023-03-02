package fr.esgi.extiaordinaryapi.repository;

import fr.esgi.extiaordinaryapi.entity.Seance;
import fr.esgi.extiaordinaryapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, UUID> {

    List<Seance> findAllByCoach(User coach);
    
}
