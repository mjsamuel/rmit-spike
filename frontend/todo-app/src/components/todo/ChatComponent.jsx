import React, {Component} from 'react'
import ChannelDataService from '../../api/todo/ChannelDataService.js'
import './ChatComponent.css';
import { FaUserAlt } from 'react-icons/fa';

class ChatComponent extends Component {

  /**
   * ChannelComponent is a component representing a channel. It is responsible for rendering the
   * content of a channel. This includes the list of threads that have been posted to that channel,
   * along with each threads, title, author, the number of comments, the number of upspikes and the
   * It makes use of the following props, which are also displayed on a card on the user feed
   * or channel page
   * It makes use of the following route paramaters in order to idetify which channel is being
   * displayed:
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
    // const { match: { params } } = this.props;
    // var data = ChannelDataService.retrieveChannelThreads(params.channelId)
    // this.setState({
    //   channelName: data.channelName,
    //   threads: data.threads
    // })
  }

  /**
   * Renders the thread HTML
   */
  render() {
    return (
      <div className="chat-panel">
        <div className="chat-banner">CHAT</div>
        <div className="chat-messages">
          <div className="chat-message">
            <FaUserAlt/><span><b>u/user</b> said 2 minutes ago</span><br/>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
            incididunt ut labore et dolore magna aliqua</p>
          </div>
          <div className="chat-message">
            <FaUserAlt/><span><b>u/user</b> said 2 minutes ago</span><br/>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
            incididunt ut labore et dolore magna aliqua</p>
          </div>
          <div className="chat-message">
            <FaUserAlt/><span><b>u/user</b> said 2 minutes ago</span><br/>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
            incididunt ut labore et dolore magna aliqua</p>
          </div>
        </div>
        <div>
          <input type="text" className="form-control" placeholder="Type a message..." name="message"/>
        </div>
      </div>
    )
  }
}

export default ChatComponent
