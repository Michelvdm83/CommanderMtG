package com.mvdmstudy.mtg.commander.cardface;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardFaceRepository extends JpaRepository<CardFace, UUID> {
}
