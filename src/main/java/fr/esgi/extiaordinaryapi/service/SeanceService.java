package fr.esgi.extiaordinaryapi.service;

import fr.esgi.extiaordinaryapi.entity.SeanceEntity;
import fr.esgi.extiaordinaryapi.repository.SeanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class SeanceService {

    private static final int MAX_POINT = 445;

    private final SeanceRepository seanceRepository;

    public SeanceEntity createSeance(SeanceEntity seance) {

        try {
            return seanceRepository.save(seance);
        } catch (Exception e) {
            log.error("Error while creating seance", e);
            throw new RuntimeException("Error while creating seance", e);
        }
    }

    public SeanceEntity updateSeance(SeanceEntity seance) {
        val findSeance = seanceRepository.findById(seance.getId());
        if (findSeance.isEmpty()) {
            throw new IllegalArgumentException("Seance not found");
        }
        return createSeance(seance);
    }

    public SeanceEntity getSeanceById(UUID id) {
        val findSeance = seanceRepository.findById(id);
        if (findSeance.isEmpty()) {
            throw new IllegalArgumentException("Seance not found");
        }
        return findSeance.get();
    }

    public List<SeanceEntity> getSeances() {
        return seanceRepository.findAll();
    }

    public void deleteSeance(UUID id) {
        val findSeance = seanceRepository.findById(id);
        if (findSeance.isEmpty()) {
            throw new IllegalArgumentException("Seance not found");
        }
        try {
            seanceRepository.delete(findSeance.get());
        } catch (Exception e) {
            log.error("Error while deleting seance", e);
            throw new RuntimeException("Error while deleting seance", e);
        }
    }



}
