package com.ai.aiworkflowsystem.controller;

import com.ai.aiworkflowsystem.ai.GeminiClient;
import com.ai.aiworkflowsystem.dto.AIInsightResponseDto;
import com.ai.aiworkflowsystem.service.impl.AIInsightServiceImpl;
import com.ai.aiworkflowsystem.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestAIController {

    private final GeminiClient geminiClient;

    @Autowired
    private AIInsightServiceImpl aiInsightService;

    @GetMapping("/api/test-ai")
    public String testAi() {

        return geminiClient.generateContent(
                "Explain Spring Boot in 3 lines."
        );
    }

    @GetMapping("/insights")
    public ResponseEntity<ApiResponse>
    getInsights() {

        AIInsightResponseDto insights =
                aiInsightService.generateInsights();

        ApiResponse response =
                ApiResponse.<AIInsightResponseDto>builder()
                        .status(true)
                        .message("AI insights generated")
                        .data(insights)
                        .build();

        return ResponseEntity.ok(response);
    }
}
