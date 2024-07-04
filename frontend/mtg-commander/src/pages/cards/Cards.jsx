import axios from "axios";
import { useEffect } from "react";
import { useState } from "react";
import NameSearch from "../../components/NameSearch/NameSearch";
import Card from "../../components/Card/Card";
import "./Cards.css";

export default function Cards({ cards }) {
  const regex = new RegExp("(?<={)([^}]+)(?=})", "g");
  const imgURI = "https://svgs.scryfall.io/card-symbols/";

  const [hoverX, setHoverX] = useState(-700);
  const [hoverY, setHoverY] = useState(-500);

  function handleMouseEnter(event, img) {
    document.documentElement.style.setProperty(
      "--backgroundC",
      "url(" + img + ")"
    );
    document.documentElement.style.setProperty("--imgDisplay", "block");
  }

  const handleMouseLeave = () => {
    document.documentElement.style.setProperty("--imgDisplay", "none");
  };

  function updateMouseXY(event) {
    setHoverX(event.clientX + 5 + "px");
    setHoverY(event.clientY + 5 + "px");
  }

  return (
    <div className="cards-list" onMouseMove={(event) => updateMouseXY(event)}>
      {cards.length > 0 &&
        cards.map((card) => (
          <Card
            card={card}
            handleMouseEnter={handleMouseEnter}
            handleMouseLeave={handleMouseLeave}
            key={card.id}
          />
        ))}
      {<div className="hover-image" style={{ left: hoverX, top: hoverY }} />}
    </div>
  );
}
