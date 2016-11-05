package de.h4ck4t0n.trips.location;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * created by: saschabast
 * since: 05/11/2016
 */
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    private double longtitude;

    private double latitude;

    public Location() {
    }

    public Location(final double latitude, final double longitude) {
        this.latitude = latitude;
        this.longtitude = longitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
