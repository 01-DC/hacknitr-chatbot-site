import React from "react";
import "./index.css";

function Navbar() {
    return (
        <div className="nav">
            <div className="logo">
                <h1>Comfy</h1>
            </div>
            <div className="menu">
                <button className="menu-item" href="#">sign up</button>
                <button className="menu-item" href="#">log in</button>
            </div>
        </div>
    );
};
export default Navbar;