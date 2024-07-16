package com.mvdmstudy.mtg.commander.scryfallData.symbol;

import com.mvdmstudy.mtg.commander.symbol.Symbol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScryfallSymbol {

    private String symbol;

    private String svg_uri;

    private String english;

    public Symbol toSymbol() {
        return new Symbol(symbol, english, svg_uri);
    }

}