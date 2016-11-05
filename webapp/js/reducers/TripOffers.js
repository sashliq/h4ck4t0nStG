import { combineReducers } from 'redux';
import * as types from '../constants/ActionTypes';

const initialState = {
  offers: []
}

function tripOffers(state = initialState, action) {
  switch (action.type) {
    case types.RECEIVE_OFFERS:
      return Object.assign({}, state, {
        offers: [...state.offers, ...action.entities]
      });
      break;
    default:
      return state

  }
}

const rootReducer = combineReducers({
  tripOffers
})

export default rootReducer
