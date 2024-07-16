package com.mvdmstudy.mtg.commander.scryfallData.card;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScryFallCardFace {
    private String name;
    private String mana_cost;
    private String type_line;
    private String oracle_text;

    private String power;
    private String toughness;
    private ScryfallImages image_uris;
}
