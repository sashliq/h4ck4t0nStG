package de.h4ck4t0n.trips;

/**
 * created by: saschabast
 * since: 05/11/2016
 */
public class TripDistance implements Comparable<TripDistance>{
    final private Double distance;
    final private AbstractTrip trip;

    public TripDistance(final Double distance, final AbstractTrip abstractTrip) {
        this.distance = distance;
        this.trip = abstractTrip;
    }

    @Override
    public int compareTo(final TripDistance o) {
        return this.distance.compareTo(o.getDistance());
    }

    public Double getDistance() {
        return distance;
    }

    public AbstractTrip getTrip() {
        return trip;
    }
}
