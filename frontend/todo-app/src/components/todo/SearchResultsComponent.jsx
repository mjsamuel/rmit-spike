import React, { Component } from 'react'
import SearchDataService from '../../api/todo/SearchDataService.js'

class SearchResultsComponent extends Component {

    constructor(props) {
      super(props);

      this.state = {
          users: [],
          channels: []
      };
    }

    componentDidMount() {
      const data = SearchDataService.makeSearch(this.props.query);

      this.setState({
        users: data.users,
        channels: data.channels,
      });
    }

    render() {
      return (
        <div className="card search-results-box border-top-0" tabIndex="0" onBlur={this.props.handleSearchFocus}>
          <div className="card-body">
            <b>Users:</b>
            <ul>
              {this.state.users.map((user, index) => {
                return (
                  <li key={index}>
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
                  <li key={index}>
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
