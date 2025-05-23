import React from 'react'
import './ThreadComponent.css';
import { FaShareAlt, FaRegComment, FaFlag, FaAngleUp, FaAngleDown } from 'react-icons/fa';
import ThreadDataService from '../api/ThreadDataService.js'
import CommentDataService from '../api/CommentDataService.js'
import ChannelDataService from '../api/ChannelDataService.js'
import CommentComponent from './CommentComponent.jsx'
import InteractionEntryForm from './InteractionEntryForm.jsx'
import UserDataService from '../api/UserDataService';
import {USER_NAME_SESSION_ATTRIBUTE_NAME} from './AuthenticationService.js'

/**
 * ThreadComponent is a component representing a thread. It is responsible for rendering the
 * content of the thread, the comments present on the thread, as well as author, spikes,
 * time created, and possible user interactions
 * It makes use of the following props, which are also displayed on a card on the user feed
 * or channel page:
 * @param id: id of the thread
 * @param author: author of the thread
 * @param title: the title of the thread
 * @param timeNumber: the figure used to represent a statement such as "2 weeks ago"
 * @param timeUnit: the unit used to represent a statement such as "2 weeks ago"
 * @param primary_channel: The channel in which the thread was originally posted
 */
class ThreadComponent extends React.Component {

	constructor(props){
		super(props)
		// console.log(props)
		this.state = {
			tagged_channels: [],
			content: '',
			comments: [],
			commentsLoading: true,
			replyActive: true,
			reportActive: false,
			upspiked: false,
			downspiked: false,
			primary_channel: '',
			author: ''
		}
		this.id = this.props.match.params.id;
		this.refresh = this.refresh.bind(this);
		this.activateReport = this.activateReport.bind(this);
		this.addUpSpike = this.addUpSpike.bind(this);
		this.addDownSpike = this.addDownSpike.bind(this);
	}

	/**
	 * Loads the contents of the thread from it's props after the constructor has
	 * finished executing
	 */
	componentDidMount() {

		// let id = this.props.id == null && this.props.match != null ? this.props.match.params.id : this.props.id;
		this.refresh();
	}

	/**
	 * Refreshes the content of the thread. Makes a GET call to the API to retrieve
	 * authoritative data and updates the state to repopulate the DOM as necessary
	 * @param id: the id of the thread
	 */
	refresh() {
    let tagChannelPattern = new RegExp("c/[a-zA-Z0-9]+");

		ThreadDataService.retrieveThread(this.id)
		.then((response) => {
      if (response.data.taggedChannels) {
        let taggedChannels = response.data.content.match(tagChannelPattern);
        var taggedChannel = taggedChannels[0];

        response.data.content = response.data.content.replace(tagChannelPattern,
          `<a href="/c/${response.data.taggedChannels}">${taggedChannel}</a>`);
      }

			this.setState({
				title: response.data.title,
				content: response.data.content,
				tagged_channels: response.data.tagged_channels,
				timeDelta: response.data.timeDelta
			})

			// Channel id resolution
			ChannelDataService.getChannel(response.data.channelId, 1)
			.then((channel_response) => {
				this.setState({
					primary_channel: channel_response.data.channelName
				})
			}).catch(error => console.log(error))
			// User id resolution
			UserDataService.getUser(response.data.authorId)
			.then((user_response) => {
				this.setState({
					author: user_response.data.username
				});
			}).catch(error => console.log(error))

		}).catch(function (error) {
			console.log("Error getting thread")
			if (error.response) {
			  // The request was made and the server responded with a status code
			  // that falls out of the range of 2xx
			  console.log(error.response.data);
			  console.log(error.response.status);
			  console.log(error.response.headers);
			} else if (error.request) {
			  // The request was made but no response was received
			  // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
			  // http.ClientRequest in node.js
			  console.log(error.request);
			} else {
			  // Something happened in setting up the request that triggered an Error
			  console.log('Error', error.message);
			}
			console.log(error.config);
		  })

		CommentDataService.getComments(this.id)
		.then((response) => {

      response.data.map((comment, i) => {
        if (comment.taggedChannels) {
          let taggedChannels = comment.content.match(tagChannelPattern);
          var taggedChannel = taggedChannels[0];

          comment.content = comment.content.replace(tagChannelPattern,
            `<a href="/c/${comment.taggedChannels}">${taggedChannel}</a>`);
        console.log(i + " tagged " + comment.taggedChannels)
        }
      });

			// console.log(response)
			this.setState({
				comments: response.data,
				commentsLoading: false
			})
		}).catch(function (error) {
			console.log("Error getting comments")
			if (error.response) {
			  // The request was made and the server responded with a status code
			  // that falls out of the range of 2xx
			  console.log(error.response.data);
			  console.log(error.response.status);
			  console.log(error.response.headers);
			} else if (error.request) {
			  // The request was made but no response was received
			  // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
			  // http.ClientRequest in node.js
			  console.log(error.request);
			} else {
			  // Something happened in setting up the request that triggered an Error
			  console.log('Error', error.message);
			}
			console.log(error.config);
		  })

	}

	/**
	 * Set the report form to active when the "Report" button is clicked. Also deactivates
	 * the "Reply" form, as both cannot be open simultaneously.
	 */
	activateReport() {
		this.setState({
			reportActive: !this.state.reportActive,
			replyActive: this.state.reportActive ? true : false
		});
	}

	/**
	 * Increment the number of spikes on the comment and change the icon colour to
	 * indicate that an upspike has been made. Sends request to API
	 */
	addUpSpike() {
		// TODO: check if user has already up-spiked a thread
		// console.log("Upspiked")
		this.setState({
			upspiked: !this.state.upspiked,
			downspiked: false
		})
		const updatePacket = {
			spikes: this.state.spikes + 1
		}
		this.setState(updatePacket)
		ThreadDataService.updateThread(this.id, updatePacket)
		// console.log(this.state)
	}

	/**
	 * Decrement the number of spikes on the comment and change the icon colour to
	 * indicate that a downspike has been made. Sends request to API
	 */
	addDownSpike() {
		// TODO: check if user has already up-spiked a thread
		// console.log("Upspiked")
		this.setState({
			downspiked: !this.state.downspiked,
			upspiked: false
		})

		const updatePacket = {
			spikes: this.state.spikes - 1
		}
		this.setState(updatePacket)
		ThreadDataService.updateThread(this.id, updatePacket)
	}


	/**
	 * Renders the thread HTML
	 */
    render() {
		// console.log(this.state)
        return (
            <>
            	<div className="thread">
            		<div className="channel">
	                	<h2>c/{this.state.primary_channel}</h2>
	                </div>
	            	<div className="thread-title">
	                	<h1>{this.state.title}</h1>
	                </div>
	                <div className="thread-author">
	                	<h4>Posted by u/{this.state.author} {this.state.timeDelta}</h4>
	                </div>
	                <div className="thread-contents">
	                    <p dangerouslySetInnerHTML={{ __html: this.state.content}} />
	                </div>
	                <div className="interactions">
                		<button className={this.state.upspiked ? 'upspiked' : 'no-spike'} onClick={this.addUpSpike}> <FaAngleUp/> </button>
                		<button className={this.state.downspiked ? 'downspiked' : 'no-spike'} onClick={this.addDownSpike}> <FaAngleDown/> </button>
                		<div className="divider"/>
	                	<span className="comment-interaction"> <FaRegComment/> {this.state.comments.length} Comments </span>
                		<div className="divider"/>
                		<button className="share-interaction" onClick={this.share} title="Share to others"> <FaShareAlt/> Share </button>
                		<div className="divider"/>
                		<button className="report-interaction" onClick={this.activateReport}> <FaFlag/> Report </button>
	                </div>
	                <div className={this.state.replyActive ? 'active-reply' : 'hidden-reply'}>
	                	<InteractionEntryForm thread_id={this.id} isReply={false} isReport={false} updateParent={this.refresh}/>
	                </div>
		            <div className={this.state.reportActive ? 'active-report' : 'hidden-reply'}>
		                <InteractionEntryForm thread_id={this.id} isReply={false} isReport={true} updateParent={this.props.updateParent}/>
	                </div>

	                <div className="comments">
	                	{this.state.comments.map((comment, i) => ( <CommentComponent key={i} updateParent={this.refresh} {...comment} />))}
	                </div>
	            </div>
            </>
        )
    }
}

export default ThreadComponent
