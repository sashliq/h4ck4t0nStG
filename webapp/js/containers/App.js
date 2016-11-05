import React, { Component, PropTypes } from 'react';
import { connect } from 'react-redux';

import Map from '../components/Map.js';
import { fetchOffers } from '../actions/TripOffers.js';

const propTypes = {
  offers: PropTypes.array.isRequired
};

class App extends Component {

  componentDidMount() {
    const { dispatch } = this.props;
    dispatch(fetchOffers());
  }

  render() {
    return (
      <Map
      dispatch={this.props.dispatch}
      markers={this.props.offers}
      />
    );
  }
}

App.propTypes = propTypes;

function mapStateToProps(state) {
  const { tripOffers } = state;
  const offers = tripOffers.offers;
  return {
    offers
  };
}

export default connect(mapStateToProps)(App);
