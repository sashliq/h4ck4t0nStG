package de.h4ck4t0n.car;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.h4ck4t0n.trips.location.Location;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * created by: saschabast
 * since: 05/11/2016
 */
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Size(max=4096)
    private String name;

    @Size(max=4096)
    private String description;

    @OneToOne
    private Location location;

    public Car() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Location getLocation() {
        return location;
    }
}
