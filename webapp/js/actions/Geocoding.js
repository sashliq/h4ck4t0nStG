import * as types from '../constants/ActionTypes.js';
import { buildGeocodingPath } from '../utils/path.js';

export function fetchLocation(address) {
  return dispatch => {
    return fetch(buildGeocodingPath(address))
      .then(response => response.json())
      .then(result => result.status === 'OK' ? result.results : [])
      .then(results => results.length > 0 ? dispatch(receiveLocation(results[0].geometry.location)) : dispatch(noLocationFound()))
      .catch(error => console.error(error));
    }
};

function receiveLocation(location) {
  return {
    type: types.RECEIVE_LOCATION,
    location
  }
}

export function noLocationFound() {
  return {
    type: types.NO_LOCATION_FOUND
  }
}
