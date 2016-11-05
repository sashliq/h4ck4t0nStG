package de.h4ck4t0n.trips.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.h4ck4t0n.trips.TripDistance;
import de.h4ck4t0n.trips.location.Location;
import de.h4ck4t0n.trips.offer.TripService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthNone;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiVersion;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVerb;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * created by: saschabast
 * since: 05/11/2016
 */

@Api(name = "trip request services", description = "methods for managing trip requests", group = "trips", visibility = ApiVisibility.PUBLIC, stage = ApiStage.RC)
@ApiVersion(since = "1.0", until = "X")
@ApiAuthNone
@RestController
@RequestMapping("/trip-requests")
public class TripRequestRestRepository {

    @Autowired
    private TripRequestRepository tripRequestRepository;

    @Autowired
    private TripService tripService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<TripRequest> getRequests() {
        return tripRequestRepository.findAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void saveRequest(@RequestBody final TripRequest request) {
        tripRequestRepository.save(request);
    }


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
            final List<TripDistance> result = tripService.getFilteredByStartLocationAndDestination(tripRequestRepository.findAll(), startLocationObj, endLocationObj);
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
            final List<TripDistance> result = tripService.getFilteredByLocation(tripRequestRepository.findAll(), startLocationObj);
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
