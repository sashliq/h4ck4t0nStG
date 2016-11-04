package de.h4ck4t0n.rest;

import de.h4ck4t0n.entities.User;
import de.h4ck4t0n.repositories.UserRepository;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthNone;
import org.jsondoc.core.annotation.ApiVersion;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * created by: saschabast
 * since: 04/11/2016
 */
@Api(name = "user services", description = "Methods for managing user", group = "user", visibility = ApiVisibility.PUBLIC, stage = ApiStage.RC)
@ApiVersion(since = "1.0", until = "X")
@ApiAuthNone
@RestController
@RequestMapping("/users")
public class UserRestConroller {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> save() {
        return userRepository.findAll();
    }
}
