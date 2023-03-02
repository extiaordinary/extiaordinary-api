package fr.esgi.extiaordinaryapi.service;

import fr.esgi.extiaordinaryapi.entity.Seance;
import fr.esgi.extiaordinaryapi.entity.User;
import fr.esgi.extiaordinaryapi.repository.SeanceRepository;
import fr.esgi.extiaordinaryapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class SeanceService {

    private final int MAX_POINT = 445;

    private final SeanceRepository seanceRepository;

    private final UserRepository userRepository;

    public Seance createSeance(Seance seance) {
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

    public Seance updateSeance(Seance seance, User user) {
        if (seance.getRewardPoint() > MAX_POINT) {
            throw new IllegalArgumentException("Reward point is too high");
        }
        val findSeance = seanceRepository.findById(seance.getSeanceId());
        if (findSeance.isEmpty()) {
            throw new IllegalArgumentException("Seance not found");
        }
        if(findSeance.get().getCoach().getId() != user.getId()) {
            throw new IllegalArgumentException("User is not coach");
        }
        val localDateTime = LocalDateTime.now();
        if (findSeance.get().getDateStart().isBefore(localDateTime)) {
            throw new IllegalArgumentException("Seance is already started");
        }
        return createSeance(
                Seance.builder()
                        .seanceId(seance.getSeanceId())
                        .name(seance.getName())
                        .description(seance.getDescription())
                        .dateStart(seance.getDateStart())
                        .dateEnd(seance.getDateEnd())
                        .rewardPoint(seance.getRewardPoint())
                        .creationDate(findSeance.get().getCreationDate())
                        .coach(seance.getCoach())
                        .build()
        );
    }

    public Seance getSeanceById(UUID id) {
        val findSeance = seanceRepository.findById(id);
        if (findSeance.isEmpty()) {
            throw new IllegalArgumentException("Seance not found");
        }
        return findSeance.get();
    }

    public List<Seance> getSeances() {
        return seanceRepository.findAll();
    }

    public void deleteSeance(UUID id, User user) {
        val findSeance = seanceRepository.findById(id);
        if (findSeance.isEmpty()) {
            throw new IllegalArgumentException("Seance not found");
        }
        if (findSeance.get().getCoach().getId() != user.getId()) {
            throw new IllegalArgumentException("User is not coach");
        }
        val localDateTime = LocalDateTime.now();
        if (findSeance.get().getDateStart().isBefore(localDateTime)) {
            throw new IllegalArgumentException("Seance is already started");
        }
        try {
            seanceRepository.delete(findSeance.get());
        } catch (Exception e) {
            log.error("Error while deleting seance", e);
            throw new RuntimeException("Error while deleting seance", e);
        }
    }

    public String addUserToSeance(UUID idSeance, User user) {
        val findSeance = seanceRepository.findById(idSeance);
        if (findSeance.isEmpty()) {
            throw new IllegalArgumentException("Seance not found");
        }
        if (findSeance.get().getCoach().getId() == user.getId()) {
            throw new IllegalArgumentException("User is coach");
        }
        val localDateTime = LocalDateTime.now();
        if (findSeance.get().getDateStart().isBefore(localDateTime)) {
            throw new IllegalArgumentException("Seance is already started");
        }
        try {
            val addSeanceToUser = user.getSeancesPlayed();
            addSeanceToUser.add(findSeance.get());
            userRepository.save(User.builder()
                    .id(user.getId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .dateOfBirth(user.getDateOfBirth())
                    .points(user.getPoints())
                    .role(user.getRole())
                    .seancesPlayed(addSeanceToUser)
                    .build());
            return "User added to seance";
        } catch (Exception e) {
            log.error("Error while adding seance to user", e);
            throw new RuntimeException("Error while adding seance to user", e);
        }
    }
}
