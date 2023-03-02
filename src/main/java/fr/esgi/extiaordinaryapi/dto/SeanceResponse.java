package fr.esgi.extiaordinaryapi.dto;

import java.util.List;

public record SeanceResponse(
        String id,
        String name,
        String description,
        int rewardPoint,
        String dateStart,
        String dateEnd,

        UserResponse coach,

        String image,

        List<UserResponse> participants
) {
}
