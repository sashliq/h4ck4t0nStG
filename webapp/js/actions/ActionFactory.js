import * as types from '../constants/ActionTypes';
import {buildPath} from '../utils/path.js';
import * as ObjectFactory from '../objects/ObjectFactory';


export function fetchOffers() {
    return (dispatch, getState) => {

        dispatch(requestOffers());
        return fetch(buildPath('trip-offers/'))
            .then(response => response.json())
            .then(json => dispatch(receiveOffers(json)));
        // .catch(error => console.error(error));
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


export function createOffer(start, destination) {
    return (dispatch, getState) => {
        dispatch(createOfferRequest());
        return fetch(buildPath('trip-offers/'), {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(ObjectFactory.createNewTripOffer(start, destination))
        })
            .then(response => response.json())
            .then(offer => dispatch(addOffer(offer)))
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