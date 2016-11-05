package de.h4ck4t0n.trips.request;

import de.h4ck4t0n.trips.location.Location;
import de.h4ck4t0n.user.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * created by: saschabast
 * since: 05/11/2016
 */
@Entity
@DiscriminatorValue("TRIP_REQUEST")
public class TripRequest {


    @ManyToOne
    private User owner;


    @OneToOne
    private Location fromLocation;
    @OneToOne
    private Location toLocation;

    public TripRequest() {
    }


}
