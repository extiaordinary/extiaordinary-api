package fr.esgi.extiaordinaryapi.dto;

import fr.esgi.extiaordinaryapi.entity.Role;
import fr.esgi.extiaordinaryapi.entity.Seance;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record UserResponse(

        UUID id,
        String firstName,
        String lastName,
        String email,
        LocalDate dateOfBirth,
        int points,
        String role
) {
}
