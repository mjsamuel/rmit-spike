import React from 'react'
import ChannelDataService from '../api/ChannelDataService.js'
import './ChannelComponent.css';
import ThreadListItem from './ThreadListItem'
import ChatComponent from './ChatComponent.jsx'
import { USER_NAME_SESSION_ATTRIBUTE_NAME } from './AuthenticationService.js'

class ChannelComponent extends React.Component {

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
      channelId: '',
      channelName: '',
      threads: [],
      subscribed: false,
      hasErrorOccured: false
    }
    this.id = this.props.match.params.channelId;
    this.subscribeClicked = this.subscribeClicked.bind(this)
    this.newThreadClicked = this.newThreadClicked.bind(this)
  }

  /**
   * Loads the current list of threads that belong to the current channel from the backend and updates
   * the state variables
   */
  componentDidMount() {
    const { match: { params } } = this.props;
    let userId = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME);

    ChannelDataService.getChannel(params.channelId, userId)
      .then((response) => {

        this.setState({
          channelId: params.channelId,
          channelName: response.data.channelName,
          subscribed: response.data.subscribed
        })
        ChannelDataService.retrieveChannelThreads(params.channelId)
        .then(response => {
          this.setState({threads: response.data})
        }).catch(error => console.log(error))
      }).catch(error => console.log(error))


  }

  /**
   * Notifies the backend that the user has subscribed/unsubscribed and changes the style
   * of the button accordigly by flipping the boolean that indicates the users subscription
   * state
   */
  subscribeClicked() {
    let userId = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
    ChannelDataService.subscribeToChannel(this.state.channelId, userId)
    .then(() => {
      this.setState({subscribed: !this.state.subscribed})
    })
    .catch(() => {

    })
  }

  /**
   * Redirects to the new thread page, passing a channel id as a paramater
   */
  newThreadClicked() {
    this.props.history.push(`/new-thread`, {
      channelId: this.state.channelId,
      channelName: this.state.channelName
    })
  }

  /**
   * Renders the channel HTML
   */
  render() {
    console.log("Channel ID: ", this.id)
    return (
      <div className="channel">
        <div className="thread-list">
          <h1 id="channel-name-banner">{"c/" + this.state.channelName}</h1>
          {this.state.threads.map((thread, index) => {
            return (
              <ThreadListItem thread={thread} displayOrigin={false} key={index}/>
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
          <button id="subsribe-btn" className={this.state.subscribed ? "btn btn-secondary" : "btn btn-success"} onClick={this.subscribeClicked}
          title={"Have c/" + this.state.channelName + "\'s most recent threads appear on your wall" }>
            {this.state.subscribed ? "Unsubscribe" : "Subscribe"}
          </button>
          <span>  </span>
          <button className="btn btn-success" onClick={this.newThreadClicked}>New Thread</button><br/>
          <ChatComponent channelId={this.id}/>
        </div>
      </div>
    )
  }
}

export default ChannelComponent
