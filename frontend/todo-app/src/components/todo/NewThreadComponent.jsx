import React, {Component} from 'react'
import {USER_NAME_SESSION_ATTRIBUTE_NAME} from './AuthenticationService.js'
import './CreateChannelComponent.css'
import ChannelDataService from '../../api/todo/ChannelDataService.js'

class NewThreadComponent extends Component {

   /**
    * CreateChannelComponent is a component that allows for the creation of a channel. It provides
    * a form that has two input fields that specify the channels name and it visibility (public or
    * private). The username of the account that has created the channel is also sent with the form
    * in order to ensure that account is a moderator of the newly created channel
    */
    constructor(props) {
      super(props)

      this.state = {
          channelId: '',
          channelName: '',
          title: '',
          body: '',
          hasSubmissionFailed: false,
          errorText: ''
      };

      this.handleChange = this.handleChange.bind(this);

      this.cancelClicked = this.cancelClicked.bind(this);
      this.confirmClicked = this.confirmClicked.bind(this);
    }

    componentDidMount() {
      this.setState({
        channelId: this.props.location.state.channelId,
        channelName: this.props.location.state.channelName
      });
    }

    /**
     * Makes a request to the API when the confirm button is clicked, sending The
     * form data along with the current user's username
     */
    confirmClicked() {
      if (this.state.title.trim === "" || this.state.body.trim() === "") {
        this.setState({
          hasSubmissionFailed: true,
          errorText: "Error: Missing field/s"
        });

      }
      else {
        ChannelDataService.postThreadToChannel(this.state.channelId, this.state.channelId.title, this.state.body)
          .then(() => {
            let path = '/c/' + this.state.channelId;
            this.props.history.push(path);
          })
          .catch(() => {
            this.setState({
              hasSubmissionFailed: true,
              errorText: "Error: Failed communicating with backend"
            });
          })
      }
    }

    /**
     * Redirects the user to the home page if they click the cancel button
     */
    cancelClicked() {
      let path = '/c/' + this.state.channelId;
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
                <h1>Post a thread</h1>
                <p>Start a new thread in c/{this.state.channelName}</p>
              </div>
              {this.state.hasSubmissionFailed && <div className="alert alert-warning">{this.state.errorText}</div>}
              <div className="form-group">
                <label htmlFor="title">Title:</label>
                <input type="text" className="form-control" name="title" onChange={this.handleChange} placeholder="Enter thread title" />
              </div>
              <div className="form-group">
                <label htmlFor="visibility">Body:</label>
                <textarea className="form-control" rows="4" name="body" onChange={this.handleChange} placeholder="Enter thread body"></textarea>
              </div>
              <div className="button-group">
                <button type="button" className="btn btn-secondary" onClick={this.cancelClicked}>Cancel</button>
                <button type="button" className="btn btn-success ml-2" onClick={this.confirmClicked}>Confirm</button>
              </div>
            </div>
        )
    }
}

export default NewThreadComponent
