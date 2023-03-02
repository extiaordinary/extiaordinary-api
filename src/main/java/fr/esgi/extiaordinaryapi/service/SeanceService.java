package fr.esgi.extiaordinaryapi.service;

import fr.esgi.extiaordinaryapi.entity.SeanceEntity;
import fr.esgi.extiaordinaryapi.repository.SeanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class SeanceService {

    private final int MAX_POINT = 445;

    private final SeanceRepository seanceRepository;

    public SeanceEntity createSeance(SeanceEntity seance) {
        try {
            if (seance.getRewardPoint() > MAX_POINT) {
                throw new IllegalArgumentException("Reward point is too high");
            }
            if (seance.getDateStart().isAfter(seance.getDateEnd())) {
                throw new IllegalArgumentException("Date start is after date end");
            }
            if (seance.getDateStart().isAfter(LocalDateTime.now())) {
                throw new IllegalArgumentException("Date start is before now");
            }
            return seanceRepository.save(seance);
        } catch (Exception e) {
            log.error("Error while creating seance", e);
            throw new RuntimeException("Error while creating seance", e);
        }
    }

    public SeanceEntity updateSeance(SeanceEntity seance) {
        if (seance.getRewardPoint() > MAX_POINT) {
            throw new IllegalArgumentException("Reward point is too high");
        }
        val findSeance = seanceRepository.findById(seance.getSeanceId());
        if (findSeance.isEmpty()) {
            throw new IllegalArgumentException("Seance not found");
        }
        return createSeance(
                SeanceEntity.builder()
                        .seanceId(seance.getSeanceId())
                        .name(seance.getName())
                        .description(seance.getDescription())
                        .dateStart(seance.getDateStart())
                        .dateEnd(seance.getDateEnd())
                        .rewardPoint(seance.getRewardPoint())
                        .coachId(seance.getCoachId())
                        .creationDate(findSeance.get().getCreationDate())
                        .build()
        );
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
