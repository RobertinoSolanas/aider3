package com.example.aider3.controller;

import com.example.aider3.model.FourCheckerBoardGame;
import com.example.aider3.service.FourCheckerBoardGameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GameWebController {
    private final FourCheckerBoardGameService gameService;

    public GameWebController(FourCheckerBoardGameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/play")
    public String handleGameRequest(
            @RequestParam(required = false) Long gameId,
            @RequestParam(defaultValue = "Player1") String player1,
            @RequestParam(defaultValue = "Player2") String player2,
            Model model) {
        
        if (gameId == null) {
            // Create new game with default or provided player names
            FourCheckerBoardGame newGame = gameService.createGame(player1, player2);
            return "redirect:/play?gameId=" + newGame.getId();
        }
        
        // Load existing game
        model.addAttribute("game", gameService.getGameById(gameId));
        return "games/play";
    }
}
