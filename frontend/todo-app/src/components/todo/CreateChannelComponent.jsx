import React, {Component} from 'react'
import {USER_NAME_SESSION_ATTRIBUTE_NAME} from './AuthenticationService.js'
import {API_URL} from '../../Constants'
import './CreateChannelComponent.css'
import axios from 'axios'

class CreateChannelComponent extends React.Component {

    constructor(props) {
      super(props)

      this.state = {
          username: sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME),
          channelName: "",
          visibility: "public"
      }

      this.handleChange = this.handleChange.bind(this)

      this.cancelClicked = this.cancelClicked.bind(this)
      this.confirmClicked = this.confirmClicked.bind(this)
    }

    confirmClicked() {
      return axios.post(`${API_URL}/channel`, {
          username: this.state.username,
          channelName: this.state.channelName,
          visibility: this.state.visibility
      })
    }

    cancelClicked() {
      let path = '/'
      this.props.history.push(path);
    }

    handleChange(event) {
        this.setState(
            {
                [event.target.name]
                    : event.target.value
            }
        )
    }

    render() {
        return (
            <div className="channel-form">
              <div class="jumbotron">
                <h1>Create a channel</h1>
                <p></p>
              </div>
              <form>
                <input type="hidden" name="username" value={this.username} />
                <div class="form-group">
                  <label for="channelName">Channel name:</label>
                  <input type="text" class="form-control" id="channelName"  name="channelName"  onChange={this.handleChange} placeholder="Enter channel name" required />
                </div>
                <div class="form-group">
                  <label for="visibility">Channel visibility:</label>
                  <select class="form-control" id="visibility" name="visibility" onChange={this.handleChange}>
                    <option value="public">Public</option>
                    <option value="private">Private</option>
                  </select>
                </div>
                <div className="button-group">
                  <button type="button" className="btn btn-secondary" onClick={this.cancelClicked}>Cancel</button>
                  <button type="button" className="btn btn-success ml-2" onClick={this.confirmClicked}>Confirm</button>
                </div>
              </form>
            </div>
        )
    }
}

export default CreateChannelComponent
