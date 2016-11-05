import React, { Component } from 'react';
import { StyleSheet, View, TextInput} from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';

const styles = StyleSheet.create({
  input: {
    height: 50,
    fontSize: 16,
  },
});

export default class DestinationInput extends Component {

  constructor(props) {
    super(props);
  }

  render() {
    return (
        <TextInput
          style={styles.input}
          onChangeText={(text) => this.setState({text})}
          value={this.props.text}
          placeholder={'Enter location or tap on map...'}
        />
    )
  }
}
