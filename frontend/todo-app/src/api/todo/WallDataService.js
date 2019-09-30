import axios from '../../axios.js'
import { API_URL, JPA_API_URL, DATA_API_URL } from '../../Constants'
import {USER_NAME_SESSION_ATTRIBUTE_NAME} from '../../components/todo/AuthenticationService.js'

class WallDataService {
  retrieveWallThreads(userId) {
      console.log("Getting Wall Threads...")
      console.log("Authorization header:", axios.instance)
      // let userId = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
      return axios.instance.get(`${DATA_API_URL}/user/${userId}/wall`);
      // const response = `{
      //   "threads": [
      //     {
      //       "id": "001",
      //       "title": "Thread Title",
      //       "author": "author",
      //       "noComments": 0,
      //       "upspikes": 0,
      //       "channelOrigin": "channel-origin"
      //     },
      //     {
      //       "id": "002",
      //       "title": "Bike facilities at Bundoora",
      //       "author": "bob",
      //       "noComments": 6,
      //       "upspikes": 5,
      //       "channelOrigin": "general"
      //     },
      //     {
      //       "id": "003",
      //       "title": "Online Survey - Psychology Honours Thesis",
      //       "author": "alice",
      //       "noComments": 0,
      //       "upspikes": 2,
      //       "channelOrigin": "psych"
      //     },
      //     {
      //       "id": "004",
      //       "title": "IT vs Computer science",
      //       "author": "carol",
      //       "noComments": 16,
      //       "upspikes": 25,
      //       "channelOrigin": "c/cs & c/it"
      //     },
      //     {
      //       "id": "005",
      //       "title": "The Rise and Fall of SEPT at RMIT",
      //       "author": "john-smith",
      //       "noComments": 13,
      //       "upspikes": 30,
      //       "channelOrigin": "ssept"
      //     }
      //   ]
      // }`
      // return JSON.parse(response)
    }
}

export default new WallDataService()
