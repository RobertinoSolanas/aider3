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
public class FourCheckerBoardGame {
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

    public boolean isPositionValid(String position) {
        if (position == null || position.length() != 2) {
            return false;
        }
        char col = position.charAt(0);
        char row = position.charAt(1);
        return col >= 'a' && col <= 'd' && row >= '1' && row <= '4';
    }
}
