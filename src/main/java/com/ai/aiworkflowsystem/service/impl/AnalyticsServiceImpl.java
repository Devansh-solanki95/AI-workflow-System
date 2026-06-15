package com.ai.aiworkflowsystem.service.impl;


import com.ai.aiworkflowsystem.dto.KpiResponseDto;
import com.ai.aiworkflowsystem.entity.SalesData;
import com.ai.aiworkflowsystem.repository.SalesDataRepository;
import com.ai.aiworkflowsystem.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl
        implements AnalyticsService {

    private final SalesDataRepository
            salesDataRepository;

    @Override
    public KpiResponseDto calculateKpis() {

        List<SalesData> salesDataList =
                salesDataRepository.findAll();

        double totalRevenue =
                salesDataList.stream()
                        .mapToDouble(
                                SalesData::getRevenue
                        )
                        .sum();

        int totalLeads =
                salesDataList.stream()
                        .mapToInt(
                                SalesData::getLeadsGenerated
                        )
                        .sum();

        int totalDealsClosed =
                salesDataList.stream()
                        .mapToInt(
                                SalesData::getDealsClosed
                        )
                        .sum();

        int totalDemoCalls =
                salesDataList.stream()
                        .mapToInt(
                                SalesData::getDemoCalls
                        )
                        .sum();

        double totalMarketingSpend =
                salesDataList.stream()
                        .mapToDouble(
                                SalesData::getMarketingSpend
                        )
                        .sum();

        double conversionRate =
                totalLeads == 0
                        ? 0
                        : ((double) totalDealsClosed
                        / totalLeads) * 100;

        double winRate =
                totalDemoCalls == 0
                        ? 0
                        : ((double) totalDealsClosed
                        / totalDemoCalls) * 100;

        double revenuePerLead =
                totalLeads == 0
                        ? 0
                        : totalRevenue / totalLeads;

        double costPerLead =
                totalLeads == 0
                        ? 0
                        : totalMarketingSpend
                        / totalLeads;

        double customerAcquisitionCost =
                totalDealsClosed == 0
                        ? 0
                        : totalMarketingSpend
                        / totalDealsClosed;

        return KpiResponseDto.builder()
                .totalRevenue(totalRevenue)
                .totalLeads(totalLeads)
                .totalDealsClosed(totalDealsClosed)
                .conversionRate(conversionRate)
                .winRate(winRate)
                .revenuePerLead(revenuePerLead)
                .costPerLead(costPerLead)
                .customerAcquisitionCost(
                        customerAcquisitionCost
                )
                .build();
    }

}
