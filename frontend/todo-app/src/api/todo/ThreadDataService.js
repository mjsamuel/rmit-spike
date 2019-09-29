import axios from '../../axios.js'
import { API_URL, JPA_API_URL, DATA_API_URL } from '../../Constants'

class ThreadDataService {

    addThread() {
        console.log('addThread API endpoint called')
        return axios.instance.post(`${DATA_API_URL}/thread`)
    }

    retrieveThreads() {
        console.log('retrieveThreads API endpoint called')
        return axios.instance.get(`${DATA_API_URL}/thread`)
    }

    retrieveThread(thread_id) {
        console.log('retrieveThread API endpoint called')
        // return axios.instance.get(`${DATA_API_URL}/thread/${thread_id}`)
        const response = `{
              "id": 1,
              "author": "John Smith",
              "title": "The Rise and Fall of SEPT at RMIT",
              "primary_channel": "sept",
              "timeDelta": "1 week",
              "tagged_channels": [
                2,
                3,
                4
              ],
              "content": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Id semper risus in hendrerit. Facilisis magna etiam tempor orci eu lobortis elementum nibh. Integer quis auctor elit sed vulputate. Arcu vitae elementum curabitur vitae nunc sed velit dignissim sodales. Pulvinar sapien et ligula ullamcorper malesuada. Mi in nulla posuere sollicitudin aliquam. Auctor augue mauris augue neque gravida in fermentum et sollicitudin. Tincidunt nunc pulvinar sapien et. Libero id faucibus nisl tincidunt eget nullam non. Dictum sit amet justo donec enim diam vulputate. Sodales ut etiam sit amet nisl purus in mollis nunc. Vulputate mi sit amet mauris commodo. Diam vulputate ut pharetra sit amet aliquam id. Etiam non quam lacus suspendisse faucibus interdum posuere. Dolor morbi non arcu risus. Vulputate enim nulla aliquet porttitor lacus. Malesuada fames ac turpis egestas sed tempus. Tincidunt lobortis feugiat vivamus at. Enim neque volutpat ac tincidunt vitae semper quis. Vestibulum lorem sed risus ultricies tristique. Quis imperdiet massa tincidunt nunc pulvinar sapien et ligula. Senectus et netus et malesuada fames ac turpis egestas. Faucibus scelerisque eleifend donec pretium vulputate sapien nec sagittis aliquam. Mauris nunc congue nisi vitae suscipit tellus mauris a. Duis ut diam quam nulla porttitor. Viverra aliquet eget sit amet tellus cras. Tincidunt augue interdum velit euismod in pellentesque massa. Quam vulputate dignissim suspendisse in est.",
              "comments": [
                {
                    "id": 1,
                    "author": "David Tenant",
                    "spikes": 42,
                    "timeDelta": "16 minutes",
                    "content": "This is some great content that you have here!"
                },
                {
                    "id": 2,
                    "author": "Gannicus Quintus",
                    "spikes": 15,
                    "timeDelta": "22 minutes",
                    "content": "Hi John, I'm not sure I agree with your sentiment. SEPT is far too hard."
                },
                {
                    "id": 3,
                    "author": "Marcus Aurelius",
                    "spikes": 2,
                    "timeDelta": "3 hours",
                    "content": "RMIT seems like a great university, hopefully they continue to be."
                },
                {
                    "id": 4,
                    "author": "The Syrian",
                    "spikes": 6,
                    "timeDelta": "2 days",
                    "content": "I tire of these games Batthiatus, I would see them end."
                }
              ]
            }`
        return JSON.parse(response)
    }

    updateThread(thread_id, request) {
        const response = axios.instance.put(`${DATA_API_URL}/thread/${thread_id}`, request)
        .then(response => {
            console.log(response);
          })
          .catch(error => {
            console.log(error);
          });
          return response
    }

    addComment(thread_id, request) {
        console.log("addComment API endpoint called")
        console.log(request)
        return axios.instance.post(`${DATA_API_URL}/thread/${thread_id}/comment`, request)
    }

    addReport(thread_id, request) {
        console.log("addReport API endpoint called")
        console.log(request)
        return axios.instance.post(`${DATA_API_URL}/thread/${thread_id}/report`, request)
    }

}

export default new ThreadDataService()
