import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Card.css";

export default function Card({ card, handleMouseEnter, handleMouseLeave }) {
  const navigate = useNavigate();

  const regex = new RegExp("(?<={)([^}]+)(?=})", "g");
  const imgURI = "https://svgs.scryfall.io/card-symbols/";
  const [showDetail, setShowDetail] = useState(false);
  const cost = card.manaCost
    ? card.manaCost
    : card.faces
    ? card.faces.find(isFront).manaCost
    : "";
  const imageURL = card.imageURL ? card.imageURL : getFrontImage();

  function getFrontImage() {
    const faces = card.faces;
    const front = faces.find(isFront);
    return front.imageURL;
  }

  function isFront(face) {
    return face.imageURL.includes("front");
  }

  return (
    <>
      <div
        onClick={() => navigate("/cards/" + card.id)}
        onMouseEnter={(event) => handleMouseEnter(event, imageURL)}
        onMouseLeave={handleMouseLeave}
      >
        {card.name}{" "}
        {cost.match(regex).map((symbol, index) => (
          <img
            key={symbol + ":" + index}
            src={imgURI + symbol + ".svg"}
            width={20}
            height={20}
          ></img>
        ))}
      </div>
    </>
  );
}
