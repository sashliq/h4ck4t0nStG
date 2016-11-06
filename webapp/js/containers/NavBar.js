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
import { setLocation, unsetLocation } from '../actions/Geocoding.js';

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
  buttonRed: {
    alignItems: 'center',
    backgroundColor: '#A4172E',
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
    const { dispatch, current } = this.props;
    if (!current) {
      return;
    }
    if (current.selected) {
      dispatch(unsetLocation())
    } else {
      dispatch(setLocation())
    }
  }

  renderButton() {
    const hasDestination = !!this.props.current;
    const isSelected = this.props.current && this.props.current.selected;
    let style = styles.buttonGray;
    let text = 'FIND A RIDE';
    if (hasDestination && isSelected) {
      style = styles.buttonRed;
      text = 'ABORT SEARCH';
    } else if (hasDestination) {
      style = styles.buttonBlue;
    }
    return (
      <TouchableHighlight
        style={ styles.button, style }
        onPress={this.onPressButton.bind(this)}>
        <Text style={styles.buttonText}>{text}</Text>
      </TouchableHighlight>
    );
  }

  render() {
      return (
          <View style={styles.container}>
              {this.renderButton()}
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
  return {
    offers,
    current,
  };
}

export default connect(mapStateToProps)(NavBar);
