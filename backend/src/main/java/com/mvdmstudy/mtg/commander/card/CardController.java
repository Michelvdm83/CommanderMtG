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
    public ResponseEntity<List<Card>> searchBy(@Valid SearchParams params) {//Pageable toevoegen
        Specification<Card> newPredicate = params.getSpecification();
        var list = cardRepository.findAll(newPredicate);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(cardRepository.findById(id).get());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Card>> findByName(@RequestParam String text) {//, @RequestParam(required = false) String types
        return ResponseEntity.ok(cardRepository.findByNameContainingIgnoreCase(text));
//                ResponseEntity.ok(cardRepository.findByNameContainingIgnoreCaseAndTypesContainingIgnoreCase(text, types));
    }

}