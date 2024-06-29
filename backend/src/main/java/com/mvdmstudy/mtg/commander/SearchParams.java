package com.mvdmstudy.mtg.commander;

import com.mvdmstudy.mtg.commander.card.Card;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;


@Getter
@Setter
@NoArgsConstructor
public class SearchParams { //eigen Specification<Card> klasse maken?

    @NotBlank(message = "name search mandatory")
    private String name;
    private String type_line;
    private String text;


    public Specification<Card> getSpecification() {
        var specsList = new ArrayList<Specification<Card>>();
        Specification<Card> nameSpecification = (root, query, builder) ->
                builder.like(builder.upper(root.get("name").as(String.class)), "%".concat(name.toUpperCase()).concat("%"));
        specsList.add(nameSpecification);
        if (type_line != null) {
            Specification<Card> typeSpecification = (root, query, builder) ->
                    builder.like(builder.upper(root.get("type_line").as(String.class)), "%" + type_line.toUpperCase() + "%");
            specsList.add(typeSpecification);
        }
        if (text != null) {
            Specification<Card> textSpecification = (root, query, builder) ->
                    builder.like(builder.upper(root.get("oracle_text").as(String.class)), "%" + text.toUpperCase() + "%");
            specsList.add(textSpecification);
        }

        return Specification.allOf(specsList);
    }

}
