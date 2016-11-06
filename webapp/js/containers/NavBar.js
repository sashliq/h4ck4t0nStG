import React, { Component, PropTypes } from 'react';
import { connect } from 'react-redux';
import {
  StyleSheet,
  View,
  TextInput,
  TouchableHighlight,
  Text
} from 'react-native';

import DestinationInput from '../components/DestinationInput.js';

const propTypes = {
  offer: PropTypes.object
};

const styles = StyleSheet.create({
  container: {
    height: 100,
  },
  button: {
    height: 50,
    alignItems: 'center',
  },
  buttonGray: {
    alignItems: 'center',
    backgroundColor: '#D0D0D0',
  },
  buttonBlue: {
    alignItems: 'center',
    backgroundColor: '#00A0E1',
  },
  buttonText: {
    flex: 1,
    height: 50,
    lineHeight: 50,
    alignItems: 'center',
    color: 'white',
    fontSize: 18,
  },
});

class NavBar extends Component {

  constructor(props) {
    super(props);
  }

  onPressButton() {

  }

  render() {
    return (
      <View style={styles.container}>
        <TouchableHighlight
          style={styles.button, this.props.hasDestination ? styles.buttonBlue : styles.buttonGray}
          onPress={this.onPressButton}>
          <Text style={styles.buttonText}>{'FIND A RIDE'}</Text>
        </TouchableHighlight>
        <DestinationInput current={this.props.current} dispatch={this.props.dispatch}/>
      </View>
    );
  }
}

NavBar.propTypes = propTypes;

function mapStateToProps(state) {
  const { tripOffers, location} = state;
  const offers = tripOffers.offers;
  const current = location.current;
  const hasDestination = !!current;
  return {
    offers,
    hasDestination,
    current,
  };
}

export default connect(mapStateToProps)(NavBar);
