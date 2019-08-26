import React, {Component} from 'react'
import ThreadDataService from '../../api/todo/ThreadDataService.js'
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
      hasErrorOccured: false
    }
  }

  /**
   * Loads the current list of threads that belong to the current channel from the backend and updates
   * the state variables
   */
  componentDidMount() {
    const { match: { params } } = this.props;
    console.log(params.channelId)

    var data = ThreadDataService.retrieveChannelThreads(params.channelId)
    this.setState({
      channelName: data.channelName,
      threads: data.threads
    })
  }

  /**
   * Renders the thread HTML
   */
  render() {
    return (
      <div className="thread-list">
        <h1>{this.state.channelName}</h1>
        {this.state.threads.map((thread, index) => {
          return (
            <div className="thread-card">
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
    )
  }
}

export default ChannelComponent
