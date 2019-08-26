import React, {Component} from 'react'
import {USER_NAME_SESSION_ATTRIBUTE_NAME} from './AuthenticationService.js'
import './CreateChannelComponent.css'
import ChannelDataService from '../../api/todo/ChannelDataService.js'

class CreateChannelComponent extends React.Component {

   /**
    * CreateChannelComponent is a component that allows for the creation of a channel. It provides
    * a form that has two input fields that specify the channels name and it visibility (public or
    * private). The username of the account that has created the channel is also sent with the form
    * in order to ensure that account is a moderator of the newly created channel
    */
    constructor(props) {
      super(props)

      this.state = {
          username: sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME),
          channelName: "",
          visibility: "public",
      }

      this.handleChange = this.handleChange.bind(this)

      this.cancelClicked = this.cancelClicked.bind(this)
      this.confirmClicked = this.confirmClicked.bind(this)
    }

    /**
     * Makes a request to the API when the confirm button is clicked, sending The
     * form data along with the current user's username
     */
    confirmClicked() {
      var data = ChannelDataService.createChannel(this.state.username,
        this.state.channelName, this.state.visibility)
      this.props.history.push(`/c/${data.channelId}`)
    }

    /**
     * Redirects the user to the home page if the clock the cancel button
     */
    cancelClicked() {
      let path = '/'
      this.props.history.push(path);
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
    * Renders the thread HTML
    */
    render() {
        return (
            <div className="channel-form">
              <div className="jumbotron">
                <h1>Create a channel</h1>
                <p></p>
              </div>
              {this.state.hasCreationFailed && <div className="alert alert-warning">Something went wrong</div>}
                <input type="hidden" name="username" value={this.username} />
                <div className="form-group">
                  <label htmlFor="channelName">Channel name:</label>
                  <input type="text" className="form-control" id="channelName"  name="channelName"  onChange={this.handleChange} placeholder="Enter channel name" required />
                </div>
                <div className="form-group">
                  <label htmlFor="visibility">Channel visibility:</label>
                  <select className="form-control" id="visibility" name="visibility" onChange={this.handleChange}>
                    <option value="public">Public</option>
                    <option value="private">Private</option>
                  </select>
                </div>
                <div className="button-group">
                  <button type="button" className="btn btn-secondary" onClick={this.cancelClicked}>Cancel</button>
                  <button type="button" className="btn btn-success ml-2" onClick={this.confirmClicked}>Confirm</button>
                </div>
            </div>
        )
    }
}

export default CreateChannelComponent
