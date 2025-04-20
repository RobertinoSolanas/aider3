package com.example.aider3.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String playerWhite;
    private String playerBlack;
    
    @Enumerated(EnumType.STRING)
    private GameStatus status;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
    private List<Piece> pieces;
    
    @Enumerated(EnumType.STRING)
    private Color currentTurn;
    
    public enum GameStatus {
        ACTIVE, FINISHED
    }
    
    public enum Color {
        WHITE, BLACK
    }
}
