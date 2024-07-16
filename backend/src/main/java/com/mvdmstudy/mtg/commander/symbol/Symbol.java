package com.mvdmstudy.mtg.commander.symbol;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Symbol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;

    private String svgUri;

    private String english;

    public Symbol(String symbol, String english, String svgUri) {
        this.symbol = symbol;
        this.svgUri = svgUri;
        this.english = english;
    }
}
