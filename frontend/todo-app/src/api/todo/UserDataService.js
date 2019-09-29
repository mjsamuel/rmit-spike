import axios from '../../axios.js'
import { DATA_API_URL } from '../../Constants'

class UserDataService {

    getUser(user_id) {
        return axios.instance.get(`${DATA_API_URL}/user/${user_id}`)
    }

}

export default new UserDataService()