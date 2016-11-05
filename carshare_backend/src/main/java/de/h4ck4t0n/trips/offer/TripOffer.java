package de.h4ck4t0n.trips.offer;

import de.h4ck4t0n.car.Car;
import de.h4ck4t0n.trips.AbstractTrip;

import javax.persistence.*;

/**
 * created by: saschabast
 * since: 05/11/2016
 */
@Entity
@DiscriminatorValue("TRIP_OFFER")
public  class TripOffer extends AbstractTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Car car;

    public TripOffer() {
    }

    public void setCar(final Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    public Long getId() {
        return id;
    }
}
