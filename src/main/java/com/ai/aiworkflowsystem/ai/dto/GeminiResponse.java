package com.ai.aiworkflowsystem.ai.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeminiResponse {

    private List<Candidate> candidates;

    @Getter
    @Setter
    public static class Candidate {

        private Content content;
    }

    @Getter
    @Setter
    public static class Content {

        private List<Part> parts;
    }

    @Getter
    @Setter
    public static class Part {

        private String text;
    }
}
