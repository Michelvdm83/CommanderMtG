import { useState } from "react";
import "./App.css";
import { Routes, Route, useNavigate } from "react-router-dom";

import Cards from "./pages/cards/Cards";
import NameSearch from "./components/NameSearch";
import CardDetail from "./pages/CardDetail/CardDetail";
import Sets from "./pages/sets/Sets";

function App() {
    const navigate = useNavigate();

    //const [cards, setCards] = useState([]);
    const [nameSearch, setNameSearch] = useState("");

    return (
        <div className="container">
            <div className="nav-bar">
                <h1 className="nav-bar-item" onClick={() => navigate("/")}>
                    Home
                </h1>
                <h1 className="nav-bar-item" onClick={() => navigate("/sets")}>
                    Sets
                </h1>
                <h1
                    className="nav-bar-item"
                    onClick={() => navigate("/search")}
                >
                    Cardsearch
                </h1>
                {nameSearch.length > 1 ? (
                    <h1
                        className="nav-bar-item"
                        onClick={() => navigate("/cards")}
                    >
                        Search Result
                    </h1>
                ) : (
                    <></>
                )}
                <h1 className="nav-bar-item">
                    <NameSearch
                        nameSearch={nameSearch}
                        setNameSearch={setNameSearch}
                    />
                </h1>
            </div>
            <Routes>
                <Route path="/" element={<></>} />
                <Route
                    path="/cards"
                    element={
                        <Cards
                            /*cards={cards} setCards={setCards}*/ nameSearch={
                                nameSearch
                            }
                        />
                    }
                />
                <Route
                    path="/search"
                    element={
                        <NameSearch
                            nameSearch={nameSearch}
                            setNameSearch={setNameSearch}
                        />
                    }
                />
                <Route path="/sets" element={<Sets />} />
                <Route path="/cards/:id" element={<CardDetail />} />
            </Routes>
        </div>
    );
}

export default App;
