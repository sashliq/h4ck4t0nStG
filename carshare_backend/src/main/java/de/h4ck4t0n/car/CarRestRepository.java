package de.h4ck4t0n.car;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthNone;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiVersion;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVerb;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * created by: saschabast
 * since: 05/11/2016
 */
@Api(name = "car services", description = "methods for managing cars", group = "cars", visibility = ApiVisibility.PUBLIC, stage = ApiStage.RC)
@ApiVersion(since = "1.0", until = "X")
@ApiAuthNone
@RestController
@RequestMapping("/cars")
public class CarRestRepository {

    @Autowired
    private CarService carService;


    @RequestMapping(value = "/{longitude:.+}/{latitude:.+}", method = RequestMethod.GET)
    @ApiMethod(
            path = "/longitude/latitude",
            verb = ApiVerb.POST,
            description = "gives you the 10 nearest cars by your actual longitude and latitude",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            responsestatuscode = "200"
    )
    public List<CarDistance> getByLangtitudeAndLongitude(@PathVariable double longitude, @PathVariable double latitude) {
        return carService.getVehicles(longitude, latitude);
    }


}
