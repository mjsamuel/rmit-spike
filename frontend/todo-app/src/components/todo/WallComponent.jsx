import React, {Component} from 'react'
import WallDataService from '../../api/todo/WallDataService.js'
import './WallComponent.css';
import ThreadListItem from './ThreadListItem'
import {USER_NAME_SESSION_ATTRIBUTE_NAME} from '../../components/todo/AuthenticationService.js'

class WallComponent extends Component {

  /**
   * WallComponent is a component that represents the user's wall. It is responsible for rendering
   * all the most recent threads that have been posted to the channels that the user is subscribed to,
   * displaying the threads. title, author, what channels it has been posted to, number of comments and
   * the number of upspikes
   */
  constructor(props) {
    super(props)

    this.state = {
      threads: [],
      hasErrorOccured: false
    }
  }

  /**
   * Loads the current list of threads that belong to the current channels the user is subscribed to
   * and updates the state variables
   */
  componentDidMount() {
    let userId = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
    WallDataService.retrieveWallThreads(userId)
      .then((response) => {
        console.log(response);
        this.setState({
          threads: response.data.threads
        });
      })
      .catch(() => {

      });
  }

  /**
   * Renders the thread HTML
   */
  render() {
    return (
      <div className="wall">
        <div className="thread-list">
          <h1>Wall</h1>
          {this.state.threads.map((thread, index) => {
            return (
              <ThreadListItem thread={thread} displayOrigin={true} key={index}/>
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
        </div>
      </div>
    )
  }
}

export default WallComponent
