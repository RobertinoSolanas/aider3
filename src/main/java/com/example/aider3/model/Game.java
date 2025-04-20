package com.example.aider3.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String playerWhite;
    private String playerBlack;
    
    @Enumerated(EnumType.STRING)
    private GameStatus status;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game", fetch = FetchType.EAGER)
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
