//package com.mvdmstudy.mtg.commander.cardface;
//
//import com.mvdmstudy.mtg.commander.card.Card;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.UUID;
//
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//public class CardFace {
//    @Id
//    @GeneratedValue
//    private UUID id;
//
//    private String name;
//    private String manaCost;
//    private String types;
//
//    @Column(columnDefinition = "TEXT")
//    private String text;
//
//    private Integer power;
//    private Boolean isPowerStarred;
//
//    private Integer toughness;
//    private String isToughnessStarred;
//
//    private String imageURL;
//
//    // @ManyToOny
//    private Card card;
//}
