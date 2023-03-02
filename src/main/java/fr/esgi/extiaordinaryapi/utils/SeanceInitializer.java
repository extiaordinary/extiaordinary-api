package fr.esgi.extiaordinaryapi.utils;

import fr.esgi.extiaordinaryapi.dto.SeanceListResponse;
import fr.esgi.extiaordinaryapi.dto.SeanceResponse;
import fr.esgi.extiaordinaryapi.entity.Seance;
import fr.esgi.extiaordinaryapi.entity.User;

import java.util.List;
import java.util.Set;

public interface SeanceInitializer {

    static SeanceResponse updateSeance(Seance seance, List<User> participants){
        return new SeanceResponse(
                seance.getSeanceId().toString(),
                seance.getName(),
                seance.getDescription(),
                seance.getRewardPoint(),
                seance.getDateStart().toString(),
                seance.getDateEnd().toString(),
                UserInitializer.mappingUserResponse(seance.getCoach()),
                seance.getImage().toString(),
                participants.stream()
                        .map(UserInitializer::mappingUserResponse)
                        .collect(java.util.stream.Collectors.toList())
        );

    }

    static SeanceResponse updateSeance(Seance seance, Set<User> participants){
        return new SeanceResponse(
                seance.getSeanceId().toString(),
                seance.getName(),
                seance.getDescription(),
                seance.getRewardPoint(),
                seance.getDateStart().toString(),
                seance.getDateEnd().toString(),
                UserInitializer.mappingUserResponse(seance.getCoach()),
                seance.getImage().toString(),
                participants.stream()
                        .map(UserInitializer::mappingUserResponse)
                        .collect(java.util.stream.Collectors.toList())
        );

    }

    static List<SeanceListResponse> listSeanceResponse(List<Seance> seances){
        return seances.stream()
                .map(seance -> new SeanceListResponse(
                        seance.getSeanceId().toString(),
                        seance.getName(),
                        seance.getDescription(),
                        seance.getRewardPoint(),
                        seance.getDateStart().toString(),
                        seance.getDateEnd().toString(),
                        seance.getImage().toString(),
                        UserInitializer.mappingUserResponse(seance.getCoach())
                ))
                .collect(java.util.stream.Collectors.toList());
    }
}
