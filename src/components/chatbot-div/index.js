import React from "react";
import Bot from "./Bot"

export default class chat extends React.Component {
    state = {
        seen: false
      };

      togglePop = () => {
        this.setState({
          seen: !this.state.seen
        });
      };
    
    render() {
        return (
            <div>
              <div style={{backgroundColor: "#CAEFD1", padding: "20px", borderRadius: "10px", boxShadow: "2px 2px 8px 1px rgba(0,0,0,0.4)"}}>
                <h2 style={{fontFamily:"'Macondo', cursive",}}>You can chat with Comfy in the window below!</h2>
                <h2 style={{fontFamily:"'Macondo', cursive",}}>Comfy can recommend you songs based on your mood.</h2>
              </div>
              <Bot />
            </div>
        );
    }
};