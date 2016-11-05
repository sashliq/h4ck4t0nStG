package de.h4ck4t0n.join_request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.h4ck4t0n.trips.AbstractTrip;
import de.h4ck4t0n.trips.offer.TripOffer;

import javax.persistence.*;

/**
 * created by: saschabast
 * since: 05/11/2016
 */
@Entity
public class JoinRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @OneToOne
    private AbstractTrip trip;

    private boolean isAccepted;

    public JoinRequest(TripOffer trip){
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
}
