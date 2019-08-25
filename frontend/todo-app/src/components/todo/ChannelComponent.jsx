import React, {Component} from 'react'
import {API_URL} from '../../Constants'
import './ChannelComponent.css';
import axios from 'axios'
import { FaRegComment, FaAngleUp } from 'react-icons/fa';


class ChannelComponent extends Component {

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

    axios.get(`${API_URL}/channel/${params.channelId}`)
    .then((response) => {
      this.setState({
        channelName: response.data.channelName,
        threads: response.data.threads
      })
    })
    .catch(() => {
        this.setState({ hasErrorOccured: true })
    })

    this.setState({
      channelName: JSON.parse(response).channelName,
      threads: JSON.parse(response).threads
    })
  }

  render() {
    return (
      <div className="thread-list">
        <h1>{this.state.channelName}</h1>
        {this.state.hasErrorOccured && <div className="alert alert-warning">Something went wrong</div>}
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
            })}
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
