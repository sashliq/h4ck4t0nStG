import React, { Component } from 'react';
import { StyleSheet, View, TextInput} from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';
import _ from 'lodash';

import { fetchLocation, noLocationFound } from '../actions/Geocoding.js';

const styles = StyleSheet.create({
  input: {
    height: 50,
    fontSize: 16,
    paddingLeft: 10,
    paddingRight: 10,
  },
});

export default class DestinationInput extends Component {

  constructor(props) {
    super(props);
    this.onChangeText = _.debounce(this._onChangeText.bind(this), 1000);
  }

  _onChangeText(text) {
    const { dispatch } = this.props;
    if (text.length > 5) {
      dispatch(fetchLocation(text));
    } else {
      dispatch(noLocationFound());
    }
  }

  render() {
    return (
        <TextInput
          style={styles.input}
          onChangeText={this.onChangeText}
          value={this.props.text}
          placeholder={'Enter location or tap on map...'}
        />
    )
  }
}
