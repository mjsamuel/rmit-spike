import axios from 'axios'
import { API_URL, JPA_API_URL, DATA_API_URL } from '../../Constants'
import {USER_NAME_SESSION_ATTRIBUTE_NAME} from '../../components/todo/AuthenticationService.js'


class ChannelDataService {
  retrieveChannelThreads(channel_id) {
      console.log('retrieveChannelThreads API endpoint called')
      let userId = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
      // return axios.get(`${DATA_API_URL}/thread?chan=${channel_id}`)
      const response = {
        channelName: "sept",
        threads: [
          {
            id: "001",
            title: "Thread Title",
            author: "author",
            noComments: 0,
            upspikes: 0
          },
          {
            id: "002",
            title: "The Rise and Fall of SEPT at RMIT",
            author: "john-smith",
            noComments: 13,
            upspikes: 30
          }
        ],
        subscribed: false
      }

      return response
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
    return axios.put(`${API_URL}/channel/${channelId}`, {
        username: sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
    })
  }

  postThreadToChannel(channelId, title, body) {
    console.log('postThreadToChannel API endpoint called')
    return axios.put(`${API_URL}/channel/${channelId}`, {
        username: sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME),
        title: title,
        body: body
    })
  }
}



export default new ChannelDataService()
