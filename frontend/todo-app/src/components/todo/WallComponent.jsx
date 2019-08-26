import React, {Component} from 'react'
import WallDataService from '../../api/todo/WallDataService.js'
import './WallComponent.css';
import { FaRegComment, FaAngleUp } from 'react-icons/fa';


class WallComponent extends Component {

  /**
   * ChannelComponent is a component that represents the user's wall. It is responsible for rendering
   * all the most recent threads that have been posted to the channels that the user is subscribed to,
   * displaying the threads. title, author, what channels it has been posted to, number of comments and
   * the number of upspikes
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
   * Loads the current list of threads that belong to the current channels the user is subscribed to
   * and updates the state variables
   */
  componentDidMount() {
    const { match: { params } } = this.props;
    console.log(params.channelId)

    var data = WallDataService.retrieveWallThreads(params.channelId)
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
        <h1>Wall</h1>
        {this.state.threads.map((thread, index) => {
          return (
            <div className="thread-card">
              <a href={"/c/" + thread.channelOrigin} className="channelOrigin">posted in {thread.channelOrigin}</a><br/>
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

export default WallComponent
