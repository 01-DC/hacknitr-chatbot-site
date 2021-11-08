import React, { Component } from "react";
import Bot from './Bot'

export default class Popup extends Component {
  handleClick = () => {
    this.props.toggle();
  };

  render() {
    return (
    <Bot />
    );
  }
}