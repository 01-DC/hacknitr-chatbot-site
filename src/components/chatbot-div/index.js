import React from "react";
import Bot from "./Bot"
import TextBox from "./Text-Box"
import { useAuth0 } from "@auth0/auth0-react";

const Chat = () => {
    const { isAuthenticated } = useAuth0();

    return isAuthenticated ? <Bot /> : <TextBox />;
};

export default Chat;