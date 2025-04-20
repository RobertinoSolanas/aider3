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
    private Color color;
    
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public enum PieceType {
        REGULAR, KING
    }
}
