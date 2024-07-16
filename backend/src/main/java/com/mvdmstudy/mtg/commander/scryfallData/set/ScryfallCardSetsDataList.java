package com.mvdmstudy.mtg.commander.scryfallData.set;

import java.util.List;

public record ScryfallCardSetsDataList(String object, Boolean has_more, List<ScryfallCardSet> data) {
}
