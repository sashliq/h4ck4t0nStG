import * as types from '../constants/ActionTypes';

const initialState = {
    offers: []
};

export default function tripReducer(state = initialState, action) {
    switch (action.type) {
        case types.RECEIVE_OFFERS:
            return Object.assign({}, state, {
                offers: [...action.entities]
            });
            break;
        case types.CREATE_OFFER:
            return state;
            break;
        default:
            return state
    }
}
