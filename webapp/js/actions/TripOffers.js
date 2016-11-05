import * as types from '../constants/ActionTypes';
import {buildPath} from '../utils/path.js';

export function fetchOffers() {
    return (dispatch, getState) => {

        dispatch(requestOffers());
        return fetch(buildPath('trip-offers/'))
            .then(response => response.json())
            .then(json => dispatch(receiveOffers(json)));
    }
}

function createNewTripOffer(start, destination) {
    return {
        "createdOn": {},
        "endLocation": {
            "latitude": destination.latitude,
            "longitude": destination.longitude,
            "rangeInMeters": 0
        },
        "startLocation": {
            "latitude": start.latitude,
            "longitude": start.longitude,
            "rangeInMeters": 0
        }
    }

}

export function createOffer(start, destination) {
    return (dispatch, getState) => {
        dispatch(createOfferRequest());
        return fetch(buildPath('trip-offers/'), {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(createNewTripOffer(start, destination))
        })
        .then(response => response.json())
        .then(offer => dispatch(addOffer(offer)))
        .catch(error => console.error(error));
    }
}

export function receiveOffers(entities) {
    return {
        type: types.RECEIVE_OFFERS,
        entities
    }
}

export function addOffer(offer) {
  return {
    type: types.ADD_OFFER,
    offer
  }
}

function requestOffers() {
    return {
        type: types.REQUEST_OFFERS
    };
}

function createOfferRequest() {
    return {
        type: types.CREATE_OFFER
    };
}
