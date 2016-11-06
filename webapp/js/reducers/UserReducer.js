import * as types from '../constants/ActionTypes';


const initialState = {
    users: []
};

export default function userReducer(state = initialState, action) {
    switch (action.type) {
        case types.RECEIVE_USERS:
            //hack :|
            return Object.assign({}, state, {
                offers: [...action.entities]
            });
            break;
        default:
            return state
    }
}
