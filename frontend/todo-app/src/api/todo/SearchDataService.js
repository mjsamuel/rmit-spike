import axios from 'axios'
import { API_URL, JPA_API_URL, DATA_API_URL } from '../../Constants'

class SearchDataService {
  makeSearch(searchQuery) {
      console.log("search API endpoint called");
      // return axios.get('${DATA_API_URL}/search?=${searchQuery}')
      const response = {
        users: [
          {
            username: "johnDoe",
            userId: "001"
          },
          {
            username: "janeDoe",
            userId: "002"
          }
        ],
        channels: [
          {
            channelName: "sept",
            channelId: "001"
          },

        ]
      }
      return response;
  }
}

export default new SearchDataService()
