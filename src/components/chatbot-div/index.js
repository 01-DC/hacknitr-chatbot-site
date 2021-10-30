import React from "react";
import Popup from "./Popup";


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
                <div style= {{alignItems: "center"}}>
                {this.state.seen ? <Popup toggle={this.togglePop} /> : null}
                </div>
                <button className="btn" onClick={this.togglePop} style={{
                    backgroundColor: "#CAEFD1",
                    border: "none",
                    color: "black",
                    padding: "15px 32px",
                    textAlign: "center",
                    textDecoration: "none",
                    fontSize: "16px",
                }}>Music and Activities recommendation according to your mood</button>
            </div>
        );
    }
};