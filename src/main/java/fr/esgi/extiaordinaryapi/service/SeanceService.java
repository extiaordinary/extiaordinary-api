package fr.esgi.extiaordinaryapi.service;

import fr.esgi.extiaordinaryapi.dto.SeanceListResponse;
import fr.esgi.extiaordinaryapi.dto.SeanceResponse;
import fr.esgi.extiaordinaryapi.entity.Seance;
import fr.esgi.extiaordinaryapi.entity.User;
import fr.esgi.extiaordinaryapi.repository.SeanceRepository;
import fr.esgi.extiaordinaryapi.repository.UserRepository;
import fr.esgi.extiaordinaryapi.utils.SeanceInitializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
            if (seance.getDateStart().isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException("Date start is before now");
            }
            val seanceSave = seanceRepository.save(seance);
            return seanceSave;
        } catch (Exception e) {
            log.error("Error while creating seance", e);
            throw new RuntimeException("Error while creating seance", e);
        }
    }

    public SeanceResponse updateSeance(Seance seance, User user) {
        if (seance.getRewardPoint() > MAX_POINT) {
            throw new IllegalArgumentException("Reward point is too high");
        }
        val findSeance = seanceRepository.findById(seance.getSeanceId());
        findSeance(user, findSeance);
        val seanceUpdated = createSeance(
                Seance.builder()
                        .seanceId(seance.getSeanceId())
                        .name(seance.getName())
                        .description(seance.getDescription())
                        .dateStart(seance.getDateStart())
                        .dateEnd(seance.getDateEnd())
                        .rewardPoint(seance.getRewardPoint())
                        .creationDate(findSeance.get().getCreationDate())
                        .image(seance.getImage())
                        .coach(seance.getCoach())
                        .build()
        );
        List<User> userPlay = userRepository.findAllBySeancesPlayed(Seance.builder().seanceId(seance.getSeanceId()).build());
        return SeanceInitializer.updateSeance(seanceUpdated, userPlay);
    }

    private void findSeance(User user, Optional<Seance> findSeance) {
        if (findSeance.isEmpty()) {
            throw new IllegalArgumentException("Seance not found");
        }
        if (findSeance.get().getCoach().getUserId() != user.getUserId()) {
            throw new IllegalArgumentException("User is not coach");
        }
        val localDateTime = LocalDateTime.now();
        if (findSeance.get().getDateStart().isBefore(localDateTime)) {
            throw new IllegalArgumentException("Seance is already started");
        }
    }

    public SeanceResponse getSeanceById(UUID id) {
        val findSeance = seanceRepository.findById(id);
        if (findSeance.isEmpty()) {
            throw new IllegalArgumentException("Seance not found");
        }
        List<User> userPlay = userRepository.findAllBySeancesPlayed(Seance.builder().seanceId(id).build());
        return SeanceInitializer.updateSeance(findSeance.get(), userPlay);
    }

    public List<SeanceListResponse> getSeances() {
        val seances = seanceRepository.findAll();
        return SeanceInitializer.listSeanceResponse(seances);
    }

    public void deleteSeance(UUID id, User user) {
        val findSeance = seanceRepository.findById(id);
        findSeance(user, findSeance);
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
        if (findSeance.get().getCoach().getUserId() == user.getUserId()) {
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
                    .userId(user.getUserId())
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
