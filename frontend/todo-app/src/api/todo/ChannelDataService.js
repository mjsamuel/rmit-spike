import axios from 'axios'
import { API_URL, JPA_API_URL, DATA_API_URL } from '../../Constants'
import {USER_NAME_SESSION_ATTRIBUTE_NAME} from '../../components/todo/AuthenticationService.js'


class ChannelDataService {
  retrieveChannelThreads(channel_id) {
      console.log('retrieveChannelThreads API endpoint called')
      // return axios.get(`${DATA_API_URL}/thread?chan=${channel_id}`)
      const response = `{
        "channelName": "c/sept",
        "threads": [
          {
            "id": "001",
            "title": "Thread Title",
            "author": "u/author",
            "noComments": 0,
            "upspikes": 0
          },
          {
            "id": "002",
            "title": "The Rise and Fall of SEPT at RMIT",
            "author": "u/john-smith",
            "noComments": 13,
            "upspikes": 30
          }
        ]
      }`

      return JSON.parse(response)
  }

  createChannel(username, channelName, visibility) {
    console.log('createChannel API endpoint called')
    // axios.post(`${API_URL}/channel`, {
    //     username: username,
    //     channelName: channelName,
    //     visibility: visibility
    // })
    // .then(() => {
    //   return `/channel/${response.data.channelId}`
    // })
    const response = `{
      "channelId": "${channelName}"
    }`
    return JSON.parse(response)
  }

  subscribeToChannel(channelId) {
    console.log('subscribeToChannel API endpoint called')
    return axios.put(`${API_URL}/channel`, {
        channelId: channelId,
        username: sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME),
    })
  }
}



export default new ChannelDataService()
