package fr.esgi.extiaordinaryapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.esgi.extiaordinaryapi.openia.OpenAiApiClient;
import fr.esgi.extiaordinaryapi.dto.CompletionRequest;
import fr.esgi.extiaordinaryapi.dto.CompletionResponse;
import fr.esgi.extiaordinaryapi.service.RecommandationIAService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/chat_gpt")
@CrossOrigin(origins = "http://localhost:4200")
public class ChatGptController {
    private final RecommandationIAService recommandationIAService;

    @PostMapping(path = "/recommandation")
    public ResponseEntity<Object> askRecommandation(@RequestBody String infos) {
        try {
            val responseGPT = recommandationIAService.chatWithGpt3(infos);
            return ResponseEntity.ok(responseGPT);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}