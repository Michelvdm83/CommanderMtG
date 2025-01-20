package com.mvdmstudy.mtg.commander;

import com.mvdmstudy.mtg.commander.card.CardRepository;
import com.mvdmstudy.mtg.commander.cardset.CardSet;
import com.mvdmstudy.mtg.commander.cardset.CardSetRepository;
import com.mvdmstudy.mtg.commander.scryfallData.card.ScryfallCard;
import com.mvdmstudy.mtg.commander.scryfallData.set.ScryfallCardSetsDataList;
import com.mvdmstudy.mtg.commander.scryfallData.set.SetCardsList;
import com.mvdmstudy.mtg.commander.scryfallData.symbol.ScryfallSymbol;
import com.mvdmstudy.mtg.commander.scryfallData.symbol.ScryfallSymbolsList;
import com.mvdmstudy.mtg.commander.symbol.SymbolRepository;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {
    private final SymbolRepository symbolRepository;
    private final CardRepository cardRepository;
    private final CardSetRepository cardSetRepository;
    private final RestClient restClient = RestClient.create();

    @Override
    public void run(String... args) throws URISyntaxException {
        if (symbolRepository.findAll().isEmpty()) {
            ScryfallSymbolsList symbols = restClient.get().uri("https://api.scryfall.com/symbology")
                    .accept(MediaType.APPLICATION_JSON).retrieve()
                    .body(ScryfallSymbolsList.class);

            if (symbols == null || symbols.data() == null) throw new RuntimeException("symbols not found");

            symbolRepository.saveAll(symbols.data().stream().map(ScryfallSymbol::toSymbol).toList());
        }

        if (cardSetRepository.findAll().isEmpty()) {
            ScryfallCardSetsDataList sets = restClient.get().uri("https://api.scryfall.com/sets")
                    .accept(MediaType.APPLICATION_JSON).retrieve()
                    .body(ScryfallCardSetsDataList.class);

            if (sets == null || sets.data() == null) throw new RuntimeException("sets not found");

            cardSetRepository.saveAll(sets.data().stream().map(CardSet::new).toList());
        }
        if (cardRepository.findAll().isEmpty()) {
            var allSets = cardSetRepository.findAll();
            var currentSet = allSets.stream().filter(set -> set.getCode().equalsIgnoreCase("mh3")).findFirst().orElse(allSets.getFirst());
            String searchUri = currentSet.getSearchUri();

            boolean more;
            do {
                System.out.println(searchUri);
                SetCardsList setCardsPage = restClient.get().uri(new URI(searchUri))
                        .accept(MediaType.APPLICATION_JSON).retrieve()
                        .body(SetCardsList.class);

                if (setCardsPage == null) throw new NullPointerException("No cards found");
                var cardsList = setCardsPage.data().stream().map(ScryfallCard::toCard).toList();
                cardsList.forEach(c -> c.setSet(currentSet));
                cardRepository.saveAll(cardsList);

                more = setCardsPage.has_more();
                searchUri = setCardsPage.next_page();
            } while (more);

            currentSet.setCardsInDatabase(true);
            cardSetRepository.save(currentSet);
        }

    }
}
