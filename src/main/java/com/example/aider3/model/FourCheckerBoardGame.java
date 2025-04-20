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
public class FourCheckerBoardGame extends Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    public enum GameStatus {
        ACTIVE, FINISHED
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
