package de.h4ck4t0n.trips.offer;

import de.h4ck4t0n.trips.TripDistance;
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
    public void saveRequest(@RequestBody final TripOffer request) {
        tripOfferRepository.save(request);
    }

    @RequestMapping(value = "/{longitude:.+}/{latitude:.+}", method = RequestMethod.GET)
    @ApiMethod(
            path = "/longitude/latitude",
            verb = ApiVerb.POST,
            description = "gives you the 10 nearest trip by your actual longitude and latitude",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            responsestatuscode = "200"
    )
    public List<TripDistance> getTripOffers(@PathVariable double longitude, @PathVariable double latitude) {
        return tripService.getNearestTrips(tripOfferRepository.findAll(), longitude, latitude);
    }

}
