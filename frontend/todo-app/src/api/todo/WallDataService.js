import axios from 'axios'
import { API_URL, JPA_API_URL, DATA_API_URL } from '../../Constants'

class WallDataService {
  retrieveWallThreads(userId) {
      console.log('retrieveThreads API endpoint called')
      // return axios.get(`${DATA_API_URL}/api/user/${userId}/feed`)
      const response = `{
        "threads": [
          {
            "id": "001",
            "title": "Thread Title",
            "author": "u/author",
            "noComments": 0,
            "upspikes": 0,
            "channelOrigin": "c/channel-origin"
          },
          {
            "id": "002",
            "title": "Bike facilities at Bundoora",
            "author": "u/bob",
            "noComments": 6,
            "upspikes": 5,
            "channelOrigin": "c/general"
          },
          {
            "id": "003",
            "title": "Online Survey - Psychology Honours Thesis",
            "author": "u/alice",
            "noComments": 0,
            "upspikes": 2,
            "channelOrigin": "c/psych"
          },
          {
            "id": "004",
            "title": "IT vs Computer science",
            "author": "u/carol",
            "noComments": 16,
            "upspikes": 25,
            "channelOrigin": "c/cs & c/it"
          },
          {
            "id": "005",
            "title": "The Rise and Fall of SEPT at RMIT",
            "author": "u/john-smith",
            "noComments": 13,
            "upspikes": 30,
            "channelOrigin": "c/sept"
          }
        ]
      }`
      return JSON.parse(response)
    }
}

export default new WallDataService()
