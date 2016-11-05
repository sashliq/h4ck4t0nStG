package de.h4ck4t0n.join_request;

import de.h4ck4t0n.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * created by: saschabast
 * since: 05/11/2016
 */
public interface JoinRequestRepository extends JpaRepository<JoinRequest, Long> {

    @Query("Select jr from JoinRequest jr where jr.trip.owner = :user")
    List<JoinRequest> findJoinRequestsSendToUser(@Param("user") final User user);

    @Query("Select jr from JoinRequest jr where jr.owner = :user")
    List<JoinRequest> findJoinRequestsSendFromUser(@Param("user") final User user);

}
