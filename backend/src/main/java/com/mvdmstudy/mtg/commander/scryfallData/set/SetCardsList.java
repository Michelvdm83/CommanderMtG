package com.mvdmstudy.mtg.commander.scryfallData.set;

import com.mvdmstudy.mtg.commander.scryfallData.card.ScryfallCard;

import java.util.List;

public record SetCardsList(String object, Boolean has_more, String next_page, List<ScryfallCard> data) {
}
