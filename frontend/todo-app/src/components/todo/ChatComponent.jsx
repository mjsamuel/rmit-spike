import React, {Component} from 'react'
import './ChatComponent.css';
import ChatMessageComponent from './ChatMessageComponent.jsx'
import { FaUserAlt } from 'react-icons/fa';

class ChatComponent extends Component {

  /**
   * ChatComponent is a component that represent a chat panel for a given channel. It is responsible
   * for rendering and recieving the most recent messages that have been sent as well as seding
   * messages.
   * It makes use of the following  paramaters to determine which messages should be displayed:
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

    }
    else {

    }
    // const { match: { params } } = this.props;
    // var data = ChannelDataService.retrieveChannelThreads(params.channelId)
    // this.setState({
    //   channelName: data.channelName,
    //   threads: data.threads
    // })
  }

  /**
   * Renders the chat panel HTML
   */
  render() {
    return (
      <div className="chat-panel">
        <div className="chat-banner">CHAT</div>
        <div className="chat-messages">
          <ChatMessageComponent username="user" timeNumber="2" timeUnit="minutes"
          content="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
          incididunt ut labore et dolore magna aliqua"/>
          <ChatMessageComponent username="user" timeNumber="2" timeUnit="minutes"
          content="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
          incididunt ut labore et dolore magna aliqua"/>
          <ChatMessageComponent username="user" timeNumber="2" timeUnit="minutes"
          content="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
          incididunt ut labore et dolore magna aliqua"/>
          <ChatMessageComponent username="user" timeNumber="2" timeUnit="minutes"
          content="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
          incididunt ut labore et dolore magna aliqua"/>
          <ChatMessageComponent username="user" timeNumber="2" timeUnit="minutes"
          content="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
          incididunt ut labore et dolore magna aliqua"/>
        </div>
        <div>
          <input type="text" className="form-control" placeholder="Type a message..." name="message"/>
        </div>
      </div>
    )
  }
}

export default ChatComponent
