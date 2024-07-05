package com.mvdmstudy.mtg.commander.card;

import com.mvdmstudy.mtg.commander.SearchParams;
import com.mvdmstudy.mtg.commander.SseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "${mtg-commander.cors}")
public class CardController {

    private final CardRepository cardRepository;
    private final SseService sseService;


    @GetMapping("/subscription")
    public SseEmitter subscribeTest() {
        System.out.println("sub");
        return sseService.subscribe();
    }

    @GetMapping
    public ResponseEntity<List<Card>> searchBy(@Valid SearchParams params) {//Pageable toevoegen
        new Thread(() -> sseService.sendEvent("new search done at:" + new Date())).start();

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