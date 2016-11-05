import React, { Component } from 'react';
import { Provider } from 'react-redux';

import App from './containers/App';
import configureStore from './store/configureStore';

const initialState = {
  tripOffers: {
    offers: [],
  },
};

const store = configureStore(initialState);

class Main extends Component {

  render() {
    return (
      <Provider store={store}>
        <App />
      </Provider>
    );
  }

}

export default Main;
