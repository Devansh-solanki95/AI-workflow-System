package com.ai.aiworkflowsystem.ai;

import com.ai.aiworkflowsystem.ai.dto.GeminiResponse;
import com.ai.aiworkflowsystem.config.GeminiProperties;
import com.ai.aiworkflowsystem.exception.AIServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GeminiClient {

    private final RestClient restClient;

    private final GeminiProperties geminiProperties;

    public String generateContent(String prompt) {

        Map<String, Object> requestBody =
                Map.of(
                        "contents",
                        List.of(
                                Map.of(
                                        "parts",
                                        List.of(
                                                Map.of(
                                                        "text",
                                                        prompt
                                                )
                                        )
                                )
                        )
                );

        GeminiResponse response =
                restClient.post()
                        .uri(
                                geminiProperties.getApiUrl()
                                        + "?key="
                                        + geminiProperties.getApiKey()
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(requestBody)
                        .retrieve()
                        .body(GeminiResponse.class);

        if (response == null
                || response.getCandidates() == null
                || response.getCandidates().isEmpty()) {

            throw new AIServiceException(
                    "No response received from Gemini"
            );
        }

        return response.getCandidates()
                .get(0)
                .getContent()
                .getParts()
                .get(0)
                .getText();
    }
}