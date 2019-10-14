import React from 'react'
import AuthenticationService from './AuthenticationService.js'
import './LoginComponent.css'
import Logo from '../SpikeLogo.png'

class RegisterComponent extends React.Component {
   /**
    * RegisterComponent is a component representing the register page. It is responsible for rendering
    * text inputs for email, username and password as well as an input to confirm password to send
    * to the backend to register.
    * It also displays a button to redirect to the login page and a button to submit the from
    * data.
    */
    name = new RegExp("^[a-zA-Z]+['-]*[a-zA-Z]+['-]*[a-zA-Z]+$");
    email = new RegExp('^[sS]{1}[0-9]{7}@student.rmit.edu.au$')  ;
    username = new RegExp('^[^ ]*$');
    

    constructor(props) {
        super(props)

        this.state = {
            email: '',
            username: '',
            password: '',
            confirmedPassword: '',
            firstName: '',
            lastName: '',
            hasRegisterFailed: false,
            showSuccessMessage: false,
            errorText: ''
        };

        this.handleChange = this.handleChange.bind(this)
        this.submitClicked = this.submitClicked.bind(this)
        this.cancelClicked = this.cancelClicked.bind(this)
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
     * Ensures that input fields are not empty before and that the password and confirmedPassword
     * inputs match before making a POST request to the backend.
     * If register succeeds, redirect to the user wall. If not display an error message.
     */
    submitClicked() {
      if (this.state.email.trim() === ""
          || this.state.username.trim() === ""
          || this.state.password.trim() === ""
          || this.state.confirmedPassword.trim() === ""
          || this.state.firstName.trim() === ""
          || this.state.lastName.trim() === "") {
        this.setState({ 
          hasRegisterFailed: true,
          errorText: "Error: Missing Fields." 
        });
      }
      else if (!this.name.test(this.state.firstName)) {
        this.setState({
          hasRegisterFailed: true,
          errorText: "Error: Invalid First Name. Can only contain (A-Z, ', -)."
        });
      }
      else if (!this.name.test(this.state.lastName)) {
        this.setState({
          hasRegisterFailed: true,
          errorText: "Error: Invalid Last Name. Can only contain (A-Z, ', -)."
        });
      }
      else if (!this.email.test(this.state.email)) {
        this.setState({
          hasRegisterFailed: true,
          errorText: "Error: Invalid Email. Must be in the format s1234567@student.rmit.edu.au."
        });
      }
      else if (!this.username.test(this.state.username)) {
        this.setState({
          hasRegisterFailed: true,
          errorText: "Error: Invalid Username. Cannot contain ' '."
        });
      }
      else if (this.state.password !== this.state.confirmedPassword) {
        this.setState({
          hasRegisterFailed: true,
          errorText: "Error: Entered Passwords Don't Match."
        });
      }
      else {
        AuthenticationService
            .executeJwtRegisterService(
              this.state.email,
              this.state.username,
              this.state.password,
              this.state.firstName,
              this.state.lastName)
            .then((response) => {
                AuthenticationService.registerSuccessfulLoginForJwt(response.data.id, response.data.token)
                this.props.history.push('/wall')
            }).catch(() => {
                this.setState({ showSuccessMessage: false })
                this.setState({ hasRegisterFailed: true })
            })
      }
    }

    /**
     * Redirects to the login page.
     */
    cancelClicked() {
      this.props.history.push(`/login`)
    }

    /**
     * Renders the register form HTML
     */
    render() {
        return (
            <div>
              <img src={Logo} alt="RMIT Spike logo"/>
              <h1>RMIT SPIKE</h1>
              <div className="form">
                {this.state.hasRegisterFailed && <div className="alert alert-warning" id="error">{this.state.errorText}</div>}
                {this.state.showSuccessMessage && <div id="success">Register Successful</div>}
                <div className="form-group">
                  <input type="text" className="form-control" placeholder="First Name" name="firstName" value={this.state.firstName} onChange={this.handleChange} />
                </div>
                <div className="form-group">
                  <input type="text" className="form-control" placeholder="Last Name" name="lastName" value={this.state.lastName} onChange={this.handleChange} />
                </div>
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
