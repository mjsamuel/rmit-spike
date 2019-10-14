import React from 'react'
import { Link } from 'react-router-dom'
import SearchResultsComponent from './SearchResultsComponent'
import AuthenticationService from './AuthenticationService.js'
import './HeaderComponent.css'
import { FaSearch, FaBell, FaWrench, FaPlus, FaDoorOpen } from 'react-icons/fa';

class HeaderComponent extends React.Component {

   /**
    * HeaderComponent is a component representing the navigation bar. It is responsible
    * for rendering the site name, a search text input as well as a dropdown menu that
    * links to user settigs, creating a new channel and logging out.
    */
    constructor(props) {
      super(props);

      this.state = {
          query: '',
          showSearchResults: false
      };

      this.handleChange = this.handleChange.bind(this);
      this.handleSearch = this.handleSearch.bind(this);
      this.handleSearchFocus = this.handleSearchFocus.bind(this);
    }

    /**
     * Updates the state of the component 'onChange' of an input field
     * @param id: the event object generated
     */
    handleChange(event) {
        this.setState(
            {
                [event.target.name]
                    : event.target.value
            }
        )
    }

    /**
     * Used for onBlur events, this function determines wether the element wether
     * the element that is now being focused on is a part of the search results box
     * otherwise hide the search results box.
     * @param e: the event object generated.
     */
    handleSearchFocus(e) {
      if (e.relatedTarget &&
      (e.relatedTarget.className === "dropdown-item" ||
      e.relatedTarget.className === "card search-results-box border-top-0")) {

      } else {
        this.setState({ showSearchResults: false });
      }

    }

    /**
     * Used for onSubmit event of the search input, this function determines wether
     * to show the search results box ot not (supressing the deafult form action as
     * as well).
     * @param e: the event object generated.
     */
    handleSearch(e) {
      e.preventDefault();

      if (this.state.query.trim() !== "") {
        this.setState({ showSearchResults: true });
      } else {
        this.setState({ showSearchResults: false });
      }
    }

    /**
     * Renders the navigation bar HTML
     */
    render() {
        // const isUserLoggedIn = AuthenticationService.isUserLoggedIn();
        //console.log(isUserLoggedIn);

        return (
          <header>
            <nav className="navbar navbar-expand-md navbar-dark bg-dark">
              <ul className="navbar-nav navbar-collapse">
                <li><a href="/wall" className="navbar-brand" data-toggle="tooltip" data-placement="top" title="Go to Home Page">RMIT Spike</a></li>
              </ul>
              <ul className="navbar-nav navbar-collapse justify-content-center">
                <form autoComplete="off" onSubmit={this.handleSearch} onBlur={this.handleSearchFocus}>
                  <div className="input-group">
                    <input type="text" className="form-control" placeholder="Search..." name="query" onChange={this.handleChange}/>
                    <div className="input-group-append">
                      <span className="input-group-text"><FaSearch /></span>
                    </div>
                  </div>
                </form>
              </ul>
              <ul className="navbar-nav navbar-collapse justify-content-end">
                <li><Link className="nav-link" to="" data-toggle="tooltip" data-placement="top" title="Logout of RMIT Spike"><FaBell /></Link></li>
                <li className="nav-item dropdown">
                  <a className="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">Account</a>
                  <div className="dropdown-menu dropdown-menu-right">
                    <a className="dropdown-item" href="">
                      <FaWrench /> User Settings
                    </a>
                    <a className="dropdown-item" href="/create-channel">
                     <FaPlus /> New Channel
                    </a>
                    <div className="dropdown-divider"></div>
                    <a className="dropdown-item" href="/logout" onClick={AuthenticationService.logout}>
                      <FaDoorOpen /> Logout
                    </a>
                  </div>
                </li>
              </ul>
            </nav>
            {this.state.showSearchResults &&
              <SearchResultsComponent
                query={this.state.query.trim()}
                handleSearchFocus={this.handleSearchFocus}
              />
            }
          </header>
        )
    }
}

export default HeaderComponent
