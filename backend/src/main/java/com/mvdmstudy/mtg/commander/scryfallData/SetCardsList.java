package com.mvdmstudy.mtg.commander.scryfallData;

import com.mvdmstudy.mtg.commander.card.CardScryfallDto;

import java.util.List;

public record SetCardsList(String object, Boolean has_more, String next_page, List<CardScryfallDto> data) {
}
