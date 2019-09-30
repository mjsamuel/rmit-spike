import axios from '../axios.js'
import { DATA_API_URL } from '../Constants'

class ChannelDataService {

  getChannel(channel_id, user_id) {
    return axios.instance.get(`${DATA_API_URL}/channel/${channel_id}?user_id=${user_id}`);
  }

  retrieveChannelThreads(channel_id) {
      console.log('retrieveChannelThreads API endpoint called')
      return axios.instance.get(`${DATA_API_URL}/channel/${channel_id}/thread`)
      // const response = {
      //   channelName: "sept",
      //   threads: [
      //     {
      //       id: "001",
      //       title: "Thread Title",
      //       authorId: 1,
      //       noComments: 0,
      //       upspikes: 0
      //     },
      //     {
      //       id: "002",
      //       title: "The Rise and Fall of SEPT at RMIT",
      //       authorId: 1,
      //       noComments: 13,
      //       upspikes: 30
      //     }
      //   ],
      //   subscribed: false
      // }

      // return response
  }

  createChannel(request) {
    console.log('createChannel API endpoint called')
    return axios.instance.post(`${DATA_API_URL}/channel`, request)
  }

  subscribeToChannel(channelId, userId) {
    console.log('subscribeToChannel API endpoint called')
    return axios.instance.post(`${DATA_API_URL}/user/${userId}/subscribe?channel_id=${channelId}`)
  }
}

export default new ChannelDataService()
