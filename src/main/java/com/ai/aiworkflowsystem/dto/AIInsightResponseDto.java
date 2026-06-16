package com.ai.aiworkflowsystem.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AIInsightResponseDto {

    private String executiveSummary;

    private String keyInsights;

    private String risks;

    private String recommendations;
}
