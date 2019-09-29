// import axios from 'axios'
import axios from '../../axios.js'
import { DATA_API_URL } from '../../Constants'

class CommentDataService {

    updateComment(thread_id, request) {
        return axios.instance.put(`${DATA_API_URL}/comment/${thread_id}`, request)
    }

    getComments(thread_id) {
      console.log("Getting Comments...")
      console.log("Authorization header:", axios.instance)
      return axios.instance.get(`${DATA_API_URL}/thread/${thread_id}/comment`)

    }

}

export default new CommentDataService()