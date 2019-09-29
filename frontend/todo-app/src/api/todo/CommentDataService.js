// import axios from 'axios'
import axios from '../../axios.js'
import { API_URL, JPA_API_URL, DATA_API_URL } from '../../Constants'

class CommentDataService {

    updateComment(thread_id, request) {
        const response = axios.instance.put(`${DATA_API_URL}/comment/${thread_id}`, request)
        .then(response => {
            console.log(response);
          })
          .catch(error => {
            console.log(error);
          });
    }

    getComments(thread_id) {
      console.log("Getting Comments...")
      console.log("Authorization header:", axios.instance)
      // let token = sessionStorage.getItem('bearerToken');
      // return axios.get(`${DATA_API_URL}/thread/${thread_id}/comment`, {headers: {"Authorization" : `${token}`}})
      return axios.instance.get(`${DATA_API_URL}/thread/${thread_id}/comment`)

    }

}

export default new CommentDataService()