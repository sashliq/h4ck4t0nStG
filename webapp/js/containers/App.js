import React, { Component, PropTypes } from 'react';
import { connect } from 'react-redux';

import Map from '../components/Map.js';
import { fetchOffers } from '../actions/TripOffers.js';

const propTypes = {
  offers: PropTypes.array.isRequired
};

const defaultProps = {
  offers: []
};

class App extends Component {

  componentDidMount() {
    const { dispatch } = this.props;
    dispatch(fetchOffers());
  }

  render() {
    return (
      <Map />
    );
  }
}

App.propTypes = propTypes;
App.defaultProps = defaultProps;

function mapStateToProps(state) {
  const { offers } = state;
  return {
    offers
  };
}

export default connect(mapStateToProps)(App);
