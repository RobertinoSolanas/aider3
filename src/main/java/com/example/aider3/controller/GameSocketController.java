package com.example.aider3.controller;

import com.example.aider3.model.Game;
import com.example.aider3.service.GameService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameSocketController {
    private final GameService gameService;

    public GameSocketController(GameService gameService) {
        this.gameService = gameService;
    }

    @MessageMapping("/game/move")
    @SendTo("/topic/game/{gameId}")
    public Game makeMove(MoveRequest moveRequest) {
        // TODO: Implement move validation
        return gameService.getGameById(moveRequest.getGameId());
    }

    @MessageMapping("/game/resign")
    @SendTo("/topic/game/{gameId}")
    public Game resignGame(ResignRequest resignRequest) {
        // TODO: Implement resign logic
        return gameService.getGameById(resignRequest.getGameId());
    }

    public static class MoveRequest {
        private Long gameId;
        private String from;
        private String to;

        // Getters and setters
    }

    public static class ResignRequest {
        private Long gameId;

        // Getters and setters
    }
}
