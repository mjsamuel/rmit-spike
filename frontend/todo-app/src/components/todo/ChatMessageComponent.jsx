import React, {Component} from 'react'
import { FaUserAlt } from 'react-icons/fa';

class ChatMessageComponent extends Component {

  /**
   * ChatMessageComponent is a component representing a channel. It is responsible for rendering the
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
      username: '',
      content: '',
      timeNumber: 0,
      timeUnit: ''
    }
  }

  /**
   * Loads the most recent messages from the backend and sets the state accordingly
   */
  componentDidMount() {
    this.setState({
      username: this.props.username,
      content: this.props.content,
      timeNumber: this.props.timeNumber,
      timeUnit: this.props.timeUnit
    })
  }

  /**
   * Renders the comment HTML
   */
  render() {
    return (
      <div className="chat-message">
        <FaUserAlt/><span><b>u/{this.state.username}</b> said {this.state.timeNumber} {this.state.timeUnit} ago</span><br/>
        <p>{this.state.content}</p>
      </div>
    )
  }
}

export default ChatMessageComponent
