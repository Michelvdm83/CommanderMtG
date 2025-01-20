package com.mvdmstudy.mtg.commander.scryfallData.set;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ScryfallCardSet {

  private UUID id;

  private String code;

  private String name;

  private String search_uri;

  private LocalDate released_at;
}
