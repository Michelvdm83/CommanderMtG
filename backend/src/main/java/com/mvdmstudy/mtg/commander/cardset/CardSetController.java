package com.mvdmstudy.mtg.commander.cardset;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "${mtg-commander.cors}")
@RequestMapping("sets")
public class CardSetController {
  private final CardSetRepository cardSetRepository;

  @GetMapping
  public List<CardSetDto> getAll() {
    return cardSetRepository.findAll().stream().map(CardSetDto::from).toList();
  }

  // PatchMapping for adding all cards from set to db (here or in cardcontroller?)
}
