package de.h4ck4t0n.entries.request;

import de.h4ck4t0n.entries.location.Location;

import javax.persistence.*;

/**
 * created by: saschabast
 * since: 05/11/2016
 */
@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @OneToOne
    private Location fromLocation;
    @OneToOne
    private Location toLocation;

    public Request() {
    }


}
