package com.ai.aiworkflowsystem.service.impl;

import com.ai.aiworkflowsystem.ai.GeminiClient;
import com.ai.aiworkflowsystem.ai.PromptBuilder;
import com.ai.aiworkflowsystem.dto.AIInsightResponseDto;
import com.ai.aiworkflowsystem.dto.KpiResponseDto;
import com.ai.aiworkflowsystem.exception.AIServiceException;
import com.ai.aiworkflowsystem.service.AIInsightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class AIInsightServiceImpl implements AIInsightService {

    private final ObjectMapper objectMapper;
    private final AnalyticsServiceImpl analyticsService;
    private final GeminiClient geminiClient;

    @Override
    public AIInsightResponseDto generateInsights() {

        KpiResponseDto kpi =
                analyticsService.calculateKpis();

        String prompt =
                PromptBuilder
                        .buildBusinessInsightPrompt(kpi);

        String aiResponse =
                geminiClient.generateContent(prompt);

        try {

            return objectMapper.readValue(
                    aiResponse,
                    AIInsightResponseDto.class
            );

        } catch (Exception ex) {

            throw new AIServiceException(
                    "Failed to parse AI response"
            );
        }
    }

}
