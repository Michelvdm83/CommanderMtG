import { useParams } from "react-router-dom";
import NameSearch from "../../components/NameSearch";
import { useEffect, useState } from "react";
import axios from "axios";
import "./CardDetail.css";

export default function CardDetail() {
  const { id } = useParams();
  const [card, setCard] = useState(null);

  useEffect(() => {
    axios
      .get(`http://localhost:8080/${id}`)
      .then((response) => setCard(response.data))
      .catch((error) => console.log(error));
  }, []);

  function getImage() {
    if (card.imageURL) {
      return <img src={card.imageURL} width={300} height={450} />;
    } else if (card.faces) {
      const front = getFace("front");
      const back = getFace("back");
      return (
        <div className="card-detail">
          <img src={front.imageURL} width={300} height={450} key={front.name} />
          <img src={back.imageURL} width={300} height={450} key={back.name} />
          {/* {card.faces.map((face) => (
            <img src={face.imageURL} width={300} height={450} key={face.name} />
          ))} */}
        </div>
      );
    } else return <></>;
  }

  function getFace(side) {
    return card.faces.find((face) => face.imageURL.includes(side));
  }

  function getText() {
    if (card.text) {
      return <div className="card-attribute">{card.text}</div>;
    } else if (card.faces) {
      const front = getFace("front");
      const back = getFace("back");

      return (
        <div>
          <div className="card-attribute">{front.text}</div>
          <div className="card-attribute">{back.text}</div>
        </div>
      );
    } else return <></>;
  }

  function getDetails() {
    if (card == null) {
      return <></>;
    }
    return (
      <div className="card-detail">
        {getImage()}
        <div>
          <div className="card-attribute">{card.name}</div>
          <div className="card-attribute">{card.types}</div>
          {getText()}
          {card.power == null ? (
            <></>
          ) : (
            <div>{card.power + "/" + card.toughness}</div>
          )}
        </div>
      </div>
    );
  }

  return getDetails();
}
