import React, {Component} from 'react'
import './ChannelComponent.css';
import { FaRegComment, FaAngleUp } from 'react-icons/fa';

class ThreadListItem extends Component {

  /**
   * ThreadListItem is a component representing a thread in a list of threads displayed either a channel
   * page or a wall page. It is repsonsible for rendering title of the thread, the author of the thread
   * and optionally which channel/s the thread was posted in.
   * It makes use of the following props
   * @param thread: thread object that contains all the thread information
   * @param displayOrigin: boolean that states wether the origin of where the thread was posted in is displayed
   */
  constructor(props) {
    super(props)

    this.state = {
      thread: {},
      displayOrigin: false
    }
  }

  /**
   * Sets the state of the component based on the props that are passed in
   */
  componentDidMount() {
    this.setState({
      thread: this.props.thread,
      displayOrigin: this.props.displayOrigin
    })

  }

  /**
   * Renders the HTML of a single thread list item
   */
  render() {
    return (
      <div className="thread-list-item" key={this.state.thread.id} id={"thread-" + this.state.thread.id}>
        {this.state.displayOrigin && <a href={"/c/" + this.state.thread.channelOrigin} className="channel-origin">posted in {"/c" + this.state.thread.channelOrigin}<br/></a>}
        <a href={"/thread/" + this.state.thread.id} className="thread-list-title" id={"thread-list-title-" + this.state.thread.id}>{this.state.thread.title}</a><br/>
        <a href={"/u/" + this.state.thread.author} className="thread-list-author" id={"thread-list-author-" + this.state.thread.id}>{"u/" + this.state.thread.author}</a><br/>
        <div className="details-bar">
          <span><FaRegComment/> {this.state.thread.noComments} comments | <FaAngleUp/> {this.state.thread.upspikes} Upspikes</span>
        </div>
      </div>
    )
  }
}

export default ThreadListItem
