package com.example.aider3.service;

import com.example.aider3.model.FourCheckerBoardGame;
import com.example.aider3.model.Piece;
import com.example.aider3.repository.FourCheckerBoardGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FourCheckerBoardGameService {
    private final FourCheckerBoardGameRepository gameRepository;

    public FourCheckerBoardGame createGame(String playerWhite, String playerBlack) {
        FourCheckerBoardGame game = FourCheckerBoardGame.builder()
                .playerWhite(playerWhite)
                .playerBlack(playerBlack)
                .status(FourCheckerBoardGame.GameStatus.ACTIVE)
                .currentTurn(FourCheckerBoardGame.Color.WHITE)
                .build();
        return gameRepository.save(game);
    }

    public List<FourCheckerBoardGame> listGames(FourCheckerBoardGame.GameStatus status) {
        if (status != null) {
            return gameRepository.findByStatus(status);
        }
        return gameRepository.findAll();
    }

    public FourCheckerBoardGame getGameById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found"));
    }

    public FourCheckerBoardGame makeMove(Long gameId, String from, String to) {
        FourCheckerBoardGame game = getGameById(gameId);
        // TODO: Implement 4x4 checkerboard specific move validation
        return gameRepository.save(game);
    }

    public FourCheckerBoardGame resignGame(Long gameId, String player) {
        FourCheckerBoardGame game = getGameById(gameId);
        game.setStatus(FourCheckerBoardGame.GameStatus.FINISHED);
        return gameRepository.save(game);
    }
}
