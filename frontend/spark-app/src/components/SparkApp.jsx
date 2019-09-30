import React from 'react'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import AuthenticatedRoute from './AuthenticatedRoute.jsx'
import LoginComponent from './LoginComponent.jsx'
import ErrorComponent from './ErrorComponent.jsx'
import HeaderComponent from './HeaderComponent.jsx'
import FooterComponent from './FooterComponent.jsx'
import LogoutComponent from './LogoutComponent.jsx'
import ThreadComponent from './ThreadComponent.jsx'
import ChannelComponent from './ChannelComponent.jsx'
import CreateChannelComponent from './CreateChannelComponent.jsx'
import WallComponent from './WallComponent.jsx'
import RegisterComponent from './RegisterComponent.jsx'
import NewThreadComponent from './NewThreadComponent.jsx'

class SparkApp extends React.Component {
    render() {
        return (
            <div className="SparkApp">
                <Router>
                    <>
                        <HeaderComponent/>
                        <Switch>
                            <Route path="/" exact component={LoginComponent} />
                            <Route path="/login" component={LoginComponent} />
                            <Route path="/register" component={RegisterComponent} />
                            <AuthenticatedRoute path="/thread/:id" component={ThreadComponent} />
                            <AuthenticatedRoute path="/c/:channelId" component={ChannelComponent} />
                            <AuthenticatedRoute path="/create-channel" component={CreateChannelComponent} />
                            <AuthenticatedRoute path="/wall" component={WallComponent} />
                            <AuthenticatedRoute path="/new-thread" component={NewThreadComponent} />
                            <AuthenticatedRoute path="/logout" component={LogoutComponent}/>
                            <Route component={ErrorComponent}/>
                        </Switch>
                        <FooterComponent/>
                    </>
                </Router>
            </div>
        )
    }
}

export default SparkApp
