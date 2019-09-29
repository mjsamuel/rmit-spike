import React, {Component} from 'react'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import AuthenticatedRoute from './AuthenticatedRoute.jsx'
import LoginComponent from './LoginComponent.jsx'
import ListTodosComponent from './ListTodosComponent.jsx'
import ErrorComponent from './ErrorComponent.jsx'
import HeaderComponent from './HeaderComponent.jsx'
import FooterComponent from './FooterComponent.jsx'
import LogoutComponent from './LogoutComponent.jsx'
import WelcomeComponent from './WelcomeComponent.jsx'
import TodoComponent from './TodoComponent.jsx'
import ThreadComponent from './ThreadComponent.jsx'
import ChannelComponent from './ChannelComponent.jsx'
import CreateChannelComponent from './CreateChannelComponent.jsx'
import WallComponent from './WallComponent.jsx'
import RegisterComponent from './RegisterComponent.jsx'
import NewThreadComponent from './NewThreadComponent.jsx'

class TodoApp extends Component {
    render() {
        return (
            <div className="TodoApp">
                <Router>
                    <>
                        <HeaderComponent/>
                        <Switch>
                            <Route path="/" exact component={LoginComponent} />
                            <Route path="/login" component={LoginComponent} />
                            <Route path="/register" component={RegisterComponent} />
                            <AuthenticatedRoute path="/thread/:id" component={ThreadComponent} />
                            <Route path="/thread" render={(props) => <ThreadComponent author="John Smith" title="The Rise and Fall of SEPT at RMIT" id="1" primary_channel="sept"/>} />
                            <Route path="/c/:channelId" component={ChannelComponent} />
                            <Route path="/create-channel" component={CreateChannelComponent} />
                            <Route path="/wall" component={WallComponent} />
                            <Route path="/new-thread" component={NewThreadComponent} />
                            <AuthenticatedRoute path="/welcome/:name" component={WelcomeComponent} />
                            <AuthenticatedRoute path="/todos/:id" component={TodoComponent}/>
                            <AuthenticatedRoute path="/todos" component={ListTodosComponent}/>
                            <AuthenticatedRoute path="/logout" component={LogoutComponent}/>

                            <Route component={ErrorComponent}/>
                        </Switch>
                        <FooterComponent/>
                    </>
                </Router>
                {/*<LoginComponent/>
                <WelcomeComponent/>*/}
            </div>
        )
    }
}

export default TodoApp
