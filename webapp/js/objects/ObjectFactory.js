export function createNewTripOffer(start, destination, owner) {
    return {
        "createdOn": {},
        "endLocation": {
            "latitude": destination.latitude,
            "longitude": destination.longitude,
            "rangeInMeters": 0
        },
        "startLocation": {
            "latitude": start.latitude,
            "longitude": start.longitude,
            "rangeInMeters": 0
        },
        "owner": owner
    }

}