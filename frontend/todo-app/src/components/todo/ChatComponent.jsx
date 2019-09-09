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
    super(props)

    this.state = {
      messages: [],
    }
  }

  /**
   * Loads the most recent messages from the backend and sets the state accordingly
   */
  componentDidMount() {
    if (this.props.channelId != null) {
      var data = ChatDataService.retrieveMessages(this.props.channelId)
      this.setState({ messages: data.messages })
    }
    else {

    }
  }

  scrollToBottom = () => {
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
        </div>
        <div>
          <input type="text" className="form-control" placeholder="Type a message..." name="message"/>
        </div>
      </div>
    )
  }
}

export default ChatComponent
