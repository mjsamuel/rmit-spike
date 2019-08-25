import React, {Component} from 'react'
import {API_URL} from '../../Constants'
import './ChannelComponent.css';
import axios from 'axios'

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
  }

  render() {
    return (
      <div className="thread-list">
        <h1>Channel Name</h1>
        {this.state.hasErrorOccured && <div className="alert alert-warning">Something went wrong</div>}
        <table className="table">
          <tbody>
            <tr>
              <th><a href="/thread"><b>The Rise and Fall of SEPT at RMIT</b><br/><i>John Smith</i></a></th>
            </tr>
            <tr>
              <th><a href="/thread"><b>thread title</b><br/><i>author</i></a></th>
            </tr>
          </tbody>
        </table>
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
