package de.h4ck4t0n.join_request;

import de.h4ck4t0n.trips.AbstractTrip;
import de.h4ck4t0n.trips.offer.TripOffer;
import de.h4ck4t0n.user.User;

import javax.persistence.*;
import java.util.Date;

/**
 * created by: saschabast
 * since: 05/11/2016
 */

@Entity
public class JoinRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdOn;

    @OneToOne
    private AbstractTrip trip;

    @OneToOne
    private User owner;

    private boolean isAccepted;

    public JoinRequest() {
    }

    public JoinRequest(TripOffer trip){
        this.createdOn = new Date();
        this.trip = trip;
        isAccepted = false;
    }

    public JoinRequest(TripOffer trip, boolean accept){
        this.trip = trip;
        isAccepted = false;
    }

    public AbstractTrip getTrip() {
        return trip;
    }

    public void setTrip(final AbstractTrip trip) {
        this.trip = trip;
    }

    public Long getId() {
        return id;
    }
}
