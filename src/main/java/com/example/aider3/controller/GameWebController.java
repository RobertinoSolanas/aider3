package com.example.aider3.controller;

import com.example.aider3.model.FourCheckerBoardGame;
import com.example.aider3.service.FourCheckerBoardGameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/games")
public class GameWebController {
    private final FourCheckerBoardGameService gameService;

    public GameWebController(FourCheckerBoardGameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public String listGames(Model model) {
        model.addAttribute("games", gameService.listGames(null));
        return "games/list";
    }

    @GetMapping("/new")
    public String newGameForm(Model model) {
        return "games/new";
    }

    @PostMapping
    public String createGame(
            @RequestParam String playerWhite,
            @RequestParam String playerBlack,
            Model model) {
        FourCheckerBoardGame game = gameService.createGame(playerWhite, playerBlack);
        return "redirect:/web/games/" + game.getId();
    }

    @GetMapping("/{id}")
    public String viewGame(@PathVariable Long id, Model model) {
        model.addAttribute("game", gameService.getGameById(id));
        return "games/play";
    }
}
