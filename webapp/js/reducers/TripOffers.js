import { combineReducers } from 'redux';
import * as types from '../constants/ActionTypes';

const initialState = {
  offers: []
}

function tripOffers(state = initialState, action) {
  switch (action.type) {
    case types.RECEIVE_OFFERS:
      return Object.assign({}, state, {
        offers: [...action.entities]
      });
      break;
    case types.CREATE_OFFER:
      return Object.assign({}, state, {
        offers: [...state.offers, action.offer]
      });
    default:
      return state
  }
}

const rootReducer = combineReducers({
  tripOffers,
  initialState
})

export default rootReducer
