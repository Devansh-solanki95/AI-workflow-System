package com.ai.aiworkflowsystem.mapper;

import com.ai.aiworkflowsystem.dto.SalesDataDto;
import com.ai.aiworkflowsystem.entity.SalesData;

public class SalesDataMapper {

    public static SalesData toEntity(
            SalesDataDto dto
    ) {

        return SalesData.builder()
                .date(dto.getDate())
                .region(dto.getRegion())
                .leadSource(dto.getLeadSource())
                .leadsGenerated(dto.getLeadsGenerated())
                .demoCalls(dto.getDemoCalls())
                .dealsClosed(dto.getDealsClosed())
                .revenue(dto.getRevenue())
                .marketingSpend(dto.getMarketingSpend())
                .salesHoursSpent(dto.getSalesHoursSpent())
                .build();
    }

}
