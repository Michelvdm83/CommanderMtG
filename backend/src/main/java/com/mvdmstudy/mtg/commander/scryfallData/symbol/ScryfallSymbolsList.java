package com.mvdmstudy.mtg.commander.scryfallData.symbol;

import java.util.List;

public record ScryfallSymbolsList(String object, Boolean has_more, List<ScryfallSymbol> data) {
}
