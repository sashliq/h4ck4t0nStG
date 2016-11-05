import {combineReducers} from 'redux';
import userReducer from './UserReducer';
import tripOffers from './TripReducer';

const rootReducer = combineReducers({
    tripOffers,
    userReducer
});

export default rootReducer
