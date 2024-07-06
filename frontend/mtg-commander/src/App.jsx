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

  const [messages, setMessages] = useState([]);
  let eventSourceCreated = false;
  let eventSource = null;

  useEffect(() => {
    if (eventSourceCreated || eventSource !== null) {
      return;
    }
    eventSource = new EventSource("http://localhost:8080/subscription");
    eventSource.addEventListener("message", (event) => updateMessages(event));
    // eventSource.onmessage = (event) => updateMessages(event);
    eventSource.addEventListener("error", () => eventSource.close());
    // eventSource.onerror = () => eventSource.close();
    eventSourceCreated = true;
  }, []);

  document.onvisibilitychange = (event) => {
    //   if (document.hidden) {
    //     if (eventSource !== null) {
    //       // eventSource.onmessage = null;
    //       // // eventSource.onerror = null;
    //       // eventSource.removeEventListener("message", (event) =>
    //       //   updateMessages(event)
    //       // );
    //       // eventSource.removeEventListener("error", () => eventSource.close());
    console.log(event);
  };
  //   } else {
  //     if (eventSource !== null) {
  //       // eventSource.onmessage = null;
  //       // eventSource.onerror = null;
  //       eventSource.removeEventListener("message", (event) =>
  //         updateMessages(event)
  //       );
  //       eventSource.removeEventListener("error", () => eventSource.close());
  //       eventSource.close();
  //       // eventSource = null;
  //     } else {
  //       eventSource = new EventSource("http://localhost:8080/subscription");
  //     }
  //     eventSource.addEventListener("message", (event) => updateMessages(event));
  //     // eventSource.onmessage = (event) => updateMessages(event);
  //     eventSource.addEventListener("error", () => eventSource.close());
  //     // eventSource.onerror = () => eventSource.close();
  //   }
  //   console.log(eventSource);
  // };

  function updateMessages(event) {
    setMessages((messages) => [...messages, event.data]);
  }

  useEffect(() => {}, [messages]);

  useEffect(() => {
    if (nameSearch.length < 3) {
      return;
    }

    axios
      .get(`http://localhost:8080?name=${nameSearch}`)
      .then((response) => {
        setCards(response.data);
      })
      .catch((error) => {
        console.log(error);
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
