import React from 'react'
import Navbar from "./components/navbar";
import Popup from "./Popup";
import image1 from './assets/Man_Head_Silhouette.png'

export default class App extends React.Component {
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
        <Navbar />
        <div style={{backgroundColor: "#FCFFE9", height: "100vh", padding: "20px", display: "flex",}}>
          <div >
            <img src={image1} alt="think-head" style={{height: "350px"}}/>
            <img src={image1} alt="think-head" style={{height: "350px"}}/>
          </div>
          <div>
            <div style= {{float: "right"}}>
              {this.state.seen ? <Popup toggle={this.togglePop} /> : null}
            </div>
            <button class="btn" onClick={this.togglePop} style={{
                backgroundColor: "#CAEFD1",
                border: "none",
                color: "black",
                padding: "15px 32px",
                textAlign: "center",
                textDecoration: "none",
                fontSize: "16px",
            }}>Music and Activities recommendation according to your mood</button>
          </div>
        </div>
      </div>
    );
  }
}


// export default App;