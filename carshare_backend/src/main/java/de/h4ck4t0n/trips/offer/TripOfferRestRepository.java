package de.h4ck4t0n.trips.offer;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.h4ck4t0n.trips.TripDistance;
import de.h4ck4t0n.trips.location.Location;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthNone;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiVersion;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVerb;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * created by: saschabast
 * since: 05/11/2016
 */
@Api(name = "trip offer services", description = "methods for managing trip offers", group = "trips", visibility = ApiVisibility.PUBLIC, stage = ApiStage.RC)
@ApiVersion(since = "1.0", until = "X")
@ApiAuthNone
@RestController
@RequestMapping("/trip-offers")
public class TripOfferRestRepository {

    @Autowired
    private TripService tripService;

    @Autowired
    private TripOfferRepository tripOfferRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<TripOffer> getRequests() {
        return tripOfferRepository.findAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<TripOffer> saveRequest(@RequestBody final TripOffer request) {
       return new ResponseEntity<>(tripOfferRepository.save(request), HttpStatus.CREATED);
    }
//
//    @RequestMapping(value = "/{longitude:.+}/{latitude:.+}", method = RequestMethod.GET)
//    @ApiMethod(
//            path = "/longitude/latitude",
//            verb = ApiVerb.POST,
//            description = "gives you the 10 nearest trip by your actual longitude and latitude",
//            consumes = {MediaType.APPLICATION_JSON_VALUE},
//            responsestatuscode = "200"
//    )
//    public List<TripDistance> getTripOffers(@PathVariable double longitude, @PathVariable double latitude) {
//        return tripService.getNearestTrips(tripOfferRepository.findAll(), longitude, latitude);
//    }


    @RequestMapping(value = "/{startLocation:.+}/{endLocation:.+}", method = RequestMethod.GET)
    @ApiMethod(
            path = "/startLocation/endLocation",
            verb = ApiVerb.POST,
            description = "gives you the trips depending on startLocation(Location) and endLocation(Location)",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            responsestatuscode = "200"
    )
    public List<TripDistance> getTripOffersLocationDestination(@PathVariable String startLocation, @PathVariable String endLocation) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            final Location startLocationObj = mapper.readValue(startLocation, Location.class);
            final Location endLocationObj = mapper.readValue(endLocation, Location.class);
            final List<TripDistance> result = tripService.getFilteredByStartLocationAndDestination(tripOfferRepository.findAll(), startLocationObj, endLocationObj);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = "/{startLocation:.+}", method = RequestMethod.GET)
    @ApiMethod(
            path = "/location/endLocation",
            verb = ApiVerb.POST,
            description = "gives you the trips depending on startLocation(Location)",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            responsestatuscode = "200"
    )
    public List<TripDistance> getTripOffersByStartLocation(@PathVariable String startLocation) {
        final List<TripDistance> result = getTripDistancesFilterdByLocation(startLocation);
        if (result != null) return result;
        return null;
    }

    @RequestMapping(value = "/{endLocation:.+}", method = RequestMethod.GET)
    @ApiMethod(
            path = "/location/endLocation",
            verb = ApiVerb.POST,
            description = "gives you the trips depending on startLocation(Location)",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            responsestatuscode = "200"
    )
    public List<TripDistance> getTripOffersByEndlocation(@PathVariable String endLocation) {
        final List<TripDistance> result = getTripDistancesFilterdByLocation(endLocation);
        if (result != null) return result;
        return null;
    }

    private List<TripDistance> getTripDistancesFilterdByLocation(final @PathVariable String endLocation) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            final Location startLocationObj = mapper.readValue(endLocation, Location.class);
            final List<TripDistance> result = tripService.getFilteredByLocation(tripOfferRepository.findAll(), startLocationObj);
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
