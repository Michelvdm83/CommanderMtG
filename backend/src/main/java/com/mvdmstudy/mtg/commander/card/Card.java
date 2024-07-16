package com.mvdmstudy.mtg.commander.card;

import com.mvdmstudy.mtg.commander.cardface.CardFace;
import com.mvdmstudy.mtg.commander.cardset.CardSet;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Card {
    @Id
    @GeneratedValue
    private UUID id;

    private UUID oracleId;

    private String name;

    private String manaCost;
    private Integer cmc;

    private String types;

    @Column(columnDefinition = "TEXT")
    private String text;

    private Double power;
    private String powerAsString;

    private Double toughness;
    private String toughnessAsString;

    private String imageURL;

    private boolean commanderLegal;
    private String colorIdentity;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private Set<CardFace> cardFaces;

    @ManyToOne
    private CardSet set;

}
