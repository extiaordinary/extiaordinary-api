package fr.esgi.extiaordinaryapi.controller;

import fr.esgi.extiaordinaryapi.entity.Classement;
import fr.esgi.extiaordinaryapi.service.ClassementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/classement")
@CrossOrigin(origins = "http://localhost:4200/")
public class ClassementController {

    private final ClassementService classementService;

    @GetMapping(path = "/{classementId}")
    public Classement getClassementById(@PathVariable UUID classementId) {
        return classementService.getClassement(classementId);
    }

}
