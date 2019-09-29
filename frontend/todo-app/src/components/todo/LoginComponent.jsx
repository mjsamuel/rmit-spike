import React, { Component } from 'react'
import AuthenticationService from './AuthenticationService.js'
import './LoginComponent.css'
import Logo from '../../SpikeLogo.png'

class LoginComponent extends Component {
   /**
    * LoginComponent is a component representing the login page. It is responsible for rendering
    * text inputs for both the username and password to send the values inputted to the backend.
    * It also displays a button to redirect to the register page and a button to submit the from
    * data.
    */
    constructor(props) {
        super(props)

        this.state = {
            username: '',
            password: '',
            hasLoginFailed: false,
            showSuccessMessage: false
        }

        this.handleChange = this.handleChange.bind(this)
        this.loginClicked = this.loginClicked.bind(this)
        this.registerClicked = this.registerClicked.bind(this)
    }

    /**
     * Updates the state of the component 'onChange' of an input field
     * @param id: the event object generated
     */
    handleChange(event) {
        this.setState(
            {
                [event.target.name]
                    : event.target.value
            }
        )
    }

    /**
     * Ensures that input fields are not empty before making a POST request to the backend.
     * If login succeeds, redirect to the user wall. If not display an error message.
     */
    loginClicked() {
        if (this.state.username.trim() === "" || this.state.password.trim() === "") {
          this.setState({ hasLoginFailed: true})
        } else {
          AuthenticationService
              .executeJwtAuthenticationService(this.state.username, this.state.password)
              .then((response) => {
                  //This stores the user id, not the username - make sure auth changes to reflect this
                  AuthenticationService.registerSuccessfulLoginForJwt(response.data.id, response.data.token)
                  this.props.history.push(`/wall`)
              }).catch(() => {
                  this.setState({ showSuccessMessage: false })
                  this.setState({ hasLoginFailed: true })
              })
        }
    }

    /**
     * Redirects to the register page.
     */
    registerClicked() {
      this.props.history.push(`/register`)
    }

    /**
     * Renders the login form HTML
     */
    render() {
        return (
            <div>
              <img src={Logo} alt="RMIT Spike logo"/>
              <h1>RMIT SPIKE</h1>
              <div className="form">
                {this.state.hasLoginFailed && <div className="alert alert-warning" id="error">Invalid Credentials or something is wrong</div>}
                {this.state.showSuccessMessage && <div id="success">Login Sucessful</div>}
                <div className="form-group">
                  <input type="text" className="form-control" placeholder="Username" name="username" value={this.state.username} onChange={this.handleChange} />
                </div>
                <div className="form-group">
                  <input type="password" className="form-control" placeholder="Password" name="password" value={this.state.password} onChange={this.handleChange} />
                </div>
                <div className="button-group">
                  <button className="btn btn-secondary" onClick={this.registerClicked}>Register</button>
                  <button className="btn btn-success ml-2" onClick={this.loginClicked}>Login</button>
                </div>
              </div>
            </div>
        )
    }
}

export default LoginComponent
