import React from 'react'
import Navbar from "./components/navbar";
import Chat from "./components/chatbot-div";
import Imagediv from "./components/images-div"


export default class App extends React.Component {
  render() {
    return (
      <div>
        <div>
          <Navbar />
        </div>
        <div style={{backgroundColor: "#FCFFE9", height: "100vh", padding: "20px", display: "flex",}}>
          <Imagediv />
          <Chat />
        </div>
      </div>
    );
  }
}


// export default App;