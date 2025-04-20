package com.example.aider3.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Standard error response format")
public class ApiErrorResponse {
    @Schema(description = "HTTP status code", example = "400")
    private int status;
    
    @Schema(description = "Error timestamp", example = "2025-04-20T14:30:00")
    private LocalDateTime timestamp;
    
    @Schema(description = "Error message", example = "Invalid input parameter")
    private String message;
    
    @Schema(description = "Detailed error description", example = "The 'from' position must be a valid board coordinate")
    private String details;
}
