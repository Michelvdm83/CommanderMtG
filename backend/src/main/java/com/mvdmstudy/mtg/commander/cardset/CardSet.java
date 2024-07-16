package com.mvdmstudy.mtg.commander.cardset;

import com.mvdmstudy.mtg.commander.card.Card;
import com.mvdmstudy.mtg.commander.scryfallData.set.ScryfallCardSet;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class CardSet {

    @Id
    @GeneratedValue
    private UUID id;

    private String code;

    private String name;

    private String searchUri;

    @OneToMany(mappedBy = "set")
    private Set<Card> cards;

    public CardSet(ScryfallCardSet scryfallCardSet) {
        this.code = scryfallCardSet.getCode();
        this.name = scryfallCardSet.getName();
        this.searchUri = scryfallCardSet.getSearch_uri();
    }
}
