import React from 'react';

export default class textBox extends React.Component {
    render() {
        return (
            <div>
                <div style={{backgroundColor: "#CAEFD1", padding: "20px", borderRadius: "10px", boxShadow: "2px 2px 8px 1px rgba(0,0,0,0.4)"}}>
                    <h2 style={{fontFamily:"'Macondo', cursive",}}>You can chat with Alexia after logging in using Google Account. (Powered by Auth0)</h2>
                    <h2 style={{fontFamily:"'Macondo', cursive",}}>Alexia can recommend you songs based on your mood!</h2>
                </div>
            </div>
        );
    }
}