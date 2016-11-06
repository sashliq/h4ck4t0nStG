import * as types from '../constants/ActionTypes.js';
import { buildGeocodingPath } from '../utils/path.js';

export function fetchLocation(address) {
  return dispatch => {
    return fetch(buildGeocodingPath(address))
      .then(response => response.json())
      .then(result => {
        const results = result.status === 'OK' ? result.results : [];
        const location = results.length > 0 ? results[0].geometry.location : null;
        location ? dispatch(receiveLocation({ latitude: location.lat, longitude: location.lng })) : dispatch(noLocationFound());
      })
      .catch(error => console.error(error));
    }
};

export function setLocation() {
  return {
    type: types.SET_LOCATION,
  }
}

export function unsetLocation() {
  return {
    type: types.UNSET_LOCATION,
  }
}


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
