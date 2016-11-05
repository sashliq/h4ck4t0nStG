package de.h4ck4t0n.trips.location;

import java.util.Random;

/**
 * created by: saschabast
 * since: 05/11/2016
 */
public class LocationUtils {

    public static Location getRandomLocation(double x0, double y0, int radiusInMeters) {
        Random random = new Random();

        // Convert radius from meters to degrees
        double radiusInDegrees = radiusInMeters / 111000f;

        double u = random.nextDouble();
        double v = random.nextDouble();
        double w = radiusInDegrees * Math.sqrt(u);
        double t = 2 * Math.PI * v;
        double x = w * Math.cos(t);
        double y = w * Math.sin(t);

        // Adjust the x-coordinate for the shrinking of the east-west distances
        double new_x = x / Math.cos(y0);

        double latitude = new_x + x0;
        double longitude = y + y0;
        return new Location(latitude, longitude);
    }
}
