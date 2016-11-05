package de.h4ck4t0n.car;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import de.h4ck4t0n.trips.location.Location;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * created by: saschabast
 * since: 05/11/2016
 */
@Component
public class CarService {

    private static final String API_KEY = "";
    private static final String VEHICLE_REST_URL = "http://www.car2go.com/api/v2.0/vehicles?loc=stuttgart&format=json";

    public List<CarDistance> getVehicles(final double longitude, final double latitude) {
        final List<CarDistance> result = new ArrayList<>();
        final List<Car> carList = new ArrayList<>();
        final GetRequest getRequest = Unirest.get(VEHICLE_REST_URL);
        try {
            final HttpResponse<JsonNode> jsonNodeHttpResponse = getRequest.asJson();
            carList.addAll(parseResponse(jsonNodeHttpResponse));
        } catch (UnirestException e) {
            e.printStackTrace();
        }


        for (final Car curcar : carList) {
            final double dis = curcar.getLocation().getDistanceTo(new Location(latitude, longitude));
            result.add(new CarDistance(dis, curcar));
        }
        Collections.sort(result);

        return result.subList(0, 9);
    }

    private Collection<? extends Car> parseResponse(final HttpResponse<JsonNode> jsonNodeHttpResponse) {
        final List<Car> result = new ArrayList<>();
        final JSONArray placemarks = ((JSONArray) jsonNodeHttpResponse.getBody().getObject().get("placemarks"));
        final int length = placemarks.length();
        int i = 0;
        while (i < length) {
            final JSONObject curJsonObj = (JSONObject) placemarks.get(i);
            final Car curCar = new Car();
            curCar.setName(curJsonObj.getString("name"));
            curCar.setDescription(curJsonObj.getString("vin"));
            final String coordinates1 = (String) curJsonObj.get("coordinates");
            final JSONArray coordinates = new JSONArray(coordinates1);
            final Location curLocation = new Location((Double) coordinates.get(1), (Double) coordinates.get(0));
            curCar.setLocation(curLocation);
            result.add(curCar);
            i++;
        }
        return result;
    }


}
