package de.h4ck4t0n.trips.request;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthNone;
import org.jsondoc.core.annotation.ApiVersion;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<TripRequest> getRequests() {
        return tripRequestRepository.findAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void saveRequest(@RequestBody final TripRequest request) {
         tripRequestRepository.save(request);
    }
}
