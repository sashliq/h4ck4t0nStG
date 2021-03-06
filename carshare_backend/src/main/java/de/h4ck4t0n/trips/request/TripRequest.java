package de.h4ck4t0n.trips.request;

import de.h4ck4t0n.trips.AbstractTrip;
import de.h4ck4t0n.trips.location.Location;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * created by: saschabast
 * since: 05/11/2016
 */
@Entity
@DiscriminatorValue("TRIP_REQUEST")
public class TripRequest extends AbstractTrip{

    @OneToOne
    private Location startLocation;

    //radius in meter
    private int radius;

    public TripRequest() {
    }

    public Location getStartLocation() {
        return startLocation;
    }
}
