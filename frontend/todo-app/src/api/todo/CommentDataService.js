import axios from 'axios'
import { API_URL, JPA_API_URL, DATA_API_URL } from '../../Constants'

class CommentDataService {

    updateComment(thread_id, request) {
        const response = axios.put(`${DATA_API_URL}/comment/${thread_id}`, request)
        .then(response => {
            console.log(response);
          })
          .catch(error => {
            console.log(error);
          });
    }

}

export default new CommentDataService()