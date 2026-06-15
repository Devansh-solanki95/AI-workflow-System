package com.ai.aiworkflowsystem.utils;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ApiResponse {

    private Boolean status;

    private String message;

    private Object data;

}
