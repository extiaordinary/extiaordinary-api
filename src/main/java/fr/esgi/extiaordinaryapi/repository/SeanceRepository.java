package fr.esgi.extiaordinaryapi.repository;

import fr.esgi.extiaordinaryapi.entity.SeanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Repository
public interface SeanceRepository extends JpaRepository<SeanceEntity, UUID> {
}
