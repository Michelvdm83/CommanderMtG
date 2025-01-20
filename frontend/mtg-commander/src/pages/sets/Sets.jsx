import axios from "axios";
import { useEffect, useState } from "react";
import "./Sets.css";
import Set from "../../components/Set/Set";

export default function Sets() {
    const [sets, setSets] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8080/sets`).then((response) => {
            let allS = response.data;
            allS.sort(function (a, b) {
                return new Date(b.release) - new Date(a.release);
            });
            setSets(allS);
        });
    }, []);
    return (
        <div className="sets-list">
            {sets.length > 0 &&
                sets.map((set) => <Set set={set} key={set.code} />)}
        </div>
    );
}

/*
String code, String name, LocalDate release, Boolean inDatabase
*/
