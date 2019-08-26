import React, { Component } from 'react'
import './CommentComponent.css';
import { FaShareAlt, FaRegComment, FaFlag, FaAngleUp, FaAngleDown } from 'react-icons/fa';
import CommentDataService from '../../api/todo/CommentDataService.js'
import AddCommentForm from './AddCommentForm.jsx'

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
class CommentComponent extends Component {

	constructor(props){
		super(props)
		// console.log(props)
		this.state = {
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
			spikes: this.state.spikes + 1
		}
		this.setState(updatePacket)
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
			upspiked: false
		})

		const updatePacket = {
			spikes: this.state.spikes - 1
		}
		this.setState(updatePacket)
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
        	    	<span className="author">u/{this.props.author}</span>
        			<span className="spikes">{this.props.spikes} Spikes</span>
        			<span className="timeDelta">{this.props.timeNumber} {this.props.timeUnit} ago</span>
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
	                <AddCommentForm thread_id={this.props.id} isReply={true} reply_id={this.id} isReport={false} updateParent={this.props.updateParent}/>
                </div>
	            <div className={this.state.reportActive ? 'active-report' : 'hidden-reply'}>
	                <AddCommentForm thread_id={this.props.thread_id} isReply={false} isReport={true} updateParent={this.props.updateParent}/>
                </div>
            </div>
        )
    }
}

export default CommentComponent