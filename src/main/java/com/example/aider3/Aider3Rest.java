package com.example.aider3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Utility API", description = "Utility endpoints for service health and testing")
public class Aider3Rest {

    @Operation(
        summary = "Get service greeting",
        description = "Returns a greeting message to verify service is running",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successful operation",
                content = @Content(
                    mediaType = "text/plain",
                    schema = @Schema(implementation = String.class),
                examples = @ExampleObject(value = "Hello from Aider3!")))
        })
    @GetMapping("/hello3")
    public String hello3() {
        return "Hello from Aider3!";
    }

    @Operation(
        summary = "Echo input",
        description = "Echoes back the input string for testing purposes",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successful operation",
                content = @Content(
                    mediaType = "text/plain",
                    schema = @Schema(implementation = String.class))))
        })
    @GetMapping("/echo")
    public String echo(
        @Parameter(
            description = "Input string to echo back",
            required = true,
            example = "Hello World!",
            schema = @Schema(type = "string", minLength = 1))
        @RequestParam String value) {
        return value;
    }

    @Operation(
        summary = "Service health check",
        description = "Simple endpoint to verify service availability",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Service is healthy",
                content = @Content(
                    mediaType = "text/plain",
                    schema = @Schema(implementation = String.class),
                examples = @ExampleObject(value = "OK"))))
        })
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
