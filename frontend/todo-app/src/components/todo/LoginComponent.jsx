import React, { Component } from 'react'
import AuthenticationService from './AuthenticationService.js'
import './LoginComponent.css'
import Logo from '../../SpikeLogo.png'

class LoginComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            username: '',
            password: '',
            hasLoginFailed: false,
            showSuccessMessage: false
        }
        // this.handleUsernameChange = this.handleUsernameChange.bind(this)
        // this.handlePasswordChange = this.handlePasswordChange.bind(this)
        this.handleChange = this.handleChange.bind(this)
        this.loginClicked = this.loginClicked.bind(this)
        this.registerClicked = this.registerClicked.bind(this)
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

    // handleUsernameChange(event) {
    //     console.log(event.target.name);
    //     this.setState(
    //         {
    //             [event.target.name]
    //               :event.target.value
    //         }
    //     )
    // }

    // handlePasswordChange(event) {
    //     console.log(event.target.value);
    //     this.setState({password:event.target.value})
    // }

    async loginClicked() {
        //sept,dummy
        // if(this.state.username==='sept' && this.state.password==='dummy'){
        //     AuthenticationService.registerSuccessfulLogin(this.state.username,this.state.password)
        //     this.props.history.push(`/welcome/${this.state.username}`)
        //     //this.setState({showSuccessMessage:true})
        //     //this.setState({hasLoginFailed:false})
        // }
        // else {
        //     this.setState({showSuccessMessage:false})
        //     this.setState({hasLoginFailed:true})
        // }

        // AuthenticationService
        // .executeBasicAuthenticationService(this.state.username, this.state.password)
        // .then(() => {
        //     AuthenticationService.registerSuccessfulLogin(this.state.username,this.state.password)
        //     this.props.history.push(`/welcome/${this.state.username}`)
        // }).catch( () =>{
        //     this.setState({showSuccessMessage:false})
        //     this.setState({hasLoginFailed:true})
        // })
        if (this.state.username.trim() === "" || this.state.password.trim() === "") {
          this.setState({ hasLoginFailed: true})
        }
        else {
          await AuthenticationService
              .executeJwtAuthenticationService(this.state.username, this.state.password)
              .then((response) => {
                  //This stores the user id, not the username - make sure auth changes to reflect this
                  AuthenticationService.registerSuccessfulLoginForJwt(response.data.id, response.data.token)
                  this.props.history.push(`/welcome/${this.state.username}`)
              }).catch(() => {
                  this.setState({ showSuccessMessage: false })
                  this.setState({ hasLoginFailed: true })
              })
        }
    }

    registerClicked() {
      this.props.history.push(`/register`)
    }

    render() {
        return (
            <div>
              <img src={Logo} alt="RMIT Spike logo"/>
              <h1>RMIT SPIKE</h1>
              <div className="form">
                {/*<ShowInvalidCredentials hasLoginFailed={this.state.hasLoginFailed}/>*/}
                {this.state.hasLoginFailed && <div className="alert alert-warning" id="error">Invalid Credentials or something is wrong</div>}
                {this.state.showSuccessMessage && <div id="success">Login Sucessful</div>}
                {/*<ShowLoginSuccessMessage showSuccessMessage={this.state.showSuccessMessage}/>*/}
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
