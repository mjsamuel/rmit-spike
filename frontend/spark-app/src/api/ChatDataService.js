import axios from '../axios.js'
import { DATA_API_URL } from '../Constants'

class ChatDataService {
    retrieveMessages(channelId) {
      console.log('retrieveMessages API endpoint called')
      // return axios.get(`${DATA_API_URL}/chat/${channelId}`)
      const response = {
        messages: [
          {
            username: "username",
            content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor',
            timeNumber: 10,
            timeUnit: "minutes"
          },
          {
            username: "username",
            content: 'Id semper risus in hendrerit. Facilisis magna etiam tempor orci eu lobortis elementum nibh',
            timeNumber: 9,
            timeUnit: "minutes"
          },
          {
            username: "username",
            content: 'Integer quis auctor elit sed vulputate. Arcu vitae elementum curabitur vitae nunc sed velit dignissim sodales',
            timeNumber: 4,
            timeUnit: "minutes"
          },
          {
            username: "username",
            content: 'Pulvinar sapien et ligula ullamcorper malesuada',
            timeNumber: 2,
            timeUnit: "minutes"
          },
          {
            username: "username",
            content: 'Mi in nulla posuere sollicitudin aliquam',
            timeNumber: 12,
            timeUnit: "seconds"
          }
        ]
      }
      return response
    }
}

export default new ChatDataService()
