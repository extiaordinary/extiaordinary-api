package fr.esgi.extiaordinaryapi.controller;

import fr.esgi.extiaordinaryapi.dto.RecommandationRequest;
import fr.esgi.extiaordinaryapi.service.RecommandationIAService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/recommandation")
@CrossOrigin(origins = "http://localhost:4200")
public class ChatGptRecommandationController {
    private final RecommandationIAService recommandationIAService;

    @PostMapping
    public ResponseEntity<Object> askRecommandation(@RequestBody @Valid RecommandationRequest request) {
        try {
            val responseGPT = recommandationIAService.chatWithGpt3(request.myInfos());
            return ResponseEntity.ok(responseGPT);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}