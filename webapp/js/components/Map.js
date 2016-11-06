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
        let mapProperties = {
            style: styles.map,
            onLongPress: this.onMapLongPress,
            showsUserLocation: true,
            initialRegion: {
                latitude: 48.781839,
                longitude: 9.177895,
                latitudeDelta: 0.0922,
                longitudeDelta: 0.0421,
            },
        };
        if (this.props.start) {
          mapProperties = Object.assign(mapProperties, {
            region: {
              latitudeDelta: 0.00922,
              longitudeDelta: 0.00421,
              ...this.props.start,
            },
          });
        }
        return (
            <MapView {...mapProperties}>
                {this.props.markers.map(this.renderMarker)}
            </MapView>
        );
    }
}
