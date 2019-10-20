import axios from '../axios.js'
import { DATA_API_URL } from '../Constants'

class CommentDataService {

    updateComment(thread_id, request) {
        return axios.instance.put(`${DATA_API_URL}/comment/${thread_id}`, request)
    }

    getComments(thread_id) {
      return axios.instance.get(`${DATA_API_URL}/thread/${thread_id}/comment`)

    }

}

export default new CommentDataService()