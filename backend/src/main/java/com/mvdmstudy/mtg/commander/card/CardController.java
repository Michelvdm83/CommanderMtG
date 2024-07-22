package com.mvdmstudy.mtg.commander.card;

import com.mvdmstudy.mtg.commander.SearchParams;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "${mtg-commander.cors}")
public class CardController {

    private final CardRepository cardRepository;

    @GetMapping
    public Iterable<CardDto> searchBy(@Valid SearchParams params, Pageable pageable) {
        Specification<Card> newPredicate = params.getSpecification();
        return cardRepository.findAll(newPredicate,
                PageRequest.of(
                        pageable.getPageNumber(),
                        10,
                        Sort.by("name")
                )).map(CardDto::from);
    }

    /*
    @GetMapping
    public Iterable<?> getAll(Pageable pageable) {
        return movieRepository.findAll(PageRequest.of(
                pageable.getPageNumber(),
                Math.min(pageable.getPageSize(), 3),
                pageable.getSortOr(Sort.by("title"))
        )).map(MovieDto::new);
    }
     */

    @GetMapping("/{id}")
    public ResponseEntity<CardDto> findById(@PathVariable UUID id) {
        var card = cardRepository.findById(id);
        if (card.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(CardDto.from(card.get()));
    }

//    @GetMapping("/search")
//    public List<CardDto> findByName(@RequestParam String text) {
//        return cardRepository.findByNameContainingIgnoreCase(text).stream().map(CardDto::from).toList();
//    }

}