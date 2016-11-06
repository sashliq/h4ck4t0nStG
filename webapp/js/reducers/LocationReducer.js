import * as types from '../constants/ActionTypes';

const initialState = {
  current: null
};

export default function location(state = initialState, action) {
    switch (action.type) {
        case types.RECEIVE_LOCATION:
            return Object.assign({}, state, {
              current: action.location
            });
            break;
        case types.NO_LOCATION_FOUND:
            return Object.assign({}, state, {
              current: null
            });
        default:
            return state
    }
}
