package com.example.aider3.controller;

import com.example.aider3.model.Game;
import com.example.aider3.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
@Tag(name = "Checkers Game API", description = "API for managing checkers games")
public class GameController {
    private final GameService gameService;

    @PostMapping
    @Operation(summary = "Create a new game",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Game created"),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            })
    public ResponseEntity<Game> createGame(
            @Parameter(description = "Player names") 
            @RequestParam(required = false) String playerWhite,
            @RequestParam(required = false) String playerBlack) {
        return ResponseEntity.ok(gameService.createGame(playerWhite, playerBlack));
    }

    @GetMapping
    @Operation(summary = "List games by status")
    public ResponseEntity<List<Game>> listGames(
            @Parameter(description = "Game status filter") 
            @RequestParam(required = false) Game.GameStatus status) {
        return ResponseEntity.ok(gameService.listGames(status));
    }

    // Additional endpoints for move, resign, etc.
}
