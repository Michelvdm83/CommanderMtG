package com.mvdmstudy.mtg.commander.setData;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class SetData {

    @Id
    private UUID id;

    private String code;

    private String name;

//    @OneToMany(mappedBy = "set")
//    private Set<Card> cards = new HashSet<>();

    private String search_uri;
}
