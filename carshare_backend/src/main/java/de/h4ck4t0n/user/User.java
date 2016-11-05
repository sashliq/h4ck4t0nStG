package de.h4ck4t0n.user;

import de.h4ck4t0n.trips.location.Location;

import javax.persistence.*;

/**
 * created by: saschabast
 * since: 04/11/2016
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String facebookId;

    @OneToOne
    private Location location;

    public User() {
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setFacebookId(final String facebookId) {
        this.facebookId = facebookId;
    }

    public void setLocation(final Location location) {
        this.location = location;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public Location getLocation() {
        return location;
    }
}
