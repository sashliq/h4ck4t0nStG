package de.h4ck4t0n.join_request;

import de.h4ck4t0n.user.User;
import de.h4ck4t0n.user.UserRepository;
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
@Api(name = "join request services", description = "methods for managing join request", group = "join-request", visibility = ApiVisibility.PUBLIC, stage = ApiStage.RC)
@ApiVersion(since = "1.0", until = "X")
@ApiAuthNone
@RestController
@RequestMapping("/join-requests")
public class JoinRequestRestRepository {

    @Autowired
    private JoinRequestRepository joinRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/to/{userId:.+}", method = RequestMethod.GET)
    @ApiMethod(
            path = "/to/userId",
            verb = ApiVerb.POST,
            description = "gives you the JoinRquests requested to some user",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            responsestatuscode = "200"
    )
    public List<JoinRequest> getJoinRequestsToUser(@PathVariable Long userId) {
        final User user = userRepository.findOne(userId);
        return joinRequestRepository.findJoinRequestsSendToUser(user);
    }

    @RequestMapping(value = "/from/{userId:.+}", method = RequestMethod.GET)
    @ApiMethod(
            path = "to/userId",
            verb = ApiVerb.POST,
            description = "gives you the JoinRquests requested to some user",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            responsestatuscode = "200"
    )

    public List<JoinRequest> getJoinRequestsFromUser(@PathVariable Long userId) {
        final User user = userRepository.findOne(userId);
        return joinRequestRepository.findJoinRequestsSendFromUser(user);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void saveRequest(@RequestBody final JoinRequest request) {
        joinRequestRepository.save(request);
    }
}
