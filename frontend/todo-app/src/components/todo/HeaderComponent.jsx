import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import AuthenticationService from './AuthenticationService.js'
import { FaSearch, FaBell } from 'react-icons/fa';
import Logo from '../../SpikeLogo.png'

class HeaderComponent extends Component {
    render() {
        // const isUserLoggedIn = AuthenticationService.isUserLoggedIn();
        //console.log(isUserLoggedIn);

        return (
          <header>
            <nav className="navbar navbar-expand-md navbar-dark bg-dark">
              <ul className="navbar-nav navbar-collapse">
                <li><a href="/wall" className="navbar-brand">RMIT Spike</a></li>
              </ul>
              <ul className="navbar-nav navbar-collapse justify-content-center">
                <div className="input-group">
                  <input type="text" className="form-control" placeholder="Search..." />
                  <div className="input-group-append">
                    <span className="input-group-text"><FaSearch /></span>
                  </div>
                </div>
              </ul>
              <ul className="navbar-nav navbar-collapse justify-content-end">
                <li><Link className="nav-link" to=""><FaBell /></Link></li>
                <li className="nav-item dropdown">
                  <a className="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">Account</a>
                  <div className="dropdown-menu dropdown-menu-right">
                    <a className="dropdown-item" href="">User Settings</a>
                    <a className="dropdown-item" href="/create-channel">New Channel</a>
                    <div className="dropdown-divider"></div>
                    <a className="dropdown-item" href="/logout" onClick={AuthenticationService.logout}>Logout</a>
                  </div>
                </li>
              </ul>
            </nav>
          </header>
        )
    }
}

// <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">Dropdown link</a>
// <div class="dropdown-menu">
//   <a class="dropdown-item" href="/a">Link 1</a>
//   <a class="dropdown-item" href="/b">Link 2</a>
//   <a class="dropdown-item" href="/c">Link 3</a>
// </div>

export default HeaderComponent
