import React, {Component} from 'react'
import ChannelDataService from '../../api/todo/ChannelDataService.js'
import './ChannelComponent.css';
import { FaRegComment, FaAngleUp } from 'react-icons/fa';


class ChannelComponent extends Component {

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
      channelName: '',
      threads: [],
      subscribed: false,
      hasErrorOccured: false
    }

    this.subscribeClicked = this.subscribeClicked.bind(this)
    this.newThreadClicked = this.newThreadClicked.bind(this)
  }

  /**
   * Loads the current list of threads that belong to the current channel from the backend and updates
   * the state variables
   */
  componentDidMount() {
    const { match: { params } } = this.props;
    var data = ChannelDataService.retrieveChannelThreads(params.channelId)
    this.setState({
      channelName: data.channelName,
      threads: data.threads
    })
  }

  /**
   * Notifies the backend that the user has subscribed/unsubscribed and changes the style
   * of the button accordigly by flipping the boolean that indicates the users subscription
   * state
   */
  subscribeClicked() {
    this.setState({subscribed: !this.state.subscribed})
  }

  /**
   * Redirects to the new thread page, passing a channel id as a paramater
   */
  newThreadClicked() {
    const { match: { params } } = this.props;
    this.props.history.push(`/new-thread`, {channelId: params.channelId})
  }
  /**
   * Renders the thread HTML
   */
  render() {
    return (
      <>
        <div className="thread-list">
          <h1>{this.state.channelName}</h1>
          {this.state.threads.map((thread, index) => {
            return (
              <div className="thread-card" id={thread.id}>
                <a href={"/thread/" + thread.id} className="title">{thread.title}</a><br/>
                <a href={"/user/" + thread.author} className="author">{thread.author}</a><br/>
                <div className="details-bar">
                  <span><FaRegComment/> {thread.noComments} comments | <FaAngleUp/> {thread.upspikes} Upspikes</span>
                </div>
              </div>
              )
            })
          }
          <ul className="pagination">
            <li className="page-item"><a className="page-link" href="#">Previous</a></li>
            <li className="page-item"><a className="page-link" href="?page1">1</a></li>
            <li className="page-item"><a className="page-link" href="#">Next</a></li>
          </ul>
        </div>
        <div className="side-panel">
          <button id="subsribe-btn" className={this.state.subscribed ? "btn btn-secondary ml-2" : "btn btn-success ml-2"} onClick={this.subscribeClicked}>
            {this.state.subscribed ? "Unsubscribe" : "Subscribe"}
          </button>
          <button className="btn btn-success ml-2" onClick={this.newThreadClicked}>New Thread</button><br/>
        </div>
      </>
    )
  }
}

export default ChannelComponent
