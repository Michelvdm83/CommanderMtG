package com.mvdmstudy.mtg.commander.card;

import com.mvdmstudy.mtg.commander.SearchParams;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "${mtg-commander.cors}")
public class CardController {

    private final CardRepository cardRepository;

    @GetMapping
    public List<CardDto> searchBy(@Valid SearchParams params) {
        Specification<Card> newPredicate = params.getSpecification();
        return cardRepository.findAll(newPredicate).stream().map(CardDto::from).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDto> findById(@PathVariable UUID id) {
        var card = cardRepository.findById(id);
        if (card.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(CardDto.from(card.get()));
    }

    @GetMapping("/search")
    public List<CardDto> findByName(@RequestParam String text) {
        return cardRepository.findByNameContainingIgnoreCase(text).stream().map(CardDto::from).toList();
    }

}