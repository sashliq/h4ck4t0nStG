package de.h4ck4t0n.trips.offer;

import de.h4ck4t0n.trips.AbstractTrip;
import de.h4ck4t0n.trips.TripDistance;
import de.h4ck4t0n.trips.location.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * created by: saschabast
 * since: 05/11/2016
 */
@Component
public class TripService {

    public List<TripDistance> getNearestTrips(final List<? extends AbstractTrip> allTrips, final Double longitude, final Double latitude) {
        final Location curLocation = new Location(latitude, longitude);
        final List<TripDistance> result = getTripDistances(allTrips, curLocation);
        Collections.sort(result);
        if (result.size() < 10) {
            return result.subList(0, result.size() - 1);
        }
        return result.subList(0, 9);
    }

    private List<TripDistance> getTripDistances(final List<? extends AbstractTrip> allTrips, final Location curLocation) {
        final List<TripDistance> result = new ArrayList<>();
        for (final AbstractTrip curTrip : allTrips) {
            result.add(new TripDistance(curTrip.getStartLocation().getDistanceTo(curLocation), curTrip));
        }
        return result;
    }

    public List<TripDistance> getFilteredByStartLocationAndDestination(final Location startLocation, final Location destination) {
        return new ArrayList<>();
    }

    public List<TripDistance> getFilteredByLocation(final List<? extends AbstractTrip> allTrips, final Location startLocation) {
        final List<TripDistance> tripDistances = getTripDistances(allTrips, startLocation);
        final List<TripDistance> result = new ArrayList<>();
        int i = 0;
        while (tripDistances.get(i).getDistance() <= (double) startLocation.getRangeInMeters()) {
            result.add(tripDistances.get(i));
        }
        return result;
    }
}
