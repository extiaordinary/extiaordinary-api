package fr.esgi.extiaordinaryapi.controller;

import fr.esgi.extiaordinaryapi.dto.CreateSeanceRequest;
import fr.esgi.extiaordinaryapi.dto.UpdateSeanceRequest;
import fr.esgi.extiaordinaryapi.entity.Seance;
import fr.esgi.extiaordinaryapi.service.SeanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/seance")
public class SeanceController {

    private final SeanceService seanceService;

    @PostMapping(path = "/add")
    public ResponseEntity<Object> createSeance(@RequestBody @Valid CreateSeanceRequest seanceDto) {
        try {
            return ResponseEntity.ok(
                    seanceService.createSeance(
                            Seance.builder()
                                    .name(seanceDto.name())
                                    .description(seanceDto.description())
                                    .dateStart(seanceDto.dateStart())
                                    .dateEnd(seanceDto.dateEnd())
                                    .rewardPoint(seanceDto.rewardPoint())
                                    .coachId(UUID.fromString(seanceDto.coachId()))
                                    .build()
                    ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Object> updateSeance(@RequestBody @Valid UpdateSeanceRequest seanceDto) {
        try {
            return ResponseEntity.ok(
                    seanceService.updateSeance(
                            Seance.builder()
                                    .seanceId(UUID.fromString(seanceDto.id()))
                                    .name(seanceDto.name())
                                    .description(seanceDto.description())
                                    .dateStart(seanceDto.dateStart())
                                    .dateEnd(seanceDto.dateEnd())
                                    .rewardPoint(seanceDto.rewardPoint())
                                    .coachId(UUID.fromString(seanceDto.coachId()))
                                    .build()
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getSeances() {
        try {
            return ResponseEntity.ok(seanceService.getSeances());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{seanceId}")
    public ResponseEntity<Object> getSeanceById(@PathVariable UUID seanceId) {
        try {
            return ResponseEntity.ok(seanceService.getSeanceById(seanceId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{seanceId}")
    public ResponseEntity<Object> deleteSeance(@PathVariable UUID seanceId) {
        try {
            seanceService.deleteSeance(seanceId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
