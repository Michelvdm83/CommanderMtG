package com.mvdmstudy.mtg.commander.cardset;

import java.time.LocalDate;

public record CardSetDto(String code, String name, LocalDate release, Boolean inDatabase) {

  public static CardSetDto from(CardSet cardSet) {
    return new CardSetDto(
        cardSet.getCode(),
        cardSet.getName(),
        cardSet.getReleaseDate(),
        cardSet.isCardsInDatabase());
  }
}
