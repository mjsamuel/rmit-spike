import React from 'react'
import ChatDataService from '../api/ChatDataService.js'
import './ChatComponent.css';
import ChatMessageComponent from './ChatMessageComponent.jsx'
import SockJsClient from 'react-stomp'
import { API_URL } from '../Constants'
import { AXIOS_HEADERS } from '../axios.js'

class ChatComponent extends React.Component {

  /**
   * ChatComponent is a component that represent a chat panel for a given channel. It is responsible
   * for rendering and recieving the most recent messages that have been sent as well as sending
   * messages.
   * It takes the following paramaters to determine which messages should be displayed:
   * @param channelId: id of the channel
   */
  constructor(props) {
    super(props);

    this.state = {
      messages: [],
      currentMessage: "",
      clientConnected: false
    };

    this.chatBottom = React.createRef();
    this.handleChange = this.handleChange.bind(this);
    this.handleSentMessage = this.handleSentMessage.bind(this);
    this.scrollToBottom = this.scrollToBottom.bind(this);
    this.handleMessageReceived = this.handleMessageReceived.bind(this);
  }

  /**
   * Loads the most recent messages from the backend and sets the state accordingly
   */
  componentDidMount() {
    if (this.props.channelId != null) {
      ChatDataService.retrieveMessages(this.props.channelId)
      .then((response) => {
        console.log(response)
        this.setState({
          messages: response.data,
        })
      }).catch(function (error) {
        console.log("Error getting chat messages")
        console.log(error)
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
   * Finds the element at the very bottom of the chat messages div and scrolls
   * down to it smoothly.
   */
  scrollToBottom() {
    this.el.scrollIntoView({ behavior: 'smooth' })
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
      );
  }

  handleSentMessage(e) {
    try {
      if (e) e.preventDefault();
      if (this.state.currentMessage.trim() !== "") {
        let newMessage = {
          authorId: Number(sessionStorage.getItem("authenticatedUser")),
          content: this.state.currentMessage,
          datetime: Date.now()
        };
        this.clientRef.sendMessage(
          `/app/channel/${this.props.channelId}`,
          JSON.stringify(newMessage)
          );
        
        this.setState({
          currentMessage: ""
        }, () => {
          if (e) this.scrollToBottom();
        });
      }
      return true;
    } catch (e) {
      console.log(e)
      return false;
    }

  }

  handleMessageReceived(message) {
    this.setState(prevState => ({
      messages: [...prevState.messages, message]
    }), () => {
      this.scrollToBottom();
    });
  }

  /**
   * Renders the chat panel HTML
   */
  render() {
    const headers = JSON.parse(sessionStorage.getItem(AXIOS_HEADERS))
    // const headers = {
    //   "Authorization": axiosHeaders.common.Authorization
    // };
    // console.log(headers)
    return (
      <div className="chat-panel">
        <div>
          <SockJsClient url={`${API_URL}/spark-websocket`} topics={[`/topic/${this.props.channelId}`]} 
          headers={headers} subscribeHeaders={headers}
          onMessage={(msg) => { this.handleMessageReceived(msg) }}
          ref={(client) => {this.clientRef = client}}
          onConnect={ () => { this.setState({ clientConnected: true }); }}
          onDisconnect={ () => this.setState({ clientConnected: false })}
          debug={false}
          options={{ transports: ['xhr-polling'] }}
          />
        </div>

        <div className="chat-banner">CHAT</div>
        <div className="chat-messages" id="chat-messages">
          {this.state.messages.map((message, index) => {
              return (
                  <ChatMessageComponent username={message.username} timeDelta={message.timeDelta}
                  content={message.content} key={index}/>
              )
            })
          }
          <span id="messages-bottom" ref={r => (this.el = r)}></span>
        </div>
        <div>
          <form autoComplete="off" onSubmit={this.handleSentMessage}>
            <input
              type="text"
              className="form-control"
              placeholder="Type a message..."
              name="currentMessage"
              onChange={this.handleChange}
              value={this.state.currentMessage}
            />
          </form>
        </div>
      </div>
    )
  }
}

export default ChatComponent
