import { API_PATH } from '../constants/env.js';

export function buildPath(subpath) {
  return API_PATH + subpath;
}
