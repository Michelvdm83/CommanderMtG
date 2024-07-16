package com.mvdmstudy.mtg.commander.cardset;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardSetRepository extends JpaRepository<CardSet, UUID> {
}
