import React, {Component} from 'react';
import MapView from 'react-native-maps';
import {StyleSheet, View} from 'react-native';
import * as ActionFactory from '../actions/ActionFactory';

const styles = StyleSheet.create({
    map: {
        flex: 1
    },
});

export default class Map extends Component {

    constructor(props) {
        super(props);
        this.onMapLongPress = this._onMapLongPress.bind(this);
    }

    _onMapLongPress(e) {
        const {dispatch} = this.props;
        const destination = e.nativeEvent.coordinate;
        navigator.geolocation.getCurrentPosition(
            (position) => {
                dispatch(ActionFactory.createOffer(position, destination, this.props));
            }
        )
    }

    renderMarker(marker) {
        return (
            <MapView.Marker
                key={marker.id}
                coordinate={{
                    longitude: marker.endLocation.longitude,
                    latitude: marker.endLocation.latitude
                }}
            />
        )
    }

    render() {
      const lat = this.props.start ? this.props.start.lat : 48.781839;
      const long = this.props.start ? this.props.start.lng : 9.177895;
        return (
            <MapView
                style={styles.map}
                onLongPress={this.onMapLongPress}
                showsUserLocation={true}
                initialRegion={{
                    latitude: lat,
                    longitude: long,
                    latitudeDelta: 0.0922,
                    longitudeDelta: 0.0421,
                }}
            >
                {this.props.markers.map(this.renderMarker)}
            </MapView>
        );
    }
}
