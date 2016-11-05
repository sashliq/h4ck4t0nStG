import * as types from '../constants/ActionTypes';
import {buildPath} from '../utils/path.js';

export function fetchOffers() {
    return (dispatch, getState) => {

        dispatch(requestOffers());
        return fetch(buildPath('trip-offers/'))
            .then(response => response.json())
            .then(json => dispatch(receiveOffers(json)));
        // .catch(error => console.error(error));
    }
}

function createNewTripIffer(endCoordinates, startCoordinater) {
    return {
        "createdOn": {},
        "endLocation": {
            "latitude": endCoordinates.latitude,
            "longitude": endCoordinates.longitude,
            "rangeInMeters": 0
        },
        "startLocation": {
            "latitude": 0,
            "longitude": 0,
            "rangeInMeters": 0
        }
    }

}

export function createOffer(coordinate) {
    return (dispatch, getState) => {
        console.log(coordinate);
        dispatch(createOfferRequest());
        return fetch(buildPath('trip-offers/'), {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(createNewTripOffer(endCoordinates, startCoordinates))
        }).then((response) => {
            dispatch(fetchOffers());
            console.log(response)
        }).catch((error)=> {
            console.log(error)
        })
    }
}

export function receiveOffers(entities) {
    return {
        type: types.RECEIVE_OFFERS,
        entities
    }
}
//
// export function createOffer(coordinate) {
//     return {
//         type: types.CREATE_OFFER,
//         offer: {
//             endLocation: coordinate
//         }
//     }
// }

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
