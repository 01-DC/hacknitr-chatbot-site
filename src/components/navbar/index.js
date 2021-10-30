import React from "react";
import "./index.css";

function Navbar() {
    return (
        <div class="nav">
            <h1 class="logo">Comfy</h1>
            <div class="menu">
                <button class="menu-item" href="#">sign up</button>
                <button class="menu-item" href="#">log in</button>
            </div>
        </div>
    );
};
export default Navbar;