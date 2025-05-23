import React from 'react'
import {USER_NAME_SESSION_ATTRIBUTE_NAME} from './AuthenticationService.js'
import './CreateChannelComponent.css'
import ThreadDataService from '../api/ThreadDataService.js'

class NewThreadComponent extends React.Component {

   /**
    * NewThreadComponent is a component that allows for the creation of a thread. It provides
    * a form that has two input fields that specify the thread's title and body. The username
    * of the creator of the thread as well as the channelId of where it is posted is also sent
    * as well.
    * It makes use of the following props:
    * @param channelId: id of the channel
    * @param channelName: name of the channel
    */
    constructor(props) {
      super(props)

      this.state = {
          channelId: '',
          channelName: '',
          title: '',
          body: '',
          hasSubmissionFailed: false,
          recievedProps: false,
          showSuccessMessage: false,
          errorText: ''
      };

      this.handleChange = this.handleChange.bind(this);
      this.cancelClicked = this.cancelClicked.bind(this);
      this.confirmClicked = this.confirmClicked.bind(this);
    }


    /**
     * Sets the state of the component from it's props after the constructor has
     * finished executing
     */
    componentDidMount() {
      if (!this.props.location.state) {
        this.setState({
          hasSubmissionFailed: true,
          errorText: "No channel specified",
          recievedProps: false
        });
      }
      else {
        this.setState({
          channelId: this.props.location.state.channelId,
          channelName: this.props.location.state.channelName,
          recievedProps: true
        });
      }

    }

    /**
     * Makes a request to the API when the confirm button is clicked, sending The
     * form data along with the current user's username and channel id
     */
    confirmClicked() {
      if (this.state.title.trim() === "" || this.state.body.trim() === "") {
        this.setState({
          hasSubmissionFailed: true,
          errorText: "Error: Missing field/s"
        });
      }
      else {
        let tagChannelPattern = new RegExp("c\/[a-zA-Z0-9]+");
        let taggedChannels = this.state.body.match(tagChannelPattern);
        var taggedChannel;
        if (taggedChannels) {
          taggedChannel = taggedChannels[0].substring(2);
        }

        const request = {
          channelId: this.state.channelId,
          title: this.state.title,
          content: this.state.body,
          datetime: Date.now(),
          authorId: sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME),
          taggedChannels: taggedChannel
        }

        ThreadDataService.newThread(request)
        .then(() => {
          this.setState({showSuccessMessage: true})
          let path = '/c/' + this.state.channelId;
          this.props.history.push(path);
        })
        .catch((error) => {
          console.log(error)
          this.setState({
            hasSubmissionFailed: true,
            errorText: "Error: Failed communicating with backend"
          })
          if (error.response) {
            // The request was made and the server responded with a status code
            // that falls out of the range of 2xx
            console.log(error.response.data);
            console.log(error.response.status);
            console.log(error.response.headers);
          } else if (error.request) {
            // The request was made but no response was received
            // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
            // http.ClientRequest in node.js
            console.log(error.request);
          } else {
            // Something happened in setting up the request that triggered an Error
            console.log('Error', error.message);
          }
          console.log(error.config);
        })
      }
    }

    /**
     * Redirects the user to the channel page the came from if they click the cancel button
     */
    cancelClicked() {
      var path;
      if (this.state.recievedProps) {
        path = '/c/' + this.state.channelId;
      }
      else {
        path = '/'
      }
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
    * Renders the new thread form HTML
    */
    render() {
        return (
            <div className="channel-form">
              <div className="jumbotron">
                <h1>Post a thread</h1>
                <p>Start a new thread in c/{this.state.channelName}</p>
              </div>
              {this.state.hasSubmissionFailed && <div className="alert alert-warning" id="error">{this.state.errorText}</div>}
              {this.state.showSuccessMessage && <div id="success">Thread created successfully</div>}
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
                {this.state.recievedProps && <button type="button" className="btn btn-success ml-2" onClick={this.confirmClicked}>Confirm</button>}
              </div>
            </div>
        )
    }
}

export default NewThreadComponent
