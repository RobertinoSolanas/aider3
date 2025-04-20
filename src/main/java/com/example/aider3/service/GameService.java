package com.example.aider3.service;

import com.example.aider3.model.Game;
import com.example.aider3.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GameService {
    private final GameRepository gameRepository;

    public Game createGame(String playerWhite, String playerBlack) {
        Game game = Game.builder()
                .playerWhite(playerWhite)
                .playerBlack(playerBlack)
                .status(Game.GameStatus.ACTIVE)
                .currentTurn(Game.Color.WHITE)
                .build();
        return gameRepository.save(game);
    }

    public List<Game> listGames(Game.GameStatus status) {
        if (status != null) {
            return gameRepository.findByStatus(status);
        }
        return gameRepository.findAll();
    }

    public Game getGameById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found"));
    }

    public Game makeMove(Long gameId, String from, String to) {
        // TODO: Implement move validation and update game state
        Game game = getGameById(gameId);
        return gameRepository.save(game);
    }

    public Game resignGame(Long gameId, String player) {
        Game game = getGameById(gameId);
        game.setStatus(Game.GameStatus.FINISHED);
        return gameRepository.save(game);
    }
}
