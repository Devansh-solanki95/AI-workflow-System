package com.ai.aiworkflowsystem.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "sales_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
