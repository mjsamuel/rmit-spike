import React, {Component} from 'react'
import ChatDataService from '../../api/todo/ChatDataService.js'
import './ChatComponent.css';
import ChatMessageComponent from './ChatMessageComponent.jsx'

class ChatComponent extends Component {

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
      currentMessage: ""
    };

    this.chatBottom = React.createRef();
    this.handleChange = this.handleChange.bind(this);
    this.handleSentMessage = this.handleSentMessage.bind(this);
    this.scrollToBottom = this.scrollToBottom.bind(this);
  }

  /**
   * Loads the most recent messages from the backend and sets the state accordingly
   */
  componentDidMount() {
    if (this.props.channelId != null) {
      var data = ChatDataService.retrieveMessages(this.props.channelId);
      this.setState({ messages: data.messages });
    } else {

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
    e.preventDefault();

    if (this.state.currentMessage.trim() !== "") {
      const newMessage = {
        username: "currentUser",
        content: this.state.currentMessage,
        timeNumber: 0,
        timeUnit: "seconds"
      };

      const messages = this.state.messages.concat(newMessage);
      this.setState({
        messages: messages,
        currentMessage: ""
      }, () => {
        this.scrollToBottom();
      });
    }

  }

  /**
   * Renders the chat panel HTML
   */
  render() {
    return (
      <div className="chat-panel">
        <div className="chat-banner">CHAT</div>
        <div className="chat-messages" id="chat-messages">
          {this.state.messages.map((message, index) => {
              return (
                  <ChatMessageComponent username={message.username} timeNumber={message.timeNumber}
                  timeUnit={message.timeUnit} content={message.content} key={index}/>
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
