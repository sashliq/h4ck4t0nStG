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

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User owner;

    @OneToOne(cascade = CascadeType.PERSIST)
    private  Location endLocation;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Location startLocation;

    @JsonIgnore
    private final Date createdOn;

    public AbstractTrip() {
        this.createdOn = new Date();
    }

    public void setOwner(final User owner) {
        this.owner = owner;
    }

    public void setEndLocation(final Location endLocation) {
        this.endLocation = endLocation;
    }

    public User getOwner() {
        return owner;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(final Location startLocation) {
        this.startLocation = startLocation;
    }
}
