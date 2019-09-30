import React from 'react'
import SearchDataService from '../api/SearchDataService.js'

class SearchResultsComponent extends React.Component {

  /**
   * SearchResultsComponent is a component that displays the search results of a user query.
   * It is responsible for rendering the list of users that match the query as well as the list
   * of channels that match the query.
   * It makes use of the following props which are passed to it from the HeaderComponent:
   * @param query: the search query the user has input
   * @param handleSearchFocus: method that tells the navigation bar to stop displaying this component
   */
    constructor(props) {
      super(props);

      this.state = {
          users: [],
          channels: []
      };
    }

    /**
     * Makes a request to the backend to get all users and channels that match the search
     * query and updates the state of the component with what is returned
     */
    componentDidMount() {
      const data = SearchDataService.makeSearch(this.props.query);

      this.setState({
        users: data.users,
        channels: data.channels,
      });
    }

    /**
     * Renders the search results box HTML
     */
    render() {
      return (
        <div className="card search-results-box border-top-0" tabIndex="0" onBlur={this.props.handleSearchFocus}>
          <div className="card-body">
            <b>Users:</b>
            <ul>
              {this.state.users.map((user, index) => {
                return (
                  <li id={"search-username-" + index} key={index}>
                    <a className="dropdown-item" href={"/u/" + user.userId}>
                      {"u/" + user.username}
                    </a>
                  </li>
                  )
                })
              }
            </ul>
            <hr></hr>
            <b>Channels:</b>
            <ul>
              {this.state.channels.map((channel, index) => {
                return (
                  <li id={"search-channel-" + index} key={index}>
                    <a className="dropdown-item" href={"/c/" + channel.channelId}>
                      {"c/" + channel.channelName}
                    </a>
                  </li>
                  )
                })
              }
            </ul>
          </div>
        </div>
      )
    }
}

export default SearchResultsComponent
