package com.mvdmstudy.mtg.commander.card;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Card {
    @Id
    private UUID id;

    private UUID oracle_id;

    private String name;

    private String mana_cost;//rename naar manaCost
    // private Integer cmc;

    //    @Column(name = "type_line") : rename naar types
    private String type_line;

    @Column(columnDefinition = "TEXT")
    private String oracle_text;
    /*
    private String text;
     */

    private String power;
    /*

    private Integer power;
    private Boolean isPowerStarred;

     */

    private String toughness;
    /*
    private String toughness;
    private String isToughnessStarred;
     */

    private String imageURL;

/*    @ManyToOne
        private SetData set;

        private boolean commanderLegal;



        private CardBack;
        private SplitPart; (geen imgUrl,
        colorIdentity? colors?
 */
}
