/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import { AppRegistry } from 'react-native';

import Home from './js/home.js';

export default class RideWithMe extends Component {

  render() {
    return (
      <Home />
    );
  }
}

AppRegistry.registerComponent('RideWithMe', () => RideWithMe);
