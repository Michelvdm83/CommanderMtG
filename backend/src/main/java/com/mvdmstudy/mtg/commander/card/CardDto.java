package com.mvdmstudy.mtg.commander.card;

import com.mvdmstudy.mtg.commander.cardface.CardFaceDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record CardDto(UUID id, String name, String manaCost, String types, String text, String imageURL, String power,
                      String toughness, List<CardFaceDto> faces) {

    public static CardDto from(Card card) {
        List<CardFaceDto> faces;
        if (card.getCardFaces() != null) {
            faces = new ArrayList<>();
            card.getCardFaces().forEach(cf -> {
                faces.add(CardFaceDto.from(cf));
            });
        } else {
            faces = null;
        }
        return new CardDto(card.getId(), card.getName(), card.getManaCost(), card.getTypes(), card.getText(), card.getImageURL(),
                card.getPowerAsString(), card.getToughnessAsString(), faces);
    }
}
