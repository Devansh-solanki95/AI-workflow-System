package com.ai.aiworkflowsystem.ai;


import com.ai.aiworkflowsystem.dto.KpiResponseDto;

public class PromptBuilder {

    private PromptBuilder() {}

    public static String buildBusinessInsightPrompt(
            KpiResponseDto kpi
    ) {

        return String.format("""
        You are a senior business analyst.

        Analyze these KPI metrics.

        Total Revenue: %.2f
        Total Leads: %d
        Total Deals Closed: %d
        Conversion Rate: %.2f%%
        Win Rate: %.2f%%
        Revenue Per Lead: %.2f
        Cost Per Lead: %.2f
        Customer Acquisition Cost: %.2f

        Return ONLY valid JSON.

        Format:

        {
          "executiveSummary":"",
          "keyInsights":"",
          "risks":"",
          "recommendations":""
        }

        Do not add markdown.
        Do not add explanation.
        Do not wrap in ```json.
        Return only valid JSON.
        """,

                kpi.getTotalRevenue(),
                kpi.getTotalLeads(),
                kpi.getTotalDealsClosed(),
                kpi.getConversionRate(),
                kpi.getWinRate(),
                kpi.getRevenuePerLead(),
                kpi.getCostPerLead(),
                kpi.getCustomerAcquisitionCost()
        );
    }
}
