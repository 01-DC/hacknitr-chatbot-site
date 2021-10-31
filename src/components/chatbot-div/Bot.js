import React, { Component } from "react";
import { ThemeProvider } from "styled-components";
import ChatBot from 'react-simple-chatbot';
import Logo from "./icon.png"
// import axios from 'axios';

const theme = {
  background: '#f5f8fb',
  fontFamily: 'Arial',
  headerBgColor: '#EF6C00',
  headerFontColor: '#fff',
  headerFontSize: '15px',
  botBubbleColor: '#EF6C00',
  botFontColor: '#fff',
  userBubbleColor: '#fff',
  userFontColor: '#4a4a4a',
};

// var emotion= "";

// function Botresponse(val) {

//     axios.get(`http://localhost:5000/pred/"${val}"`).then(response => {
//       console.log("SUCCESS", response)

//     }).catch(error => {
//       console.log(error)

//   })

//   fetch(`/pred/"${val}"`)
//               .then(response => {
//                 console.log(response)
//               }).catch(err=>{
//                   console.log(err);
//               })

//   return (
//     `${emotion}`
//   );
// }

function generateResponse(args) {
  var val= String(args).toLowerCase();
      if(val.includes("hello"))
      {
          switch(Math.floor(Math.random()*2))
          {
            case 0: return "Hello there! Nice to meet you";
            case 1: return "Hi! So nice to meet you";
            case 2: return "Buongiorno! Nice to meet you";
            default: return "Error"
          }
      }
      else if(val.includes("how are you"))
      {
        switch(Math.floor(Math.random()*2))
          {
            case 0: return "I'm doing fine:) Thanks! Would you like me recommend some songs?";
            case 1: return "I'm hungry:( Would you like me recommend some songs?";
            case 2: return "Pretty good:) Would you like me recommend some songs?";
            default: return "Error"
          }
      }
      else
      {
        switch(Math.floor(Math.random()*2))
          {
            case 0: return "Please ask something else";
            case 1: return "Try asking me something different";
            case 2: return "Is there anything else I can do?";
            default: return "Error"
          }
      }
}

const steps = [
  {
    id: '0',
    message: 'Hello! I\'m Alexia, your personal companion. I\'ll recommend you songs based on your mood.',
    trigger: '1',
  },
  {
    id: '1',
    user: true,
    trigger: '2',
  },
  {
    id: '2',
    message: ({previousValue, steps}) => {
      return generateResponse(previousValue);
    },
    trigger: '3',
  },
  {
    id: '3',
    user: true,
    trigger: '4',
  },
  {
    id: '4',
    message: ({previousValue, steps}) => {
      return generateResponse(previousValue);
    },
    trigger: '5',
  },
  {
    id: '5',
    user: true,
    trigger: ({value, steps}) => {
      var val= String(value).toLowerCase();
      console.log(val);
      if(val.includes("sure") || val.includes("ok") || val.includes("yes") || val.includes("fine"))
        return '7';
      else
        return '6';
    }
  },
  {
    id: '6',
    message: "All good",
    trigger: '1',
  },
  {
    id: '7',
    message: "How are you feeling?",
    trigger: '8',
  },
  {
    id: '8',
    options: [
      { value: 1, label: 'Happy', trigger: '9' },
      { value: 2, label: 'Sad', trigger: '10' },
    ],
  },
  {
    id: '9',
    component: (
      <a href="https://www.w3schools.com/jsref/jsref_random.asp" target="_blank" rel="noreferrer">Here is your song</a>
    ),
    asMessage: true,
    end: true,
  },
  {
    id: '10',
    component: (
      <a href="https://www.w3schools.com/jsref/jsref_random.asp" target="_blank" rel="noreferrer">Here is your song</a>
    ),
    asMessage: true,
    end: true,
  }
];

export default class Bot extends Component {
    render() {
        return (
          <ThemeProvider theme={theme}>
          <ChatBot steps={steps} floating={true} floatingIcon={`${Logo}`} headerTitle={"Comfy ReactJS"} width={"520px"} opened={true}/>;
        </ThemeProvider>
        );
    }
}