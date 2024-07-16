package com.mvdmstudy.mtg.commander.cardface;

public record CardFaceDto(String name, String manaCost, String types, String text, String imageURL, String power,
                          String toughness) {

    public static CardFaceDto from(CardFace cardFace) {
        return new CardFaceDto(cardFace.getName(), cardFace.getManaCost(), cardFace.getTypes(), cardFace.getText(),
                cardFace.getImageURL(), cardFace.getPowerAsString(), cardFace.getToughnessAsString());
    }
}