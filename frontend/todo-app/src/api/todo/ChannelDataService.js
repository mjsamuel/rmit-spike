import axios from 'axios'
import { API_URL, JPA_API_URL, DATA_API_URL } from '../../Constants'

class ChannelDataService {
  createChannel(username, channelName, visibility) {
    // axios.post(`${API_URL}/channel`, {
    //     username: username,
    //     channelName: channelName,
    //     visibility: visibility
    // })
    // .then(() => {
    //   return `/channel/${response.data.channelId}`
    // })

    const response = `{
      "channelId": "sept"
    }`
    return JSON.parse(response)
  }
}

export default new ChannelDataService()
