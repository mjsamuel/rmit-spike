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
          visibility: "public",
          hasCreationFailed: false
      }

      this.handleChange = this.handleChange.bind(this)

      this.cancelClicked = this.cancelClicked.bind(this)
      this.confirmClicked = this.confirmClicked.bind(this)
    }

    confirmClicked() {
      return axios.post(`https://acbc885c-1f6a-41ff-b8bf-b56483583084.mock.pstmn.io/channel`, {
          username: this.state.username,
          channelName: this.state.channelName,
          visibility: this.state.visibility
      })
      .then((response) => {
         this.props.history.push(`/channel/${response.data.channelId}`)
      })
      .catch(() => {
        this.setState({ hasCreationFailed: true })
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
              <div className="jumbotron">
                <h1>Create a channel</h1>
                <p></p>
              </div>
              {this.state.hasCreationFailed && <div className="alert alert-warning">Something went wrong</div>}
              <form>
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
              </form>
            </div>
        )
    }
}

export default CreateChannelComponent
