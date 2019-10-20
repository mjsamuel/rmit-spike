import axios from '../axios.js'
import { DATA_API_URL } from '../Constants'

class SearchDataService {
  makeSearch(searchQuery) {
      console.log("search API endpoint called");
      return axios.instance.get(`${DATA_API_URL}/search?query=${searchQuery}`)
  }
}

export default new SearchDataService()
