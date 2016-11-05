import React, { Component } from 'react';
import MapView from 'react-native-maps';
import { StyleSheet, View } from 'react-native';

import { createOffer } from '../actions/TripOffers.js';

const styles = StyleSheet.create({
  container: {
    ...StyleSheet.absoluteFillObject,
    justifyContent: 'flex-end',
    alignItems: 'center',
  },
  map: {
    position: 'absolute',
    top: 0,
    left: 0,
    right: 0,
    bottom: 0,
  },
});

export default class Map extends Component {

  constructor(props) {
    super(props);
    this.onMapLongPress = this.onMapLongPress.bind(this);
  }

  onMapLongPress(e) {
    const { dispatch } = this.props;
    dispatch(createOffer(e.nativeEvent.coordinate));
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
    return (
      <View style={styles.container}>
        <MapView
        style={styles.map}
        onLongPress={this.onMapLongPress}
        showsUserLocation={true}
        followsUserLocation={true}
        initialRegion={{
          latitude: 48.781839,
          longitude: 9.177895,
          latitudeDelta: 0.0922,
          longitudeDelta: 0.0421,
        }}
        >
        {this.props.markers.map(this.renderMarker)}
        </MapView>
      </ View>

    );
  }
}
