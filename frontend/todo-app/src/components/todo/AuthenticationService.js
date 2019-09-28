// import axios from 'axios'
import axios from '../../axios.js'
import { API_URL } from '../../Constants'

export const USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'

class AuthenticationService {

    executeBasicAuthenticationService(username, password) {
        return axios.instance.get(`${API_URL}/basicauth`,
            { headers: { authorization: this.createBasicAuthToken(username, password) } })
    }

    executeJwtAuthenticationService(username, password) {
        const response = axios.instance.post(`${API_URL}/authenticate`, {
            username: username,
            password: password
        })
        return response
    }

    executeJwtRegisterService(email, username, password, confirmedPassword) {
      return axios.instance.post(`${API_URL}/user`, {
        email,
        username,
        password,
        confirmedPassword
      })
    }

    createBasicAuthToken(username, password) {
        return 'Basic ' + window.btoa(username + ":" + password)
    }

    registerSuccessfulLogin(username, password) {
        //let basicAuthHeader = 'Basic ' +  window.btoa(username + ":" + password)
        //console.log('registerSuccessfulLogin')

        sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, username)
        this.setupAxiosInterceptors(this.createBasicAuthToken(username, password))
    }

    registerSuccessfulLoginForJwt(username, token) {

        // console.log(username)
        // console.log(USER_NAME_SESSION_ATTRIBUTE_NAME)
        sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, username)
        let JWT_TOKEN = this.createJWTToken(token)
        // console.log(JWT_TOKEN)
        // this.setupAxiosInterceptors(JWT_TOKEN)
        this.setupAxiosGlobals(JWT_TOKEN)
    }

    createJWTToken(token) {
        return 'Bearer ' + token
    }


    logout() {
        sessionStorage.removeItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
    }

    isUserLoggedIn() {
        let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
        if (user === null) return false
        return true
    }

    getLoggedInUserName() {
        let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
        if (user === null) return ''
        return user
    }

    setupAxiosInterceptors(token) {
        console.log("Interceptors set up")
        axios.instance.interceptors.request.use(
            (config) => {
                console.log("Request intercepted")
                if (this.isUserLoggedIn()) {
                    config.headers.Authorization = token
                }
                console.log(config)
                return config
            }
        )
    }

    setupAxiosGlobals(token) {
        console.log("Entered globals")
        // sessionStorage.setItem("bearerToken", token);
        // axios.defaults.headers.common['Authorization'] = token;
        axios.setInstanceAuth(token);
        console.log("Axios state at globals: ", axios.instance)
    }
}

export default new AuthenticationService()
