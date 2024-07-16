package com.mvdmstudy.mtg.commander.scryfallData.card;

import com.mvdmstudy.mtg.commander.card.Card;
import com.mvdmstudy.mtg.commander.cardface.CardFace;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class ScryfallCard {
    private UUID id;

    private UUID oracle_id;

    private String name;

    private String mana_cost;
    private Integer cmc;

    private String type_line;

    private String oracle_text;

    private String power;

    private String toughness;

    private ScryfallImages image_uris;

    private Legalities legalities = new Legalities();

    private String[] color_identity;

    private String rarity;

    private String set_type;

    private String set;

    private List<ScryFallCardFace> card_faces;

    public Card toCard() {
        Card card = new Card();
        card.setOracleId(oracle_id);
        card.setName(name);
        card.setManaCost(mana_cost);
        card.setCmc(cmc);
        card.setTypes(type_line);
        card.setText(oracle_text);

        if (power != null) {
            card.setPower(statToNumber(power));
            card.setPowerAsString(power);

            card.setToughness(statToNumber(toughness));
            card.setToughnessAsString(toughness);
        }

        if (image_uris != null && image_uris.png != null) {
            card.setImageURL(image_uris.png);
        }

        card.setCommanderLegal(legalities.commander.equalsIgnoreCase("legal"));
        card.setColorIdentity(String.join("", color_identity));

        if (card_faces != null) {
            Set<CardFace> faces = new HashSet<>();
            card_faces.forEach(sfFace -> {
                CardFace face = new CardFace();
                face.setName(sfFace.getName());
                face.setManaCost(sfFace.getMana_cost());
                face.setTypes(sfFace.getType_line());
                face.setText(sfFace.getOracle_text());

                if (sfFace.getPower() != null) {
                    face.setPowerAsString(sfFace.getPower());
                    face.setPower(statToNumber(sfFace.getPower()));
                    face.setToughnessAsString(sfFace.getToughness());
                    face.setToughness(statToNumber(sfFace.getToughness()));
                }
                if (sfFace.getImage_uris() != null && sfFace.getImage_uris().getPng() != null) {
                    face.setImageURL(sfFace.getImage_uris().getPng());
                }
                face.setCard(card);
                faces.add(face);
            });
            card.setCardFaces(faces);
        }
        return card;
    }


    private double statToNumber(String stat) {
        try {
            return Double.parseDouble(stat);
        } catch (NumberFormatException nfe) {
            if (stat.equalsIgnoreCase("*") ||
                    stat.equalsIgnoreCase("*²") ||
                    stat.equalsIgnoreCase("?")) return 0;

            if (stat.equalsIgnoreCase("∞")) return Double.POSITIVE_INFINITY;

            return Double.parseDouble(stat.replaceAll("\\D", ""));


        }
    }
}

@Getter
@Setter
@NoArgsConstructor
class Legalities {
    String commander = "";
}
