package com.mvdmstudy.mtg.commander.scryfallData.set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class ScryfallCardSet {

    private UUID id;

    private String code;

    private String name;

    private String search_uri;

}
