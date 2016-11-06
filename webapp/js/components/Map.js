import React, {Component} from 'react';
import MapView from 'react-native-maps';
import {StyleSheet, View, Image} from 'react-native';
import * as ActionFactory from '../actions/ActionFactory';
import markerImgOrange from '../../img/marker_icon_orange.png';
import markerImgGreen from '../../img/marker_icon_green.png';
import markerImgGrey from '../../img/marker_icon_grey.png';

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

    renderCurrent(current) {
      if (!current) {
        return;
      }
      const markerImg = current.selected ? markerImgGreen : markerImgGrey;
      return (
          <MapView.Marker
              key={'current'}
              image={markerImg}
              title={'Selected destination'}
              coordinate={{
                  longitude: current.longitude,
                  latitude: current.latitude
              }}
          />
      )
    }

    renderMarker(marker) {
        return (
            <MapView.Marker
                key={marker.id}
                image={markerImgOrange}
                title={'Departure in 15 minutes'}
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
                {this.renderCurrent(this.props.start)}
            </MapView>
        );
    }
}
