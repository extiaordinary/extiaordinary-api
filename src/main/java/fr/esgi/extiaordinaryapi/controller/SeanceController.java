package fr.esgi.extiaordinaryapi.controller;

import fr.esgi.extiaordinaryapi.entity.SeanceEntity;
import fr.esgi.extiaordinaryapi.service.SeanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/seance")
public class SeanceController {

    private final SeanceService seanceService;

    @PostMapping
    public ResponseEntity<Object> createSeance(@RequestBody @Valid SeanceEntity seanceEntity) {
        try {
            return ResponseEntity.ok(seanceService.createSeance(seanceEntity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateSeance(@RequestBody @Valid SeanceEntity seanceEntity) {
        try {
            return ResponseEntity.ok(seanceService.updateSeance(seanceEntity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getSeance() {
        try {
            return ResponseEntity.ok(seanceService.getSeances());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSeanceById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(seanceService.getSeanceById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSeance(@PathVariable UUID id) {
        try {
            seanceService.deleteSeance(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
