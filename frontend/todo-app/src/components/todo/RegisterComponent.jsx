import React, { Component } from 'react'
import AuthenticationService from './AuthenticationService.js'
import './LoginComponent.css'
import Logo from '../../SpikeLogo.png'

class RegisterComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            email: '',
            username: '',
            password: '',
            confirmedPassword: '',
            hasRegisterFailed: false,
            showSuccessMessage: false
        }

        this.handleChange = this.handleChange.bind(this)
        this.submitClicked = this.submitClicked.bind(this)
        this.cancelClicked = this.cancelClicked.bind(this)
    }

    handleChange(event) {
        //console.log(this.state);
        this.setState(
            {
                [event.target.name]
                    : event.target.value
            }
        )
    }

    submitClicked() {
      if (this.state.password !== this.state.confirmedPassword ||
          this.state.email.trim() === "" ||
          this.state.username.trim() === "" ||
          this.state.password.trim() === "" ||
          this.state.confirmedPassword.trim === "") {
        this.setState({ hasRegisterFailed: true })
      }
      else {
        AuthenticationService
            .executeJwtRegisterService(this.state.email, this.state.username, this.state.password, this.state.confirmedPassword)
            .then((response) => {
                AuthenticationService.registerSuccessfulLoginForJwt(response.data.id, response.data.token)
                this.props.history.push(`/welcome/${this.state.username}`)
            }).catch(() => {
                this.setState({ showSuccessMessage: false })
                this.setState({ hasRegisterFailed: true })
            })
      }
    }

    cancelClicked() {
      this.props.history.push(`/login`)
    }

    render() {
        return (
            <div>
              <img src={Logo} alt="RMIT Spike logo"/>
              <h1>RMIT SPIKE</h1>
              <div className="form">
                {this.state.hasRegisterFailed && <div className="alert alert-warning" id="error">Invalid Credentials or something is wrong</div>}
                {this.state.showSuccessMessage && <div id="success">Register Sucessful</div>}
                <div className="form-group">
                  <input type="text" className="form-control" placeholder="Email" name="email" value={this.state.email} onChange={this.handleChange} />
                </div>
                <div className="input-group mb-3">
                  <div className="input-group-prepend">
                    <span className="input-group-text">u/</span>
                  </div>
                  <input type="text" className="form-control" placeholder="Username" name="username" value={this.state.username} onChange={this.handleChange} />
                </div>
                <div className="form-group">
                  <input type="password" className="form-control" placeholder="Password" name="password" value={this.state.password} onChange={this.handleChange} />
                </div>
                <div className="form-group">
                  <input type="password" className="form-control" placeholder="Confirm Password" name="confirmedPassword" value={this.state.confirmedPassword} onChange={this.handleChange} />
                </div>
                <div className="button-group">
                  <button className="btn btn-secondary" onClick={this.cancelClicked}>Cancel</button>
                  <button className="btn btn-success ml-2" onClick={this.submitClicked}>Submit</button>
                </div>
              </div>
            </div>
        )
    }
}

export default RegisterComponent
