package de.h4ck4t0n.trips.location;

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
    private Long id;

    private double longitude;

    private double latitude;

    private int rangeInMeters;

    public Location() {
    }

    public Location(final double latitude, final double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getDistanceTo(Location l) {
        double lo = l.getLongitude();
        double la = l.getLatitude();
        double dx = 111.3 * (latitude - la);
        double dy = 71.5 * (longitude - lo);
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLongitude(final double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(final double latitude) {
        this.latitude = latitude;
    }

    public int getRangeInMeters() {
        return rangeInMeters;
    }

    public void setRangeInMeters(final int rangeInMeters) {
        this.rangeInMeters = rangeInMeters;
    }

    public Long getId() {
        return id;
    }
}
