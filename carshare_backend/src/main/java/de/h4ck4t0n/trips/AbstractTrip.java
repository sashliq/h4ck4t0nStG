package de.h4ck4t0n.trips;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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


    @JsonIgnore
    private final Date createdOn;

    public AbstractTrip() {
        this.createdOn = new Date();
    }
}
