package fr.esgi.extiaordinaryapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.esgi.extiaordinaryapi.dto.CompletionRequest;
import fr.esgi.extiaordinaryapi.dto.CompletionResponse;
import fr.esgi.extiaordinaryapi.entity.User;
import fr.esgi.extiaordinaryapi.openia.OpenAiApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class RecommandationIAService {

    private final ObjectMapper jsonMapper;
    private final OpenAiApiClient client;
    private final UserService userService;

    public String chatWithGpt3(String infos) throws Exception {
        String message = demandRecommandation(infos);
        var completion = CompletionRequest.defaultWith(message);
        var postBodyJson = jsonMapper.writeValueAsString(completion);
        var responseBody = client.postToOpenAiApi(postBodyJson, OpenAiApiClient.OpenAiService.GPT_3);
        var completionResponse = jsonMapper.readValue(responseBody, CompletionResponse.class);
        return completionResponse.firstAnswer().orElseThrow();
    }

    public String demandRecommandation(String infos){
        String messageQuestion = "Donne moi des recommandantions ou conseils de santé sport pour une personne" + infos;
        return messageQuestion;
    }
}
