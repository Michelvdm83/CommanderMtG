import { useState } from "react";
import "./Set.css";

export default function Set({ set }) {
    const [inDB, setInDB] = useState(set.inDatabase);
    return (
        <div className="set">
            <div className="set-code">{set.code}</div>
            <div>{set.name}</div>
            {inDB && <div className="set-indb">in database</div>}
            {!inDB && <div className="set-not-indb">add</div>}
        </div>
    );
}
/*
String code, String name, LocalDate release, Boolean inDatabase
*/
