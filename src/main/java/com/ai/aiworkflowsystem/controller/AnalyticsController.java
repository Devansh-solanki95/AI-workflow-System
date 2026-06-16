package com.ai.aiworkflowsystem.controller;


import com.ai.aiworkflowsystem.dto.KpiResponseDto;
import com.ai.aiworkflowsystem.service.AnalyticsService;
import com.ai.aiworkflowsystem.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService
            analyticsService;

    @GetMapping("/kpis")
    public ResponseEntity<ApiResponse>
    getKpis() {

        KpiResponseDto responseDto =
                analyticsService.calculateKpis();

        ApiResponse response =
                ApiResponse.builder()
                        .status(true)
                        .message(
                                "KPIs calculated successfully"
                        )
                        .data(responseDto)
                        .build();

        return ResponseEntity.ok(response);
    }

}
