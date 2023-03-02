package fr.esgi.extiaordinaryapi.dto;

import fr.esgi.extiaordinaryapi.entity.User;

import java.util.List;
import java.util.Set;

public record SeanceResponse(
        String id,
        String name,
        String description,
        int rewardPoint,
        String dateStart,
        String dateEnd,

        UserResponse coach,

        List<UserResponse> participants
){}
