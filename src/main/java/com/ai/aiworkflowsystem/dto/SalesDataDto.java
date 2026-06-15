package com.ai.aiworkflowsystem.dto;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesDataDto {

    private LocalDate date;

    private String region;

    private String leadSource;

    private Integer leadsGenerated;

    private Integer demoCalls;

    private Integer dealsClosed;

    private Double revenue;

    private Double marketingSpend;

    private Integer salesHoursSpent;

}
