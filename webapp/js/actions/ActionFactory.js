import * as types from '../constants/ActionTypes';
import {buildPath} from '../utils/path.js';
import * as ObjectFactory from '../objects/ObjectFactory';

import { receiveLocation } from './Geocoding.js';

export function fetchOffers() {
    return (dispatch, getState) => {

        dispatch(requestOffers());
        return fetch(buildPath('trip-offers/'))
            .then(response => response.json())
            .then(json => dispatch(receiveOffers(json)))
            .catch(error => dispatch(receiveOffers([])));
    }
}


export function fetchUsers() {
    return (dispatch, getState) => {
        return fetch(buildPath('users/'))
            .then(response => response.json())
            .then(json => dispatch(receiveUsers(json)));
        // .catch(error => console.error(error));
    }
}


export function receiveOffers(entities) {
    return {
        type: types.RECEIVE_OFFERS,
        entities
    }
}
export function receiveUsers(entities) {
    return {
        type: types.RECEIVE_USERS,
        entities
    }
}


function requestOffers() {
    return {
        type: types.REQUEST_OFFERS
    };
}


export function createOffer(start, destination,props) {
    return (dispatch, getState) => {
        dispatch(createOfferRequest());
        return fetch(buildPath('trip-offers/'), {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(ObjectFactory.createNewTripOffer(start, destination,props.users[0]))
        })
            .then(response => response.json())
            .then(offer => dispatch(receiveLocation(offer.endLocation)))
            .catch(error => console.error(error));
    }
}


function createOfferRequest() {
    return {
        type: types.CREATE_OFFER
    };
}


export function addOffer(offer) {
    return {
        type: types.ADD_OFFER,
        offer
    }
}
