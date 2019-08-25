import React, { Component } from 'react'
import './ThreadComponent.css';
import { FaShareAlt, FaRegComment, FaFlag, FaAngleUp, FaAngleDown } from 'react-icons/fa';
import ThreadDataService from '../../api/todo/ThreadDataService.js'
import CommentComponent from './CommentComponent.jsx'
import AddCommentForm from './AddCommentForm.jsx'

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

	componentDidMount() {
		this.refresh(this.props.id);
	}

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

	activateReport() {
		// console.log("Activate report called");
		this.setState({
			reportActive: !this.state.reportActive,
			replyActive: this.state.reportActive ? true : false
		});
	}

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
	                <div className="username">
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