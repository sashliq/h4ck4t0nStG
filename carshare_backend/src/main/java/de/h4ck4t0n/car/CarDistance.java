package de.h4ck4t0n.car;

/**
 * created by: saschabast
 * since: 05/11/2016
 */
public class CarDistance implements Comparable<CarDistance> {

    private final Double distance;
    private final Car car;

    public CarDistance(final Double distance, final Car car) {
        this.distance = distance;
        this.car = car;
    }

    public double getDistance() {
        return distance;
    }

    public Car getCar() {
        return car;
    }

    @Override
    public int compareTo(final CarDistance o) {
        return this.distance.compareTo(o.distance);
    }
}
