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
    private Game.Color color;
    
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public enum PieceType {
        REGULAR, KING
    }

    // Helper method to get opponent color
    public Game.Color getOpponentColor() {
        return this.color == Game.Color.WHITE ? Game.Color.BLACK : Game.Color.WHITE;
    }
}
