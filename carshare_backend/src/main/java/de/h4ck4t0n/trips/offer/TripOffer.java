package de.h4ck4t0n.trips.offer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.h4ck4t0n.trips.AbstractTrip;

import javax.persistence.*;

/**
 * created by: saschabast
 * since: 05/11/2016
 */
@Entity
@DiscriminatorValue("TRIP_OFFER")
public class TripOffer extends AbstractTrip {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
}
