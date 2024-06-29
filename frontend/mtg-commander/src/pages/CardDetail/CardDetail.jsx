import { useParams } from "react-router-dom";
import NameSearch from "../../components/NameSearch";
import { useEffect, useState } from "react";
import axios from "axios";
import "./CardDetail.css";

export default function CardDetail(){
    const { id } = useParams();
    const [card, setCard] = useState(null);

    useEffect(() => {
        axios.get(`http://localhost:8080/${id}`).then((response) => setCard(response.data)).catch((error) => console.log(error));
    }, []);

    function getDetails(){
        if(card == null){
            return <></>;
        }
        return (
            <div className="card-detail">
                <img src={card.imageURL} width={300} height={450}/>
                <div>
                    <div>{card.name}</div>
                    <div>{card.type_line}</div>
                    <div>{card.oracle_text}</div>
                    {card.power == null? <></> : <div>{card.power + "/" + card.toughness}</div>}
                </div>
            </div>
        );
    }

    return (
        <>
            { getDetails() }
        </>
    );
}