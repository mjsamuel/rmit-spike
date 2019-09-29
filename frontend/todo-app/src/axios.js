import axios from 'axios';


class AxiosService {
    constructor(options) {
        this.instance = axios.create({
            params: options || {} // do not remove this, its added to add params later in the config
        });
    }
    // instance.defaults.headers.common['Authorization'] = 'test';
    setInstanceAuth = (auth) => {
        this.instance.defaults.headers.common['Authorization'] = auth;
    }
}

export default new AxiosService();