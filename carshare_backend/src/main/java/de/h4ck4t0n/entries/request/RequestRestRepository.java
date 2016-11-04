package de.h4ck4t0n.entries.request;

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

@Api(name = "request services", description = "Methods for managing requests", group = "entries", visibility = ApiVisibility.PUBLIC, stage = ApiStage.RC)
@ApiVersion(since = "1.0", until = "X")
@ApiAuthNone
@RestController
@RequestMapping("/requests")
public class RequestRestRepository {

    @Autowired
    private RequestRepository requestRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Request> getRequests() {
        return requestRepository.findAll();
    }

    @RequestMapping(value = "/save/", method = RequestMethod.POST)
    public void saveRequest(@RequestBody final Request request) {
         requestRepository.save(request);
    }
}
