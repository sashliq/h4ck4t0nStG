import { API_PATH, GOOGLE_GEOCODING_TOKEN} from '../constants/env.js';

export function buildPath(subpath) {
  return API_PATH + subpath;
}

export function buildGeocodingPath(query) {
  return `https://maps.googleapis.com/maps/api/geocode/json?address=${encodeURI(query)}&key=${GOOGLE_GEOCODING_TOKEN}`;
}
