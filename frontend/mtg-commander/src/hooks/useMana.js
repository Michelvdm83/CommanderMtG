import { useState, useEffect } from "react";

export default function useMana() {
    const costRegex = new RegExp("(?<={)([^}]+)(?=})|// ", "g");
    const symbolsURI = "https://svgs.scryfall.io/card-symbols/";

    function splitManaCost(manaCost){
        return manaCost.match(costRegex);
    }
}