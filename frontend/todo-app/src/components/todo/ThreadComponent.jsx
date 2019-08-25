import React, { Component } from 'react'
import './ThreadComponent.css';
import { FaShareAlt, FaRegComment, FaFlag, FaAngleUp, FaAngleDown } from 'react-icons/fa';
import ThreadDataService from '../../api/todo/ThreadDataService.js'
import CommentComponent from './CommentComponent.jsx'
import AddCommentForm from './AddCommentForm.jsx'

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
class ThreadComponent extends Component {

	constructor(props){
		super(props)
		// console.log(props)
		this.state = {
			tagged_channels: [],
			content: '',
			comments: [],
			replyActive: true,
			reportActive: false,
			upspiked: false,
			downspiked: false
		}
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

		var id = this.props.id == null && this.props.match != null ? this.props.match.params.id : this.props.id;
		this.refresh(id);

	}

	/**
	 * Refreshes the content of the thread. Makes a GET call to the API to retrieve
	 * authoritative data and updates the state to repopulate the DOM as necessary
	 * @param id: the id of the thread
	 */
	refresh(id) {
		// console.log("Thread refreshed")
		var thread = ThreadDataService.retrieveThread(id)
		this.setState({ 
			content: thread.content,
			tagged_channels: thread.tagged_channels,
			comments: thread.comments,
			timeNumber: thread.timeNumber,
			timeUnit: thread.timeUnit
		})
		// console.log(this.state)
	}

	/**
	 * Set the report form to active when the "Report" button is clicked. Also deactivates
	 * the "Reply" form, as both cannot be open simultaneously.
	 */
	activateReport() {
		// console.log("Activate report called");
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
		ThreadDataService.updateThread(this.props.id, updatePacket)
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
		ThreadDataService.updateThread(this.props.id, updatePacket)
		// console.log(this.state)
	}
	

	/**
	 * Renders the thread HTML
	 */
    render() {
        return (
            <>
            	<div className="thread">
            		<div className="channel">
	                	<h2>c/{this.props.primary_channel}</h2>
	                </div>
	            	<div className="thread-title">
	                	<h1>{this.props.title}</h1>
	                </div>
	                <div className="author">
	                	<h3>Posted by u/{this.props.author} {this.state.timeNumber} {this.state.timeUnit} ago </h3>
	                </div>
	                <div className="thread-contents">
	                    <p>
	                    	{this.state.content}
						</p>
	                </div>
	                <div className="interactions">
                		<button className={this.state.upspiked ? 'upspiked' : 'no-spike'} onClick={this.addUpSpike}> <FaAngleUp/> </button>
                		<button className={this.state.downspiked ? 'downspiked' : 'no-spike'} onClick={this.addDownSpike}> <FaAngleDown/> </button>
                		<div className="divider"/>	                	
	                	<span className="comment-interaction"> <FaRegComment/> {this.state.comments.length} Comments </span>
                		<div className="divider"/>                
                		<button className="share-interaction" onClick={this.share}> <FaShareAlt/> Share </button>
                		<div className="divider"/>                		
                		<button className="report-interaction" onClick={this.activateReport}> <FaFlag/> Report </button>
	                </div>
	                <div className={this.state.replyActive ? 'active-reply' : 'hidden-reply'}>
	                	<AddCommentForm thread_id={this.props.id} isReply={false} isReport={false} updateParent={this.refresh}/>
	                </div>
		            <div className={this.state.reportActive ? 'active-report' : 'hidden-reply'}>
		                <AddCommentForm thread_id={this.props.thread_id} isReply={false} isReport={true} updateParent={this.props.updateParent}/>
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