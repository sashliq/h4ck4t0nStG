package de.h4ck4t0n.trips;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.h4ck4t0n.trips.location.Location;
import de.h4ck4t0n.user.User;

import javax.persistence.*;
import java.util.Date;

/**
 * created by: saschabast
 * since: 05/11/2016
 */
@Entity
public class AbstractTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @ManyToOne
    private User owner;

    @OneToOne
    private  Location destination;

    @JsonIgnore
    private final Date createdOn;

    public AbstractTrip() {
        this.createdOn = new Date();
    }

    public void setOwner(final User owner) {
        this.owner = owner;
    }

    public void setDestination(final Location destination) {
        this.destination = destination;
    }

    public User getOwner() {
        return owner;
    }

    public Location getDestination() {
        return destination;
    }

    public Date getCreatedOn() {
        return createdOn;
    }
}
