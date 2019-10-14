import axios from '../axios.js'
import { DATA_API_URL } from '../Constants'

class ChatDataService {
    retrieveMessages(channelId) {
      // console.log('retrieveMessages API endpoint called')
      return axios.instance.get(`${DATA_API_URL}/channel/${channelId}/chat`)
    }
}

export default new ChatDataService()
