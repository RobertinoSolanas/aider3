package com.example.aider3.repository;

import com.example.aider3.model.FourCheckerBoardGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FourCheckerBoardGameRepository extends JpaRepository<FourCheckerBoardGame, Long> {
    List<FourCheckerBoardGame> findByStatus(FourCheckerBoardGame.GameStatus status);
}
