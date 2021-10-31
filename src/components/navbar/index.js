import React from "react";
import { useAuth0 } from "@auth0/auth0-react";
import AuthenticateButton from './authenticate'
import "./index.css";

function Navbar() {
    const { loginWithRedirect } = useAuth0();
    return (
        <div className="nav">
            <div className="logo">
                <h1>Comfy</h1>
            </div>
            <div className="menu">
                <button className="menu-item" onClick={() => loginWithRedirect({ screen_hint: 'signup',})}>Sign up</button>
                <AuthenticateButton />
            </div>
        </div>
    );
};
export default Navbar;