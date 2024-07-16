import { useState } from "react";
import "./NameSearch.css";
import { useNavigate } from "react-router-dom";

export default function ({ nameSearch, setNameSearch }) {
  const navigate = useNavigate();
  const [newSearch, setNewSearch] = useState(nameSearch);

  function handleSearch() {
    setNameSearch(newSearch);
    navigate("/cards");
  }

  function handleChange(event) {
    setNewSearch(event.target.value);
  }

  return (
    <div className="name-searcher">
      <input
        className="name-input"
        type="text"
        size={10}
        onChange={(event) => handleChange(event)}
        value={newSearch}
      />
      <button className="name-search" onClick={handleSearch}>
        search
      </button>
    </div>
  );
}
