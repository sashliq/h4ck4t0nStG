import React, {Component, PropTypes} from 'react';
import {connect} from 'react-redux';
import {View, StatusBar} from 'react-native';

import NavBar from './NavBar.js';
import Map from '../components/Map.js';
import *  as ActionFactory from '../actions/ActionFactory';

const propTypes = {
    offers: PropTypes.array.isRequired
};

const styles = {
    container: {
        flex: 1
    },
};

class App extends Component {

    componentDidMount() {
        const {dispatch} = this.props;
        dispatch(ActionFactory.fetchOffers());
        dispatch(ActionFactory.fetchUsers());
    }

    render() {
        return (
            <View style={styles.container}>
                <StatusBar
                    translucent={true}
                    backgroundColor="rgba(0, 0, 0, 0.2)"
                    barStyle="dark-content"
                />
                <Map
                    dispatch={this.props.dispatch}
                    markers={this.props.offers}
                    users={this.props.users}
                />
                <NavBar />
            </View>
        );
    }
}

App.propTypes = propTypes;

function mapStateToProps(state) {
    const {tripOffers} = state;
    const {userReducer} = state;
    const offers = tripOffers.offers;
    const users = userReducer.users;
    return {
        offers,
        users
    };
}

export default connect(mapStateToProps)(App);
