import * as types from '../constants/ActionTypes';


const initialState = {
    user: []
};

export default function userReducer(state = initialState, action) {
    switch (action.type) {
        case types.RECEIVE_USERS:
            return Object.assign({}, state, {
                offers: [...action.entities]
            });
            break;
        default:
            return state
    }
}
