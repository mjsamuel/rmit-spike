import axios from 'axios';

export const AXIOS_HEADERS = 'axiosHeaders';

class AxiosService {
  constructor(options) {
    this.instance = axios.create({
      params: options || {} // do not remove this, its added to add params later in the config
    });

    this.restoreHeaders();
  }

  restoreHeaders = () => {
    const restoredHeaders = JSON.parse(sessionStorage.getItem(AXIOS_HEADERS));

    if (restoredHeaders) {
      this.instance.defaults.headers = restoredHeaders;
    }
  };

  setInstanceAuth = auth => {
    this.instance.defaults.headers.common["Authorization"] = auth;
    sessionStorage.setItem(
      AXIOS_HEADERS,
      JSON.stringify(this.instance.defaults.headers)
    );
  };
}

export default new AxiosService();