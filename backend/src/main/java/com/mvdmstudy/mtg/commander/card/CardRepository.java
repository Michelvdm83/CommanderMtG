package com.mvdmstudy.mtg.commander.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID>, JpaSpecificationExecutor<Card> {
    List<Card> findByNameContainingIgnoreCase(String text);

    // List<Card> findByNameContainingIgnoreCaseAndTypesContainingIgnoreCase(String text, String types);
}
