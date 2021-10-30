import React from 'react'
import './App.css';
import Navbar from "./components/navbar";
import Popup from "./Popup";

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
        <div>
          <button class="btn" onClick={this.togglePop}>Music and Song suggestions</button>
        </div>
        <div>
          {this.state.seen ? <Popup toggle={this.togglePop} /> : null}
        </div>
      </div>
    );
  }
}


// export default App;