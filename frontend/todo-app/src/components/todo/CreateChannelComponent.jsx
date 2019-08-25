import React, {Component} from 'react'
import {USER_NAME_SESSION_ATTRIBUTE_NAME} from './AuthenticationService.js'
import './CreateChannelComponent.css'
import ChannelDataService from '../../api/todo/ChannelDataService.js'

class CreateChannelComponent extends React.Component {

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

    confirmClicked() {
      var data = ChannelDataService.createChannel(this.state.username,
        this.state.channelName, this.state.visibility)
      this.props.history.push(`/c/${data.channelId}`)
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
