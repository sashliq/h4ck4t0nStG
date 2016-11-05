package de.h4ck4t0n.trips.offer;

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

    public List<TripDistance> getNearestTrips(final List<TripOffer> allTrips, final Double longitude, final Double latitude) {
        final List<TripDistance> result = new ArrayList<>();
        final Location curLocation = new Location(latitude, longitude);
        for (final TripOffer curTrip : allTrips) {
            result.add(new TripDistance(curTrip.getCar().getLocation().getDistanceTo(curLocation), curTrip));
        }
        Collections.sort(result);
        if (result.size() < 10) {
            return result.subList(0, result.size() - 1);
        }
        return result.subList(0, 9);
    }

}
