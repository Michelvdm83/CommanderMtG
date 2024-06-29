package com.mvdmstudy.mtg.commander;

import com.mvdmstudy.mtg.commander.card.CardRepository;
import com.mvdmstudy.mtg.commander.card.CardScryfallDto;
import com.mvdmstudy.mtg.commander.scryfallData.SetCardsList;
import com.mvdmstudy.mtg.commander.scryfallData.SetDataList;
import com.mvdmstudy.mtg.commander.scryfallData.SymbolsList;
import com.mvdmstudy.mtg.commander.setData.SetDataRepository;
import com.mvdmstudy.mtg.commander.symbol.SymbolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.net.URISyntaxException;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {
    private final SymbolRepository symbolRepository;
    private final CardRepository cardRepository;
    private final SetDataRepository setDataRepository;
    private final RestClient restClient = RestClient.create();

    @Override
    public void run(String... args) throws URISyntaxException {
        if (symbolRepository.findAll().isEmpty()) {
            SymbolsList symbols = restClient.get().uri("https://api.scryfall.com/symbology")
                    .accept(MediaType.APPLICATION_JSON).retrieve()
                    .body(SymbolsList.class);

            if (symbols == null || symbols.data() == null) throw new RuntimeException("symbols not found");

            symbolRepository.saveAll(symbols.data());
        }

        if (setDataRepository.findAll().isEmpty()) {
            SetDataList sets = restClient.get().uri("https://api.scryfall.com/sets")
                    .accept(MediaType.APPLICATION_JSON).retrieve()
                    .body(SetDataList.class);

            if (sets == null || sets.data() == null) throw new RuntimeException("sets not found");

            setDataRepository.saveAll(sets.data());
        }
        if (cardRepository.findAll().isEmpty()) {
            var allSets = setDataRepository.findAll();
            String searchUri = allSets.getLast().getSearch_uri();

            boolean more;
            do {
                System.out.println(searchUri);
                SetCardsList setCardsPage = restClient.get().uri(new URI(searchUri))
                        .accept(MediaType.APPLICATION_JSON).retrieve()
                        .body(SetCardsList.class);
                cardRepository.saveAll(setCardsPage.data().stream().map(CardScryfallDto::toCard).toList());
                more = setCardsPage.has_more();
                searchUri = setCardsPage.next_page();
            } while (more);
        }

    }
}
