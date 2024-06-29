import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Card.css";

export default function Card({ card, handleMouseEnter, handleMouseLeave }){
    const navigate = useNavigate();

    const regex = new RegExp("(?<={)([^}]+)(?=})", "g");
    const imgURI = "https://svgs.scryfall.io/card-symbols/";
    const [showDetail, setShowDetail] = useState(false);

    return (
        <>
            <div onClick={() => navigate("/cards/" + card.id)} onMouseEnter={(event) => handleMouseEnter(event, card.imageURL)} onMouseLeave={handleMouseLeave}>
            {card.name} { card.mana_cost.match(regex).map((symbol, index) => <img key={symbol + ":" + index} src={imgURI + symbol + ".svg"} width={20} height={20}></img>)}
            </div>
        </>
    );
}