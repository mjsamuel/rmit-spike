// import axios from 'axios'
import axios, { AXIOS_HEADERS } from '../../axios.js'
import { API_URL } from '../../Constants'

export const USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'

class AuthenticationService {

    executeJwtAuthenticationService(username, password) {
        const response = axios.instance.post(`${API_URL}/authenticate`, {
            username: username,
            password: password
        })
        return response
    }

    executeJwtRegisterService(email, username, password, firstName, lastName) {
      return axios.instance.post(`${API_URL}/api/user`, {
        email: email,
        username: username,
        password: password,
        firstName: firstName,
        lastName: lastName
      })
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
        if (this.isUserLoggedIn()) {
            axios.setInstanceAuth(token);
        }

    }
}

export default new AuthenticationService()
