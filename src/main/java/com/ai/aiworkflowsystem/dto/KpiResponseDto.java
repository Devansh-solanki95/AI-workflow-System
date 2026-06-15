package com.ai.aiworkflowsystem.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KpiResponseDto {

    private Double totalRevenue;

    private Integer totalLeads;

    private Integer totalDealsClosed;

    private Double conversionRate;

    private Double winRate;

    private Double revenuePerLead;

    private Double costPerLead;

    private Double customerAcquisitionCost;

}
