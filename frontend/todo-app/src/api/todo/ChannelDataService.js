import axios from '../../axios.js'
import { API_URL, JPA_API_URL, DATA_API_URL } from '../../Constants'
import {USER_NAME_SESSION_ATTRIBUTE_NAME} from '../../components/todo/AuthenticationService.js'


class ChannelDataService {

  getChannel(channel_id, user_id) {
    return axios.instance.get(`${DATA_API_URL}/channel/${channel_id}?user_id=${user_id}`);
  }

  retrieveChannelThreads(channel_id) {
      console.log('retrieveChannelThreads API endpoint called')
      let userId = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
      // return axios.instance.get(`${DATA_API_URL}/thread?chan=${channel_id}`)
      const response = {
        channelName: "sept",
        threads: [
          {
            id: "001",
            title: "Thread Title",
            authorId: 1,
            noComments: 0,
            upspikes: 0
          },
          {
            id: "002",
            title: "The Rise and Fall of SEPT at RMIT",
            authorId: 1,
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
    // axios.instance.post(`${API_URL}/channel`, {
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
    return axios.instance.put(`${API_URL}/channel/${channelId}`, {
        username: sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
    })
  }

  // Flag for deletion: replaced by ThreadDataService.newThread
  postThreadToChannel(channelId, title, body) {
    console.log('postThreadToChannel API endpoint called')
    return axios.instance.put(`${API_URL}/channel/${channelId}`, {
        username: sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME),
        title: title,
        body: body
    })
  }
}



export default new ChannelDataService()
