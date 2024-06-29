package com.mvdmstudy.mtg.commander.card;

import com.mvdmstudy.mtg.commander.cardface.CardFace;
import com.mvdmstudy.mtg.commander.setData.SetData;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCard {
    private UUID id;

    private UUID oracleId;

    private String name;

    private String manaCost;
    private Integer cmc;

    private String types;

    @Column(columnDefinition = "TEXT")
    private String text;

    private Integer power;
    private Boolean isPowerStarred;

    private Integer toughness;
    private Boolean isToughnessStarred;

    private String imageURL;

    private boolean commanderLegal;

    //    @ManyToOne
    private SetData set;

    // @OneToMany (mappedBy = "card", cascade = CascadeType.ALL)
    private Set<CardFace> cardFaces;
}