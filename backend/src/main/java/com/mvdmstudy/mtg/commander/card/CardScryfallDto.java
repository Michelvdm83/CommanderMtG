package com.mvdmstudy.mtg.commander.card;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class CardScryfallDto {
    private UUID id;

    private UUID oracle_id;

    private String name;

    private String mana_cost;
    private String cmc;

    private String type_line;

    private String oracle_text;

    private String power;

    private String toughness;

    private Images image_uris;

    private Legalities legalities;

    private String rarity;

    private String set_type;

    private String set;

    private Set<ScryFallCardFace> card_faces;

    public Card toCard() {
        return new Card(id, oracle_id, name, mana_cost, type_line, oracle_text, power, toughness, image_uris.getPng());
    }
}

@Getter
@Setter
@NoArgsConstructor
class Images {
    String small;
    String medium;
    String large;
    String png;
}

@Getter
@Setter
@NoArgsConstructor
class Legalities {
    String commander;
}
