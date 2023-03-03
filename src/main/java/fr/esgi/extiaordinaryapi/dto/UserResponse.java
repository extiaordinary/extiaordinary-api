package fr.esgi.extiaordinaryapi.dto;

import java.util.UUID;

public record UserResponse(

        UUID id,
        String firstName,
        String lastName,
        String email
) {
}
