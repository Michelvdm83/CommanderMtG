package com.mvdmstudy.mtg.commander.cardface;

import com.mvdmstudy.mtg.commander.card.Card;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
public class CardFace {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String manaCost;
    private String types;

    @Column(columnDefinition = "TEXT")
    private String text;

    private Double power;
    private String powerAsString;

    private Double toughness;
    private String toughnessAsString;

    private String imageURL;

    @ManyToOne
    private Card card;
}
