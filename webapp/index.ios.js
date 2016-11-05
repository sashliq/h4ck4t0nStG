/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import { AppRegistry } from 'react-native';

import Main from './js/main.js';

export default class RideWithMe extends Component {

  render() {
    return (
      <Main />
    );
  }
}

AppRegistry.registerComponent('RideWithMe', () => RideWithMe);
