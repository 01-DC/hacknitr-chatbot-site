import React, { Component } from "react";
import ChatBot from 'react-simple-chatbot';

const steps = [
  {
    id: '0',
    message: 'Welcome to react chatbot!',
    trigger: '1',
  },
  {
    id: '1',
    message: 'Bye!',
    end: true,
  },
];

export default class Bot extends Component {
    render() {
        return (
            <ChatBot steps= {steps} />
        );
    }
}