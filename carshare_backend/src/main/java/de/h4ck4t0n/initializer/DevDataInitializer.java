package de.h4ck4t0n.initializer;

import de.h4ck4t0n.car.Car;
import de.h4ck4t0n.car.CarRepository;
import de.h4ck4t0n.trips.location.Location;
import de.h4ck4t0n.trips.location.LocationRepository;
import de.h4ck4t0n.trips.location.LocationUtils;
import de.h4ck4t0n.trips.offer.TripOffer;
import de.h4ck4t0n.trips.offer.TripOfferRepository;
import de.h4ck4t0n.trips.request.TripRequest;
import de.h4ck4t0n.trips.request.TripRequestRepository;
import de.h4ck4t0n.user.User;
import de.h4ck4t0n.user.UserRepository;
import io.codearte.jfairy.Fairy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * created by: saschabast
 * since: 05/11/2016
 */
@Component
public class DevDataInitializer {

    private static final double CENTER_LONGITUDE = 9.1814593;
    private static final double CENTER_LATITUDE = 48.7826305;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TripOfferRepository tripOfferRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private TripRequestRepository tripRequestRepository;

    @Autowired
    private LocationRepository locationRepository;

    @PostConstruct
    private void init() {
        final List<Location> locations = getRandomLocations(10, 3000);
        buildTestTripOffers(locations);
        final List<Location> requestLocations = getRandomLocations(10, 3000);
        buildTestTripRequest(requestLocations);
    }

    private void buildTestTripRequest(final List<Location> requestLocations) {
        Fairy fairy = Fairy.create();
        for (final Location curRequestLocation : requestLocations) {

            final List<Location> randomStartLocation = getRandomLocations(1, 5000);
            final TripRequest curTripRequest = new TripRequest();
            curTripRequest.setEndLocation(curRequestLocation);
            curTripRequest.setStartLocation(randomStartLocation.get(0));
            final User owner = new User();
            owner.setFirstName(fairy.person().firstName());
            owner.setLastName(fairy.person().lastName());
            owner.setLocation(curRequestLocation);
            userRepository.save(owner);
            final Car car = new Car();
            carRepository.save(car);
            curTripRequest.setOwner(owner);
            tripRequestRepository.save(curTripRequest);
        }

    }

    private void buildTestTripOffers(final List<Location> locations) {
        Fairy fairy = Fairy.create();
        for (final Location curLocation : locations) {
            final List<Location> randomStartLocation = getRandomLocations(1, 5000);
            final TripOffer curTripOffer = new TripOffer();
            curTripOffer.setEndLocation(curLocation);
            curTripOffer.setStartLocation(randomStartLocation.get(0));
            final User owner = new User();
            owner.setFirstName(fairy.person().firstName());
            owner.setLastName(fairy.person().lastName());
            owner.setLocation(curLocation);
            userRepository.save(owner);
            final Car car = new Car();
            carRepository.save(car);
            curTripOffer.setCar(car);
            curTripOffer.setOwner(owner);
            tripOfferRepository.save(curTripOffer);
        }
    }

    public List<Location> getRandomLocations(final int numberOfLocations, final int radiusInMeters) {
        final List<Location> result = new ArrayList<>();
        int i = 0;
        while (i <= numberOfLocations) {
            final Location randomLocation = LocationUtils.getRandomLocation(CENTER_LATITUDE, CENTER_LONGITUDE, radiusInMeters);
            locationRepository.save(randomLocation);
            result.add(randomLocation);
            i++;
        }
        return result;
    }
}
