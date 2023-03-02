package fr.esgi.extiaordinaryapi.service;

import fr.esgi.extiaordinaryapi.entity.Classement;
import fr.esgi.extiaordinaryapi.repository.ClassementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClassementService {

    private final ClassementRepository classementRepository;

    public Classement getClassement(UUID classementId) {
        return classementRepository.findById(classementId)
                .orElseThrow(() -> new RuntimeException("The classement was not found"));
    }

}
