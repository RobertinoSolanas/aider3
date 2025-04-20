package com.example.aider3.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Piece {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String position; // e.g. "a1"
    
    @Enumerated(EnumType.STRING)
    private PieceType type;
    
    @Enumerated(EnumType.STRING)
    private FourCheckerBoardGame.Color color;
    
    @ManyToOne
    @JoinColumn(name = "game_id")
    private FourCheckerBoardGame game;

    public enum PieceType {
        REGULAR, KING
    }

    // Helper method to get opponent color
    public FourCheckerBoardGame.Color getOpponentColor() {
        return this.color == FourCheckerBoardGame.Color.WHITE ? 
               FourCheckerBoardGame.Color.BLACK : FourCheckerBoardGame.Color.WHITE;
    }
}
