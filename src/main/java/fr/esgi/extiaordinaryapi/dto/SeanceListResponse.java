package fr.esgi.extiaordinaryapi.dto;

public record SeanceListResponse(
        String id,
        String name,
        String description,
        int rewardPoint,
        String dateStart,
        String dateEnd,
        String image,

        UserResponse coach


) { }
