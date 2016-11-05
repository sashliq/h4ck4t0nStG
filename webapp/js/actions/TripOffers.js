import * as types from '../constants/ActionTypes';
import { buildPath } from '../utils/path.js';

export function fetchOffers() {
  return (dispatch, getState) => {

    dispatch(requestOffers());
    return fetch(buildPath('trip-offers/'))
      .then(response => response.json())
      .then(json => dispatch(receiveOffers(json)));
      // .catch(error => console.error(error));
  }
}

export function receiveOffers(entities) {
  return {
    type: types.RECEIVE_OFFERS,
    entities
  }
}

function requestOffers() {
  return {
    type: types.REQUEST_OFFERS
  };
}
