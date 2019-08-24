import axios from 'axios'
import { API_URL, JPA_API_URL, DATA_API_URL } from '../../Constants'

class ThreadDataService {

    addThread() {
        console.log('addThread API endpoint called')
        return axios.post(`${DATA_API_URL}/thread`)
    }

    retrieveThreads() {
        console.log('retrieveThreads API endpoint called')
        return axios.get(`${DATA_API_URL}/thread`)
    }

    retrieveChannelThreads(channel_id) {
        console.log('retrieveChannelThreads API endpoint called')
        return axios.get(`${DATA_API_URL}/thread?chan=${channel_id}`) 
    }

    retrieveThread(thread_id) {
        console.log('retrieveThread API endpoint called')
        // return axios.get(`${DATA_API_URL}/thread/${thread_id}`)
        const response = `{
              "tagged_channels": [
                2,
                3,
                4
              ],
              "content": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Id semper risus in hendrerit. Facilisis magna etiam tempor orci eu lobortis elementum nibh. Integer quis auctor elit sed vulputate. Arcu vitae elementum curabitur vitae nunc sed velit dignissim sodales. Pulvinar sapien et ligula ullamcorper malesuada. Mi in nulla posuere sollicitudin aliquam. Auctor augue mauris augue neque gravida in fermentum et sollicitudin. Tincidunt nunc pulvinar sapien et. Libero id faucibus nisl tincidunt eget nullam non. Dictum sit amet justo donec enim diam vulputate. Sodales ut etiam sit amet nisl purus in mollis nunc. Vulputate mi sit amet mauris commodo. Diam vulputate ut pharetra sit amet aliquam id. Etiam non quam lacus suspendisse faucibus interdum posuere. Dolor morbi non arcu risus. Vulputate enim nulla aliquet porttitor lacus. Malesuada fames ac turpis egestas sed tempus. Tincidunt lobortis feugiat vivamus at. Enim neque volutpat ac tincidunt vitae semper quis. Vestibulum lorem sed risus ultricies tristique. Quis imperdiet massa tincidunt nunc pulvinar sapien et ligula. Senectus et netus et malesuada fames ac turpis egestas. Faucibus scelerisque eleifend donec pretium vulputate sapien nec sagittis aliquam. Mauris nunc congue nisi vitae suscipit tellus mauris a. Duis ut diam quam nulla porttitor. Viverra aliquet eget sit amet tellus cras. Tincidunt augue interdum velit euismod in pellentesque massa. Quam vulputate dignissim suspendisse in est.",
              "comments": [
                1,
                2,
                3
              ]
            }`
        return JSON.parse(response)
    }

}

export default new ThreadDataService()