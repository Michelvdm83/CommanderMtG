package com.mvdmstudy.mtg.commander.scryfallData;

import com.mvdmstudy.mtg.commander.symbol.Symbol;

import java.util.List;

public record SymbolsList(String object, Boolean has_more, List<Symbol> data) {
}
