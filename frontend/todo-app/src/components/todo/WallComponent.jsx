import React, {Component} from 'react'
import WallDataService from '../../api/todo/WallDataService.js'
import './WallComponent.css';
import { FaRegComment, FaAngleUp } from 'react-icons/fa';


class WallComponent extends Component {

  constructor(props) {
    super(props)

    this.state = {
      channelName: '',
      threads: [],
      hasErrorOccured: false
    }
  }

  componentDidMount() {
    const { match: { params } } = this.props;
    console.log(params.channelId)

    var data = WallDataService.retrieveWallThreads(params.channelId)
    this.setState({
      channelName: data.channelName,
      threads: data.threads
    })
  }

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
