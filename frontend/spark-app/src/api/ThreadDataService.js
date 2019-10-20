import axios from '../axios.js'
import { DATA_API_URL } from '../Constants'

class ThreadDataService {

    newThread(request) {
        return axios.instance.post(`${DATA_API_URL}/thread`, request)
    }

    retrieveThreads() {
        return axios.instance.get(`${DATA_API_URL}/thread`)
    }

    retrieveThread(thread_id) {
        return axios.instance.get(`${DATA_API_URL}/thread/${thread_id}`)
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
        return axios.instance.post(`${DATA_API_URL}/thread/${thread_id}/comment`, request)
    }

    addReport(thread_id, request) {
        return axios.instance.post(`${DATA_API_URL}/thread/${thread_id}/report`, request)
    }

}

export default new ThreadDataService()
