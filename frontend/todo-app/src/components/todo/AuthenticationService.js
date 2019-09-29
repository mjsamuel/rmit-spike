// import axios from 'axios'
import axios, { AXIOS_HEADERS } from '../../axios.js'
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
        sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, username)
        let JWT_TOKEN = this.createJWTToken(token)
        this.setupAxiosGlobals(JWT_TOKEN)
    }

    createJWTToken(token) {
        return 'Bearer ' + token
    }


    logout() {
        sessionStorage.removeItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
        sessionStorage.removeItem(AXIOS_HEADERS);
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

    setupAxiosGlobals(token) {
        console.log("Entered globals")
        if (this.isUserLoggedIn()) {
            axios.setInstanceAuth(token);
            console.log("Axios state at globals: ", axios.instance)
        }

    }
}

export default new AuthenticationService()
