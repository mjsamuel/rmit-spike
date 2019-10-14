import React from 'react'
import './CommentComponent.css';
import { FaShareAlt, FaRegComment, FaFlag, FaAngleUp, FaAngleDown } from 'react-icons/fa';
import CommentDataService from '../api/CommentDataService.js'
import UserDataService from '../api/UserDataService.js'
import InteractionEntryForm from './InteractionEntryForm.jsx'

/**
 * CommentComponent is a component representing each comment in a thread. It is responsible for
 * rendering the content of a comment, as well as author, spikes, time created, and possible
 * user interactions
 * It makes use of the following props:
 * @param id: id of the comment
 * @param author: author of the comment
 * @param spikes: number of spikes that the comment has received from users
 * @param timeNumber: the figure used to represent a statement such as "2 weeks ago"
 * @param timeUnit: the unit used to represent a statement such as "2 weeks ago"
 * @param content: The content of the comment
 */
class CommentComponent extends React.Component {

	constructor(props){
		super(props)
		// console.log(props)
		this.state = {
			author: '',
			spikes: this.props.spikes,
			replyActive: false,
			reportActive: false,
			upspiked: false,
			downspiked: false
		}

		this.addUpSpike = this.addUpSpike.bind(this)
        this.addDownSpike = this.addDownSpike.bind(this)
        this.activateReply = this.activateReply.bind(this)
        this.share = this.share.bind(this)
        this.activateReport = this.activateReport.bind(this)

	}

	componentDidMount() {
		//This will tax the API, should try to replace this with custom return server side
		// console.log("Comment component mounted", this.props.authorId)
		if (this.props.authorId) {
			UserDataService.getUser(this.props.authorId)
			.then((response) => {
				this.setState({author: response.data.username});
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
		// console.log(this.props)

	}

	/**
	 * Increment the number of spikes on the comment and change the icon colour to
	 * indicate that an upspike has been made. Sends request to API
	 */
	addUpSpike() {
		// TODO: check if user has already up-spiked a comment
		// console.log("Upspiked")
		this.setState({
			upspiked: !this.state.upspiked,
			downspiked: false
		})
		const updatePacket = {
			upspikes: this.props.upspikes + 1
		}
		this.setState({spikes: this.state.spikes + 1})
		CommentDataService.updateComment(this.props.id, updatePacket)
		// console.log(this.state)
	}

	/**
	 * Decrement the number of spikes on the comment and change the icon colour to
	 * indicate that a downspike has been made. Sends request to API
	 */
	addDownSpike() {
		// TODO: check if user has already up-spiked a comment
		// console.log("Upspiked")
		this.setState({
			downspiked: !this.state.downspiked,
			upspiked: false,
			spikes: this.state.spikes-1
		})

		const updatePacket = {
			downspikes: this.props.downspikes + 1
		}
		// this.setState({spikes: this.state.spikes - 1})
		// .then(() => console.log(this.state.spikes))
		// .catch((error) => console.log(error))
		CommentDataService.updateComment(this.props.id, updatePacket)
		// console.log(this.state)
	}

	/**
	 * Set the reply form to active when the "Reply" button is clicked. Also deactivates
	 * the "Report" form, as both cannot be open simultaneously.
	 */
	activateReply() {
		this.setState({
			replyActive: !this.state.replyActive,
			reportActive: false
		})
	}

	/**
	 *
	 */
	share() {
		// TODO: Show share options
	}

	/**
	 * Set the report form to active when the "Report" button is clicked. Also deactivates
	 * the "Reply" form, as both cannot be open simultaneously.
	 */
	activateReport() {
		this.setState({
			reportActive: !this.state.reportActive,
			replyActive: false
		})
	}

	/**
	 * Render the comment as a card
	 */
    render() {
        return (
        	<div className="comment">
        		<div className="comment-header">
        	    	<span className="author">u/{this.state.author}</span>
        			<span className="spikes">{this.state.spikes} Spikes</span>
        			<span className="timeDelta">{this.props.timeDelta}</span>
	        	</div>
        		<div className="content">
        			<p>{this.props.content}</p>
        		</div>
        		<div className="interactions">
                	<button className={this.state.upspiked ? 'upspiked' : 'no-spike'} onClick={this.addUpSpike}> <FaAngleUp/> </button>
                	<button className={this.state.downspiked ? 'downspiked' : 'no-spike'} onClick={this.addDownSpike}> <FaAngleDown/> </button>
            		<div className="divider"/>	                	
                	<button className="reply-interaction" onClick={this.activateReply}> <FaRegComment/> Reply </button>
            		<div className="divider"/>	                	
                	<button className="share-interaction" onClick={this.share}> <FaShareAlt/> Share </button>
            		<div className="divider"/>	                	
                	<button className="report-interaction" onClick={this.activateReport}> <FaFlag/> Report </button>
	            </div>
	            <div className={this.state.replyActive ? 'active-reply' : 'hidden-reply'}>
	                <InteractionEntryForm thread_id={this.props.threadId} isReply={true} reply_id={this.props.id} isReport={false} updateParent={this.props.updateParent}/>
                </div>
	            <div className={this.state.reportActive ? 'active-report' : 'hidden-reply'}>
	                <InteractionEntryForm thread_id={this.props.threadId} isReply={false} isReport={true} updateParent={this.props.updateParent}/>
                </div>
            </div>
        )
    }
}

export default CommentComponent