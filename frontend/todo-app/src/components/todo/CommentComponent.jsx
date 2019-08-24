import React, { Component } from 'react'
import './CommentComponent.css';
import { FaShareAlt, FaRegComment, FaFlag, FaAngleUp, FaAngleDown } from 'react-icons/fa';
import CommentDataService from '../../api/todo/CommentDataService.js'

class CommentComponent extends Component {

	constructor(props){
		super(props)
		console.log(props)
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

	addUpSpike() {
		// TODO: check if user has already up-spiked a comment
		// TODO: change the colour of the icon on click
		const updatePacket = {
			spikes: this.state.spikes + 1
		}
		this.setState(updatePacket)
		CommentDataService.updateComment(this.props.id, updatePacket)
	}

	addDownSpike() {
		// TODO: check if user has already up-spiked a comment
		// TODO: change the colour of the icon on click
		const updatePacket = {
			spikes: this.state.spikes - 1
		}
		this.setState(updatePacket)
		CommentDataService.updateComment(this.props.id, updatePacket)
	}

	activateReply() {
		this.setState({
			replyActive: !this.state.replyActive
		})
	}

	share() {
		// TODO: Show share options
	}

	activateReport() {
		this.setState({
			reportActive: !this.state.reportActive
		})
	}

    render() {
        return (
        	<div className="comment">
        		<div className="comment-header">
        	    	<span className="author"> {this.props.author} </span>
        			<span className="spikes"> {this.props.spikes} Spikes </span>
        			<span className="timeDelta"> {this.props.timeNumber} {this.props.timeUnit} ago </span>
	        	</div>
        		<div className="content">
        			<p> {this.props.content}</p>
        		</div>
        		<div className="interactions">
                	<button className="up-spike" onClick={this.addUpSpike}> <FaAngleUp/> </button>
                	<button className="up-spike" onClick={this.addDownSpike}> <FaAngleDown/> </button>
            		<div className="divider"/>	                	
                	<button className="reply-interaction" onClick={this.activateReply}> <FaRegComment/> Reply </button>
            		<div className="divider"/>	                	
                	<button className="share-interaction" onClick={this.share}> <FaShareAlt/> Share </button>
            		<div className="divider"/>	                	
                	<button className="report-interaction" onClick={this.activateReport}> <FaFlag/> Report </button>
	            </div>
	        {/* TODO: Determine the nature of the form response and where it should be submitted */}
	            <div className={this.state.replyActive ? 'active-reply' : 'hidden-reply'}>
                	<form onSubmit={this.submitComment}>
                		<textarea rows="4" placeholder="What are your thoughts?"></textarea>
                		<input type="submit" className="btn btn-success" value="Reply" />
                	</form>
                </div>
	            <div className={this.state.reportActive ? 'active-report' : 'hidden-reply'}>
                	<form onSubmit={this.submitComment}>
                		<textarea rows="4" placeholder="What would you like to report? Please include as much detail as possible"></textarea>
                		<input type="submit" className="btn btn-success" value="Report" />
                	</form>
                </div>
            </div>
        )
    }
}

export default CommentComponent