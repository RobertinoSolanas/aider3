package com.example.aider3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Aider3 API", description = "Simple demonstration endpoints")
public class Aider3Rest {

    @Operation(
        summary = "Get greeting",
        description = "Returns a friendly greeting message",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
        })
    @GetMapping("/hello3")
    public String hello3() {
        return "Hello from Aider3!";
    }

    @Operation(
        summary = "Echo input",
        description = "Returns the exact string that was provided as input",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
        })
    @GetMapping("/echo")
    public String echo(
        @Parameter(description = "The string to echo back", example = "Hello World2!")
        @RequestParam String value) {
        return value;
    }
}
