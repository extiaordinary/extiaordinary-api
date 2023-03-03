package fr.esgi.extiaordinaryapi.dto;

import java.util.UUID;

public record ChallengeResponse(
     UUID challengeId,
     String dateStart,
     String dateEnd,
     String description,
     String typeSport,
     UserResponse collaboratorChallenger,
     UserResponse collaboratorChallenged,
     UUID seance,
     Boolean isAchieved,
     String tag
) { }
