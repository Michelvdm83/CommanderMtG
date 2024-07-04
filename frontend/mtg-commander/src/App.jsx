import { useState, useEffect } from "react";
import axios from "axios";
import "./App.css";
import { Routes, Route, useNavigate } from "react-router-dom";

import Cards from "./pages/cards/Cards";
import NameSearch from "./components/NameSearch";
import CardDetail from "./pages/CardDetail/CardDetail";
import Home from "./pages/home/Home";

function App() {
  const navigate = useNavigate();

  const [cards, setCards] = useState([]);
  const [nameSearch, setNameSearch] = useState("");

  const [messages, setMessages] = useState(["first", "second"]);
  const eventSource = new EventSource("http://localhost:8080/subscription");
  eventSource.addEventListener("message", (event) => {
    console.log(event.data);
    setMessages([...messages, event.data]);
  });

  useEffect(() => {
    console.log(nameSearch);
    if (nameSearch.length < 3) {
      console.log(nameSearch + ", in if");
      return;
    }
    console.log(2);

    axios
      .get(`http://localhost:8080?name=${nameSearch}`)
      .then((response) => {
        setCards(response.data);
        console.log("check");
      })
      .catch((error) => {
        console.log(error);
        console.log(3);
      });
  }, [nameSearch]);

  return (
    <div className="container">
      <div className="nav-bar">
        <h1 className="nav-bar-item" onClick={() => navigate("/")}>
          Home
        </h1>
        <h1 className="nav-bar-item" onClick={() => navigate("/search")}>
          Cardsearch
        </h1>
        {nameSearch.length > 1 ? (
          <h1 className="nav-bar-item" onClick={() => navigate("/cards")}>
            Search Result
          </h1>
        ) : (
          <></>
        )}
        <h1 className="nav-bar-item search">
          <NameSearch nameSearch={nameSearch} setNameSearch={setNameSearch} />
        </h1>
      </div>
      <Routes>
        <Route path="/" element={<Home messages={messages} />} />
        <Route
          path="/cards"
          element={
            <Cards cards={cards} setCards={setCards} nameSearch={nameSearch} />
          }
        />
        <Route
          path="/search"
          element={
            <NameSearch nameSearch={nameSearch} setNameSearch={setNameSearch} />
          }
        />
        <Route path="/cards/:id" element={<CardDetail />} />
      </Routes>
    </div>
  );
}

export default App;
