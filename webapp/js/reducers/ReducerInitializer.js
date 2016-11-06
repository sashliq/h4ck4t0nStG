import {combineReducers} from 'redux';
import userReducer from './UserReducer';
import tripOffers from './TripReducer';
import location from './LocationReducer';

const rootReducer = combineReducers({
    tripOffers,
    location,
    userReducer,
});

export default rootReducer
