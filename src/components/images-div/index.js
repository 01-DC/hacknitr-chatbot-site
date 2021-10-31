import React from 'react';
import image1 from './Man_Head_Silhouette.png'
import image2 from './heart.png'

export default class imagediv extends React.Component {
    render() {
        return (
          <div style={{display: "flex-column"}}>
            <div>
              <img src={image1} alt="think-head" style={{height: "350px", margin: "1vh"}}/>
            </div>
            <div>
              <img src={image2} alt="heart.png" style={{height: "250px"}}/>
            </div>
          </div>
        );
    }
}