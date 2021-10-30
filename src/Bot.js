import React, { Component } from "react";
import { ThemeProvider } from "styled-components";
import ChatBot from 'react-simple-chatbot';

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

const steps = [
  {
    id: '0',
    message: 'Hello! I am Comfy. What is your name?',
    trigger: '1',
  },
  {
    id: '1',
    user: true,
    trigger: '2',
  },
  {
    id: '2',
    message: 'How are you doing {previousValue}?',
  }
];

export default class Bot extends Component {
    render() {
        return (
          <ThemeProvider theme={theme}>
          <ChatBot steps={steps} />;
        </ThemeProvider>
        );
    }
}